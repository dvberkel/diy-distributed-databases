package com.github.seeemilyplay.diydistdb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * A Main method you can use as a stub.
 */
public class DatabaseClient {
    private final Logger logger;
    private final Repairer repairer;
    private final Resolver resolver;
    private final Node[] nodes;
    private final int writeConsistency;
    private final int readConsistency;

    public DatabaseClient(Logger logger, Repairer repairer, Resolver resolver, Node[] nodes, int writeConsistency, int readConsistency) {
        this.logger = logger;
        this.repairer = repairer;
        this.resolver = resolver;
        this.nodes = nodes;
        this.writeConsistency = writeConsistency;
        this.readConsistency = readConsistency;
    }

    public void write(Thing thing) throws Exception {
        int successCount = 0;
        for(Node node: nodes) {
            try {
                node.putThing(thing);
                successCount++;
            } catch(Exception e) {
                logger.debug(String.format("Could not write thing %s to %s", thing, node.getUrl()));
            }
        }
        if (successCount < writeConsistency) {
            throw new Exception(String.format("Unable to successfully write to enough nodes. Wrote to %d nodes", successCount));
        }
    }


    public Thing read(int id) throws Exception {
        int successCount = 0;
        List<Thing> results = new ArrayList<Thing>();
        int nodeIndex = 0;
        while (nodeIndex < nodes.length && successCount < readConsistency) {
            Node node = nodes[nodeIndex++];
            try {
                Thing thing = node.getThing(id);
                results.add(thing);
                successCount++;
            } catch(Exception e) {
                logger.debug(String.format("Could not read thing with %d from %s", id, node.getUrl()));
            }
        }
        if (successCount < readConsistency) {
            throw new Exception(String.format("Unable to successfully read from enough nodes. Read from %d nodes", successCount));
        }
        Thing result = resolver.resolve(results);
        repairer.repair(nodes, result);
        return result;
    }
}
