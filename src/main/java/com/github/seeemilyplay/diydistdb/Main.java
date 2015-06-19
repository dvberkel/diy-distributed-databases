package com.github.seeemilyplay.diydistdb;

import java.util.List;

/**
 * A Main method you can use as a stub.
 */
public class Main {
    public static void main( String[] args ) throws Exception {
        String[] nodeUrls = new String[]{"http://localhost:8080"};
        write(nodeUrls, new Thing(3, "foo"));
        write(nodeUrls, new Thing(7, "bar"));
        Thing thing3 = read(nodeUrls, 3);
        Thing thing7 = read(nodeUrls, 7);
        System.out.println(thing3);
        System.out.println(thing7);
    }

    public static void write(String[] nodeUrls, Thing thing) throws Exception {
        //todo: only works with one node, need to make distributed!
        Node.putThing(nodeUrls[0], thing);
    }

    public static Thing read(String[] nodeUrls, int id) throws Exception {
        //todo: only works with one node, need to make distributed!
        return Node.getThing(nodeUrls[0], id);
    }
}
