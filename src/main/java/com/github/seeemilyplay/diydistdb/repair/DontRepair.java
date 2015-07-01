package com.github.seeemilyplay.diydistdb.repair;

import com.github.seeemilyplay.diydistdb.connection.Node;
import com.github.seeemilyplay.diydistdb.model.Thing;

public class DontRepair implements Repairer {
    @Override
    public void repair(Node[] nodes, Thing thing) {
        /* do nothing */
    }
}
