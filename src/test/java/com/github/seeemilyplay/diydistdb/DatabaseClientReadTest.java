package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.connection.Node;
import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.repair.Repairer;
import com.github.seeemilyplay.diydistdb.resolve.ReturnFirst;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseClientReadTest {
    public static final int ANY_ID = 1;
    private ReturnFirst resolver;

    @Before
    public void createAResolver() {
        resolver = new ReturnFirst();
    }

    @Test
    public void shouldSuccessfullyReadThing() throws Exception {
        Thing thing = new Thing(1, "foo", 0L);
        Node node1 = mockedNodeThatReturns(thing);
        Node node2 = mockedNodeThatReturns(thing);
        Node node3 = mockedNodeThatReturns(thing);

        DatabaseClient databaseClient = new DatabaseClient(mock(Logger.class), mock(Repairer.class), resolver, new Node[]{ node1, node2, node3 }, 3, 2);

        assertThat(databaseClient.read(ANY_ID), is(thing));
    }

    @Test
    public void shouldSuccessfullyReadThingWithOneOfThreeNodesDownAndReadConsistencyTwo() throws Exception {
        Thing thing = new Thing(1, "foo", 0L);
        Node node1 = mockedNodeThatReturns(thing);
        Node node2 = mockedNodeThatThrowsException();
        Node node3 = mockedNodeThatReturns(thing);

        DatabaseClient databaseClient = new DatabaseClient(mock(Logger.class), mock(Repairer.class), resolver, new Node[]{ node1, node2, node3 }, 3, 2);

        assertThat(databaseClient.read(ANY_ID), is(thing));
    }

    @Test(expected = Exception.class)
    public void shouldThrowExceptionWhenTwoOfThreeNodesDownAndReadConsistencyTwo() throws Exception {
        Thing thing = new Thing(1, "foo", 0L);
        Node node1 = mockedNodeThatThrowsException();
        Node node2 = mockedNodeThatThrowsException();
        Node node3 = mockedNodeThatReturns(thing);

        DatabaseClient databaseClient = new DatabaseClient(mock(Logger.class), mock(Repairer.class), resolver, new Node[]{ node1, node2, node3 }, 3, 2);
        databaseClient.read(ANY_ID);
    }

    private Node mockedNodeThatReturns(Thing thing) throws Exception {
        Node node = mock(Node.class);
        when(node.getThing(ANY_ID)).thenReturn(thing);
        return node;
    }

    private Node mockedNodeThatThrowsException() throws Exception {
        Node node = mock(Node.class);
        when(node.getThing(ANY_ID)).thenThrow(new Exception("thrown by a mock"));
        return node;
    }
}
