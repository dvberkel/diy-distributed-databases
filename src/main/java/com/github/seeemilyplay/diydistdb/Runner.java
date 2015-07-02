package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.connection.Node;
import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.repair.AlwaysRepair;
import com.github.seeemilyplay.diydistdb.resolve.ReturnMostRecent;
import com.github.seeemilyplay.diydistdb.ui.CommandLineParser;
import com.github.seeemilyplay.diydistdb.ui.command.Command;
import com.github.seeemilyplay.diydistdb.ui.result.Result;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        CommandLineParser parser = new CommandLineParser();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        do {
            System.out.print("> ");
            line = reader.readLine();
            Command command = parser.parse(line);
            Result result = command.executeOn(databaseClient);
            result.report();
        } while(!line.equalsIgnoreCase("quit"));
    }
}
