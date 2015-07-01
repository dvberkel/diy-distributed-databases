package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.repair.AlwaysRepair;
import com.github.seeemilyplay.diydistdb.resolve.ReturnMostRecent;
import org.apache.logging.log4j.LogManager;

public class Runner {
    public static void main( String[] args ) throws Exception {
        Node[] nodes = new Node[]{
                new Node("http://localhost:8080"),
                new Node("http://localhost:8081"),
                new Node("http://localhost:8082")
        };
        DatabaseClient databaseClient = new DatabaseClient(
                LogManager.getLogger(DatabaseClient.class),
                new AlwaysRepair(LogManager.getLogger(AlwaysRepair.class)),
                new ReturnMostRecent(),
                nodes, 2, 2);

        databaseClient.write(new Thing(3, "foo"));
        databaseClient.write(new Thing(7, "bar"));
        Thing thing3 = databaseClient.read(3);
        Thing thing7 = databaseClient.read(7);
        System.out.println(thing3);
        System.out.println(thing7);
    }
}
