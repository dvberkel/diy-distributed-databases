package com.github.seeemilyplay.diydistdb.repair;

import com.github.seeemilyplay.diydistdb.Node;
import com.github.seeemilyplay.diydistdb.Thing;

public interface Repairer {
    void repair(Node[] nodes, Thing thing);
}
