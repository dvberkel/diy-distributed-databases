package com.github.seeemilyplay.diydistdb;

import com.github.seeemilyplay.diydistdb.repair.Repairer;
import com.github.seeemilyplay.diydistdb.resolve.ReturnFirst;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class DatabaseClientWriteTest {
    private static final Thing ANY_THING = new Thing(1, "foo", 0L);
    private ReturnFirst resolver;

    @Before
    public void createAResolver() {
        resolver = new ReturnFirst();
    }

    @Test
    public void shouldSuccessfullyWriteThing() throws Exception {
        Node node1 = mockedNodeThatWrites();
        Node node2 = mockedNodeThatWrites();
        Node node3 = mockedNodeThatWrites();

        DatabaseClient databaseClient = new DatabaseClient(mock(Logger.class), mock(Repairer.class), resolver, new Node[]{ node1, node2, node3 }, 3, 2);
        databaseClient.write(ANY_THING);

        verify(node1).putThing(ANY_THING);
        verify(node2).putThing(ANY_THING);
        verify(node3).putThing(ANY_THING);
    }

    @Test(expected = Exception.class)
    public void shouldThrowWhenOneOfThreeNodesDownAndWriteConsistencyThree() throws Exception {
        Thing thing = new Thing(1, "foo", 0L);
        Node node1 = mockedNodeThatWrites();
        Node node2 = mockedNodeThatThrowsException();
        Node node3 = mockedNodeThatWrites();

        DatabaseClient databaseClient = new DatabaseClient(mock(Logger.class), mock(Repairer.class), resolver, new Node[]{ node1, node2, node3 }, 3, 2);
        databaseClient.write(ANY_THING);
    }

    private Node mockedNodeThatWrites() throws Exception {
        Node node = mock(Node.class);
        return node;
    }

    private Node mockedNodeThatThrowsException() throws Exception {
        Node node = mock(Node.class);
        when(node.putThing(any(Thing.class))).thenThrow(new Exception("thrown by a mock"));
        return node;
    }
}
