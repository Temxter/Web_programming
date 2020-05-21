/**
 * Copyright (c) 2014 Oracle and/or its affiliates. All rights reserved.
 *
 * You may not modify, use, reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * https://github.com/javaee/tutorial-examples/LICENSE.txt
 */
package controller.chat;

import controller.chat.decoders.MessageDecoder;
import controller.chat.encoders.ChatMessageEncoder;
import controller.chat.encoders.InfoMessageEncoder;
import controller.chat.encoders.JoinMessageEncoder;
import controller.chat.encoders.UsersMessageEncoder;
import controller.chat.messages.*;
import model.Dao.DaoUser;
import model.Entities.User;
import model.Entities.UserType;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Websocket endpoint */
@ServerEndpoint(
        value = "/websocketbot",
        decoders = { MessageDecoder.class }, 
        encoders = { JoinMessageEncoder.class, ChatMessageEncoder.class, 
                     InfoMessageEncoder.class, UsersMessageEncoder.class }
        )
/* There is a BotEndpoint instance per connetion */
public class BotEndpoint {
    private static final Logger logger = Logger.getLogger("BotEndpoint");
    
    @OnOpen
    public void openConnection(Session session) {
        logger.log(Level.INFO, "Connection opened.");
    }
    
    @OnMessage
    public void message(final Session session, Message msg) {
        logger.log(Level.INFO, "Received: {0}", msg.toString());
        
        if (msg instanceof JoinMessage) {
            /* Add the new user and notify everybody */
            JoinMessage jmsg = (JoinMessage) msg;

            String username = jmsg.getName().split("#")[0];

            if (jmsg.getName().equalsIgnoreCase("admin#password")) {
                session.getUserProperties().put("admin", true);
                session.getUserProperties().put("name", "admin");
                sendAll(session, new UsersMessage(this.getAdminList(session)));
                sendAll(session, new InfoMessage(username +
                        " has joined the chat"));
            }
            else {
                session.getUserProperties().put("admin", false);
                session.getUserProperties().put("name", username);
                sendToMyself(session, new UsersMessage(this.getAdminList(session)));
                sendAdmin(session, new InfoMessage(username +
                        " has joined the chat"));
            }

            session.getUserProperties().put("active", true);

            logger.log(Level.INFO, "Received: {0}", jmsg.toString());

            sendAdmin(session, new UsersMessage(this.getUserList(session)));
            
        } else if (msg instanceof ChatMessage) {
            final ChatMessage cmsg = (ChatMessage) msg;
            logger.log(Level.INFO, "Received: {0}", cmsg.toString());

           if ((boolean)session.getUserProperties().get("admin") == true){
               if (!cmsg.getTarget().isEmpty()) {
                   sendToUser(session, cmsg);
               } else {
                   sendAll(session, cmsg);
               }
           }
           else {
               sendToMyself(session, cmsg);
               sendAdmin(session, cmsg);
           }

       }
    }

    
    @OnClose
    public void closedConnection(Session session) {
        /* Notify everybody */
        session.getUserProperties().put("active", false);
        if (session.getUserProperties().containsKey("name")) {
            String name = session.getUserProperties().get("name").toString();
            sendAll(session, new InfoMessage(name + " has left the chat"));
            sendAll(session, new UsersMessage(this.getUserList(session)));
        }
        logger.log(Level.INFO, "Connection closed.");
    }
    
    @OnError
    public void error(Session session, Throwable t) {
        logger.log(Level.INFO, "Connection error ({0})", t.toString());
    }
    
    /* Forward a message to all connected clients
     * The endpoint figures what encoder to use based on the message type */
    public synchronized void sendAll(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen()) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }   
    }

    public void sendToMyself(Session session, Object msg){
        try {
            session.getBasicRemote().sendObject(msg);
            logger.log(Level.INFO, "Sent: {0}", msg.toString());
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    public synchronized void sendToUser(Session session, Object msg) {
        try {
            session.getBasicRemote().sendObject(msg);

            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && s.getUserProperties().get("name").equals(
                        ((ChatMessage)msg).getTarget() )
                ) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }

    public synchronized void sendAdmin(Session session, Object msg) {
        try {
            for (Session s : session.getOpenSessions()) {
                if (s.isOpen() && (boolean)s.getUserProperties().get("admin") == true) {
                    s.getBasicRemote().sendObject(msg);
                    logger.log(Level.INFO, "Sent: {0}", msg.toString());
                }
            }
        } catch (IOException | EncodeException e) {
            logger.log(Level.INFO, e.toString());
        }
    }
    
    /* Returns the list of users from the properties of all open sessions */
    public List<String> getUserList(Session session) {
        List<String> users = new ArrayList<>();
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }

    public List<String> getAdminList(Session session) {
        List<String> users = new ArrayList<>();
        for (Session s : session.getOpenSessions()) {
            if (s.isOpen() && (boolean) s.getUserProperties().get("active")
                    && (boolean) s.getUserProperties().get("admin"))
                users.add(s.getUserProperties().get("name").toString());
        }
        return users;
    }
}
