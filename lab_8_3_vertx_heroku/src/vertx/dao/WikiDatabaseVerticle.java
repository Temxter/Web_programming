package vertx.dao;/*
 *  Copyright (c) 2017 Red Hat, Inc. and/or its affiliates.
 *  Copyright (c) 2017 INSA Lyon, CITI Laboratory.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

import io.vertx.ext.asyncsql.PostgreSQLClient;


import java.util.List;
import java.util.Optional;

import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class WikiDatabaseVerticle extends AbstractVerticle {

  public static final String CONFIG_WIKIDB_JDBC_URL = "wikidb.jdbc.url";
  public static final String CONFIG_WIKIDB_JDBC_DRIVER_CLASS = "wikidb.jdbc.driver_class";
  public static final String CONFIG_WIKIDB_JDBC_MAX_POOL_SIZE = "wikidb.jdbc.max_pool_size";
  public static final String CONFIG_WIKIDB_SQL_QUERIES_RESOURCE_FILE = "wikidb.sqlqueries.resource.file";
  
  final static String QUERY_SELECT_BY_ID="SELECT id, Name FROM tests WHERE name = ?";
  final static String QUERY_SELECT_All_TESTS ="SELECT id, Name FROM tests";
  final static String QUERY_CREATE_TEST= "insert into tests values (?, ?)";
  final static String QUERY_SAVE_TEST="update tests set Name = ? where id = ?";
  final static String QUERY_DELETE_TEST="delete from tests where id = ?";

  private static int countId;
  
  public static final String CONFIG_WIKIDB_JDBC_HOST = "host";  
  public static final String CONFIG_WIKIDB_QUEUE = "wikidb.queue";
  
    private String host;
    private String url;
	private String driver;
	private String user;
	private String pass;
	private String databasename;
	private int port;
	private String sslmode;


 
  private void loadDbProperties()  {
	  ResourceBundle  resource = ResourceBundle.getBundle("database");
	    host= resource.getString("host");
	    url= resource.getString("url");
	    driver= resource.getString("driver");
	    user = resource.getString("username");
	    pass = resource.getString("password");
	    databasename= resource.getString("database");
	    port=Integer.parseInt(resource.getString("port"));
	    sslmode=resource.getString("ssl");
  }

  

  private SQLClient postgreClient;


  @Override
  public void start(Promise<Void> promise) throws Exception {

    /*
     * Note: this uses blocking APIs, but data is small...
     */
	  loadDbProperties();  
  
   
    	
  
    
   JsonObject postgreSQLClientConfig = new JsonObject().put("host", host).put("port", port).put("username", user).put("password", pass).put("database", databasename).put("sslmode", sslmode);
   
 
    
    postgreClient = PostgreSQLClient.createNonShared(vertx, postgreSQLClientConfig);
    

     postgreClient.getConnection(ar -> {
      if (ar.failed()) {
    //    LOGGER.error("Could not open a database connection", ar.cause());
    	
    	  promise.fail(ar.cause());
      } else {
    	  vertx.eventBus().consumer(config().getString(CONFIG_WIKIDB_QUEUE, "wikidb.queue"), this::onMessage); 
    	  promise.complete();
          
      
      }
     
    });
  }
 

  public enum ErrorCodes {
    NO_ACTION_SPECIFIED,
    BAD_ACTION,
    DB_ERROR
  }

  public void onMessage(Message<JsonObject> message) {

    if (!message.headers().contains("action")) {
  //   LOGGER.error("No action header specified for message with headers {} and body {}",
  //     message.headers(), message.body().encodePrettily());
      message.fail(ErrorCodes.NO_ACTION_SPECIFIED.ordinal(), "No action header specified");
      return;
    }
    String action = message.headers().get("action");

    switch (action) {
      case "all-tests":
    	  fetchAllTests(message);
        break;
      case "get-test":
        fetchTest(message);
        break;
      case "create-test":
        createTest(message);
        break;
      case "save-test":
        saveTest(message);
        break;
      case "delete-test":
        deleteTest(message);
        break;
      default:
        message.fail(ErrorCodes.BAD_ACTION.ordinal(), "Bad action: " + action);
    }
  }
 
  private void fetchAllTests(Message<JsonObject> message) {
	 
	
	  postgreClient.getConnection(car -> {
      if (car.succeeded()) {
        SQLConnection connection = car.result();
        connection.query(QUERY_SELECT_All_TESTS, res -> {
          connection.close();
          if (res.succeeded()) {
            List<String> tests = res.result()
              .getResults()
              .stream()
              .map(json -> json.getString(1))
              .sorted()
              .collect(Collectors.toList());
           
            Stream<Integer> streamIds =  res.result().getResults().stream().map(json -> json.getInteger(0));
            Optional<Integer> maxId = streamIds.max(Integer::compare);
    
     
           if(maxId.isPresent()) {
            countId=maxId.get() + 1;
          
            message.reply(new JsonObject().put("tests", new JsonArray(tests)).put("count_id", countId));
           }
          } else {
        	 
       	  reportQueryError(message, res.cause());
          }
        });
      } else {
    	 
        reportQueryError(message, car.cause());
      }
    });
  }

  private void fetchTest(Message<JsonObject> message) {


    String requestedTest = message.body().getString("testName");
   

    postgreClient.getConnection(car -> {
      if (car.succeeded()) {
        SQLConnection connection = car.result();
        connection.queryWithParams(QUERY_SELECT_BY_ID, new JsonArray().add(requestedTest), fetch -> {
          connection.close();
          if (fetch.succeeded()) {
            JsonObject response = new JsonObject();
            ResultSet resultSet = fetch.result();
            if (resultSet.getNumRows() == 0) {
              response.put("found", false);
            } else {
              response.put("found", true);
              JsonArray row = resultSet.getResults().get(0);
              response.put("id", row.getInteger(0));
              response.put("name", row.getString(1));
            }
            message.reply(response);
          } else {
            reportQueryError(message, fetch.cause());
          }
        });
      } else {
        reportQueryError(message, car.cause());
      }
    });

  }

  private void createTest(Message<JsonObject> message) {
	  
    JsonObject request = message.body();

    postgreClient.getConnection(car -> {

      if (car.succeeded()) {
        SQLConnection connection = car.result();
        JsonArray data = new JsonArray()
          .add(countId)		
          .add(request.getString("name"));

        connection.updateWithParams(QUERY_CREATE_TEST, data, res -> {
          connection.close();
          if (res.succeeded()) {
            message.reply("ok");
          } else {
            reportQueryError(message, res.cause());
          }
        });
      } else {
        reportQueryError(message, car.cause());
      }
    });
  }

  private void saveTest(Message<JsonObject> message) {
	  
    JsonObject request = message.body();

    postgreClient.getConnection(car -> {

      if (car.succeeded()) {
        SQLConnection connection = car.result();
        JsonArray data = new JsonArray()
          .add(request.getString("name"))
          .add(request.getString("id"));

        connection.updateWithParams(QUERY_SAVE_TEST, data, res -> {
          connection.close();
          if (res.succeeded()) {
            message.reply("ok");
          } else {
            reportQueryError(message, res.cause());
          }
        });
      } else {
        reportQueryError(message, car.cause());
      }
    });
  }

  private void deleteTest(Message<JsonObject> message) {
	  
	  postgreClient.getConnection(car -> {
      if (car.succeeded()) {
        SQLConnection connection = car.result();
        JsonArray data = new JsonArray().add(message.body().getString("id"));
        connection.updateWithParams(QUERY_DELETE_TEST, data, res -> {
          connection.close();
          if (res.succeeded()) {
            message.reply("ok");
          } else {
            reportQueryError(message, res.cause());
          }
        });
      } else {
        reportQueryError(message, car.cause());
      }
    });
  }

  private void reportQueryError(Message<JsonObject> message, Throwable cause) {
 //   LOGGER.error("Database query error", cause);
    message.fail(ErrorCodes.DB_ERROR.ordinal(), cause.getMessage());
  }
  // end::rest[]
}
