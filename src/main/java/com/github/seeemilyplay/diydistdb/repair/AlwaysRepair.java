package com.github.seeemilyplay.diydistdb.repair;

import com.github.seeemilyplay.diydistdb.Node;
import com.github.seeemilyplay.diydistdb.Thing;
import org.apache.logging.log4j.Logger;

public class AlwaysRepair implements Repairer {
    private final Logger logger;

    public AlwaysRepair(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void repair(Node[] nodes, Thing thing) {
        for (Node node : nodes) {
            try {
                logger.debug(String.format("repairing %s on %s", thing, node.getUrl()));
                node.putThing(thing);
            } catch (Exception e) {
                logger.debug(String.format("could not repair %s on %s", thing, node.getUrl()));
            }
        }

    }
}
