package com.github.seeemilyplay.diydistdb;

/**
 * A Main method you can use as a stub.
 */
public class Main {
    public static void main( String[] args ) throws Exception {
        String[] nodeUrls = new String[]{
                "http://localhost:8080",
                "http://localhost:8081",
                "http://localhost:8082"
        };
        Main main = new Main(2);

        main.write(nodeUrls, new Thing(3, "foo"));
        main.write(nodeUrls, new Thing(7, "bar"));
        Thing thing3 = main.read(nodeUrls, 3);
        Thing thing7 = main.read(nodeUrls, 7);
        System.out.println(thing3);
        System.out.println(thing7);
    }

    private final int writeConsistency;

    public Main(int writeConsistency) {
        this.writeConsistency = writeConsistency;
    }

    public void write(String[] nodeUrls, Thing thing) throws Exception {
        int successCount = 0;
        for(String nodeUrl: nodeUrls) {
            try {
                Node node = new Node(nodeUrl);
                node.putThing(thing);
                successCount++;
            } catch(Exception e) {
                System.out.printf("Could not write thing %s to %s\n", thing, nodeUrl);

            }
        }
        if (successCount < writeConsistency) {
            throw new Exception(String.format("Unable to succesfully write to enough nodes. Wrote to %d nodes", successCount));
        }
    }


    public Thing read(String[] nodeUrls, int id) throws Exception {
        //todo: only works with one node, need to make distributed!
        Node node = new Node(nodeUrls[0]);
        return node.getThing(id);
    }
}
