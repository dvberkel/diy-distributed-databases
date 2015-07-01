package com.github.seeemilyplay.diydistdb;

import java.util.ArrayList;
import java.util.List;

/**
 * A Main method you can use as a stub.
 */
public class DatabaseClient {
    public static void main( String[] args ) throws Exception {
        Node[] nodes = new Node[]{
                new Node("http://localhost:8080"),
                new Node("http://localhost:8081"),
                new Node("http://localhost:8082")
        };
        DatabaseClient databaseClient = new DatabaseClient(new ReturnMostRecent(), nodes, 2, 2);

        databaseClient.write(new Thing(3, "foo"));
        databaseClient.write(new Thing(7, "bar"));
        Thing thing3 = databaseClient.read(3);
        Thing thing7 = databaseClient.read(7);
        System.out.println(thing3);
        System.out.println(thing7);
    }

    private final Resolver resolver;
    private final Node[] nodes;
    private final int writeConsistency;
    private final int readConsistency;

    public DatabaseClient(Resolver resolver, Node[] nodes, int writeConsistency, int readConsistency) {
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
                System.out.printf("Could not write thing %s to %s\n", thing, node.getUrl());

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
                System.out.printf("Could not read thing with %d from %s\n", id, node.getUrl());
            }
        }
        if (successCount < readConsistency) {
            throw new Exception(String.format("Unable to successfully read from enough nodes. Read from %d nodes", successCount));
        }
        return resolver.resolve(results);
    }
}
