package com.github.seeemilyplay.diydistdb;

/**
 * A Main method you can use as a stub.
 */
public class Main {
    public static void main( String[] args ) throws Exception {
        Node[] nodes = new Node[]{
                new Node("http://localhost:8080"),
                new Node("http://localhost:8081"),
                new Node("http://localhost:8082")
        };
        Main main = new Main(nodes, 2);

        main.write(new Thing(3, "foo"));
        main.write(new Thing(7, "bar"));
        Thing thing3 = main.read(3);
        Thing thing7 = main.read(7);
        System.out.println(thing3);
        System.out.println(thing7);
    }

    private final Node[] nodes;
    private final int writeConsistency;

    public Main(Node[] nodes, int writeConsistency) {
        this.nodes = nodes;
        this.writeConsistency = writeConsistency;
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
            throw new Exception(String.format("Unable to succesfully write to enough nodes. Wrote to %d nodes", successCount));
        }
    }


    public Thing read(int id) throws Exception {
        //todo: only works with one node, need to make distributed!
        return nodes[0].getThing(id);
    }
}
