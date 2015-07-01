package com.github.seeemilyplay.diydistdb;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class AlwaysRepairTest {
    @Test
    public void shouldCallRepairOnAllNodesPassedIn() throws Exception {
        Repairer repairer = new AlwaysRepair(mock(Logger.class));
        Node node1 = mock(Node.class);
        Node node2 = mock(Node.class);
        Thing thing = mock(Thing.class);

        repairer.repair(new Node[]{node1, node2}, thing);

        verify(node1).putThing(thing);
        verify(node2).putThing(thing);
    }
}
