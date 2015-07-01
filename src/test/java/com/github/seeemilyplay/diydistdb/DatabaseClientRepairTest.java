package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.repair.Repairer;
import com.github.seeemilyplay.diydistdb.resolve.ReturnFirst;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class DatabaseClientRepairTest {
    public static final int ANY_ID = 1;
    private ReturnFirst resolver;

    @Before
    public void createAResolver() {
        resolver = new ReturnFirst();
    }

    @Test
    public void shouldCallRepairerOnEachRead() throws Exception {
        Thing thing = new Thing(ANY_ID, "foo", Long.MAX_VALUE);
        Repairer repairer = mock(Repairer.class);
        Node node = mockedNodeThatReturns(thing);
        Node[] nodes = {node};
        DatabaseClient client = new DatabaseClient(mock(Logger.class), repairer, resolver, nodes, 3, 1);

        client.read(ANY_ID);

        verify(repairer).repair(nodes, thing);
    }

    private Node mockedNodeThatReturns(Thing thing) throws Exception {
        Node node = mock(Node.class);
        when(node.getThing(ANY_ID)).thenReturn(thing);
        return node;
    }

}
