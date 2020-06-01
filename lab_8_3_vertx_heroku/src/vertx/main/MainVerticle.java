package vertx.main;/*
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
import io.vertx.core.DeploymentOptions;

import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;
import vertx.dao.WikiDatabaseVerticle;


public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> promise) throws Exception {

	  Promise<String> dbVerticleDeployment = Promise.promise();  
    
   // ClusterManager mgr = new HazelcastClusterManager();

//    VertxOptions options = new VertxOptions().setClusterManager(mgr);
	  
	


   
    vertx.deployVerticle(new WikiDatabaseVerticle(), dbVerticleDeployment);

    dbVerticleDeployment.future().compose(id -> {  

  	  Promise<String> httpVerticleDeployment = Promise.promise();
  	
  	  vertx.deployVerticle(
  		       "vertx.web.HttpServerVerticle",
  		        new DeploymentOptions().setInstances(2),    
  		        httpVerticleDeployment);

    return httpVerticleDeployment.future();  

  }).setHandler(ar -> {   
    if (ar.succeeded()) {
      promise.complete();
    } else {
      promise.fail(ar.cause());
    }
  });
    
    
  }
}
