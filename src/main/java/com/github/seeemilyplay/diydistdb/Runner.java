package com.github.seeemilyplay.diydistdb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    public static void main( String[] args ) throws Exception {
        Logger logger = LogManager.getLogger(DatabaseClient.class);
        Node[] nodes = new Node[]{
                new Node("http://localhost:8080"),
                new Node("http://localhost:8081"),
                new Node("http://localhost:8082")
        };
        DatabaseClient databaseClient = new DatabaseClient(logger, new ReturnMostRecent(), nodes, 2, 2);

        databaseClient.write(new Thing(3, "foo"));
        databaseClient.write(new Thing(7, "bar"));
        Thing thing3 = databaseClient.read(3);
        Thing thing7 = databaseClient.read(7);
        System.out.println(thing3);
        System.out.println(thing7);
    }
}
