Part of the SPA 2015 Distributed Databases session.
Start here if you'd like to do the workshop in Java.
This project will set you up with all the boring
code that talks to a node via its REST API. At the
moment this code can only talk to one node, it's
your job to make it work with multiple, distributed
nodes.

## Building
Build with Maven. You can create an executable jar
containing all dependencies with the `assembly:single`
target, for example:

    mvn clean compile assembly:single

## Running
First start up at least one node node with:

    diy-dist-db-node 8080

(Use different ports for different nodes.)

Run the exectuable jar with:

    java -jar target/diy-dist-db-1.0-SNAPSHOT-jar-with-dependencies.jar

## Writing code
There's two ways of approaching this:

1. Stick with this one project, and write your
   code in `src/main/java/com/github/seeemilyplay/diydistdb/Main.java`.

2. Run `maven install` and use this code as a library
   in another project. Perhaps you want to write Clojure code
   for example.

## What's here 
All code is in the `src/main/java/com/github/seeemilyplay/diydistdb`
directory. There are three classes:

1. `Thing.java`: A POJO representing the Things stored in the db.
2. `Node.java`: A wrapper around the node's REST API that deals with the HTTP calls for you.
3. `Main.java`: A Main method that demonstrates how to use the
   other classes, and has placeholders for where to put your
   own code.
