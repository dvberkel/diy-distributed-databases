package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.connection.Node;
import com.github.seeemilyplay.diydistdb.repair.AlwaysRepair;
import com.github.seeemilyplay.diydistdb.resolve.ReturnMostRecent;
import com.github.seeemilyplay.diydistdb.ui.CommandLineParser;
import com.github.seeemilyplay.diydistdb.ui.command.Command;
import com.github.seeemilyplay.diydistdb.ui.result.Result;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
    public static final String QUIT_COMMAND = "quit";

    public static void main( String[] args ) {
        Logger logger = LogManager.getLogger(Shell.class);
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
        String line = "";
        do {
            System.out.print("> ");
            try {
                line = reader.readLine();
            } catch (IOException e) {
                logger.debug(String.format("error while reading line: %s", e.getMessage()));
                line = QUIT_COMMAND;
                continue;
            }
            if (line.equalsIgnoreCase(QUIT_COMMAND)) { break; }
            Command command = parser.parse(line);
            Result result = null;
            try {
                result = command.executeOn(databaseClient);
            } catch (Exception e) {
                logger.debug(String.format("error while executing command: %s", e.getMessage()));
                continue;
            }
            result.report();
        } while(true);
    }
}
