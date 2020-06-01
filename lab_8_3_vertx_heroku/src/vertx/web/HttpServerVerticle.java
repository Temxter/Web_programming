/*
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

package vertx.web;


import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import io.vertx.servicediscovery.Record;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.HttpEndpoint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;


public class HttpServerVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);

  public static final String CONFIG_HTTP_SERVER_PORT = "http.server.port";  
  public static final String CONFIG_WIKIDB_QUEUE = "wikidb.queue";

  private String wikiDbQueue = "wikidb.queue";
  private FreeMarkerTemplateEngine templateEngine;
  
  private ServiceDiscovery discovery;

  @Override
  public void start(Promise<Void> promise) throws Exception {
	 
	  
	    int portNumber = Integer.getInteger("http.port");
	  

    wikiDbQueue = config().getString(CONFIG_WIKIDB_QUEUE, "wikidb.queue");  
   
		
     /*		 discovery = ServiceDiscovery.create(vertx, new ServiceDiscoveryOptions()
		 .setAnnounceAddress("service-announce") .setName("bsu-name"));
		 */
    
    Record    record = HttpEndpoint.createRecord("tests-list", "tests-list.herokuapp.com", portNumber, "/");
/*    discovery.publish(record, ar -> {
      if (ar.succeeded()) {
        // publication succeeded
        Record publishedRecord = ar.result();
        System.out.println(" service is published   "+ publishedRecord.getName());
      } else {
        // publication failed
    	  System.out.println("Error in publishing service");
      }
    });
  */  
    
    HttpServer server = vertx.createHttpServer();

    Router router = Router.router(vertx);
    router.get("/").handler(this::indexHandler);
    router.get("/wiki/:test").handler(this::testRenderingHandler);
    router.post().handler(BodyHandler.create());
    router.post("/save").handler(this::testUpdateHandler);
    router.post("/create").handler(this::testCreateHandler);
    router.post("/delete").handler(this::testDeletionHandler);
    
    templateEngine = FreeMarkerTemplateEngine.create(vertx);
 
    server
      .requestHandler(router)
      .listen(portNumber, ar -> {
        if (ar.succeeded()) {
          LOGGER.info("HTTP server running on port " + portNumber);
          promise.complete();
          discovery = ServiceDiscovery.create(vertx);
          discovery.publish(record, ar1 -> {
              if (ar1.succeeded()) {
                // publication succeeded
                Record publishedRecord = ar1.result();
                System.out.println(" service is published   "+ publishedRecord.getName());
              } else {
                // publication failed
            	  System.out.println("Error in publishing service");
              }
            });
          
          
        } else {
          LOGGER.error("Could not start a HTTP server", ar.cause());
          promise.fail(ar.cause());
        }
      });
  }

  

//  @SuppressWarnings("deprecation")
private void indexHandler(RoutingContext context) {

    DeliveryOptions options = new DeliveryOptions().addHeader("action", "all-tests");
 
    vertx.eventBus().request(wikiDbQueue, new JsonObject(), options, reply -> {  
      if (reply.succeeded()) {
        JsonObject body = (JsonObject) reply.result().body();   
        context.put("title", "Home");
        context.put("tests", body.getJsonArray("tests").getList());
       
        templateEngine.render(context.data(), "templates/index.ftl", ar -> {
          if (ar.succeeded()) {
            context.response().putHeader("Content-Type", "text/html");
            context.response().end(ar.result());
          } else {
            context.fail(ar.cause());
          }
        });
      } else {
        context.fail(reply.cause());
      }
    });
  }
  
  private static final String EMPTY_PAGE_MARKDOWN =
  "# A new page\n" +
    "\n" +
    "Feel-free to write in Markdown!\n";

//  @SuppressWarnings("deprecation")
  private void testRenderingHandler(RoutingContext context) {

    String requestedTest = context.request().getParam("test");
    JsonObject request = new JsonObject().put("testName", requestedTest);

    DeliveryOptions options = new DeliveryOptions().addHeader("action", "get-test");
    vertx.eventBus().request(wikiDbQueue, request, options, reply -> {

      if (reply.succeeded()) {
        JsonObject body = (JsonObject) reply.result().body();

        boolean found = body.getBoolean("found");
   if(found) {
        String rawContent = body.getString("rawContent", EMPTY_PAGE_MARKDOWN);
        context.put("title", requestedTest);
        
 
        context.put("id", body.getInteger("id"));
        context.put("name", body.getString("name"));
        context.put("newTest", found ? "no" : "yes");
        context.put("rawContent", rawContent);
 
       
   }
   else {
	   context.put("title", requestedTest);
	   context.put("id", 0);
	   context.put("name", "");
       context.put("newTest", found ? "no" : "yes");
       context.put("rawContent", "");
   }
   context.put("timestamp", new Date().toString());
        templateEngine.render(context.data(), "templates/page.ftl", ar -> {
          if (ar.succeeded()) {
            context.response().putHeader("Content-Type", "text/html");
            context.response().end(ar.result());
          } else {
            context.fail(ar.cause());
          }
        });

      } else {
        context.fail(reply.cause());
      }
    });
  }

//  @SuppressWarnings("deprecation")
  private void testUpdateHandler(RoutingContext context)  {

    String title = context.request().getParam("title");
    JsonObject request = new JsonObject()
      .put("name", context.request().getParam("name"))
      .put("id", context.request().getParam("id"))
      .put("markdown", context.request().getParam("markdown"));
    System.out.println("From http verticle update:   " + context.request().getParam("title")
            + context.request().getParam("markdown"));
    
    DeliveryOptions options = new DeliveryOptions();
    if ("yes".equals(context.request().getParam("newPage"))) {
      options.addHeader("action", "create-test");
    } else {
      options.addHeader("action", "save-test");
    }
    context.put("title", context.request().getParam("name"));
    System.out.println("From http verticle update:   " + context.request().getParam("name"));
    vertx.eventBus().request(wikiDbQueue, request, options, reply -> {
      if (reply.succeeded()) {
        context.response().setStatusCode(303);
        try {
			context.response().putHeader("Location", "/wiki/" + URLEncoder.encode(context.request().getParam("name"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        context.response().end();
      } else {
        context.fail(reply.cause());
      }
    });
  }

  private void testCreateHandler(RoutingContext context) {
    String testName = context.request().getParam("name");
    try {	
		String testNameUtf8=URLEncoder.encode(testName, "UTF-8");
    
    String location = "/wiki/" + testNameUtf8;
    if (testName == null || testName.isEmpty()) {
      location = "/";
    }
    
    
    context.response().setStatusCode(303);
    context.response().putHeader("Location", location);
    context.response().end();
    } catch (UnsupportedEncodingException e) {
		
		e.printStackTrace();
	}
  }

 
  private void testDeletionHandler(RoutingContext context) {
	  System.out.println("From http verticle delete:   ");
	 String id = context.request().getParam("id");
    System.out.println("From http verticle delete:   "+ id);
    JsonObject request = new JsonObject().put("id", id);
    DeliveryOptions options = new DeliveryOptions().addHeader("action", "delete-test");
    vertx.eventBus().request(wikiDbQueue, request, options, reply -> {
      if (reply.succeeded()) {
        context.response().setStatusCode(303);
        context.response().putHeader("Location", "/");
        context.response().end();
      } else {
        context.fail(reply.cause());
      }
    });
  }
  // end::rest[]
}
