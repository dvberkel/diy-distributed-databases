package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import com.github.seeemilyplay.diydistdb.model.Thing;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandTest {
    public static final int ANY_ID = 123;
    private static final String ANY_STRING = "foo";
    private static final long ANY_TIMESTAMP = 0L;

    @Test
    public void getCommandShouldCallWriteOnDatabaseClient() throws Exception {
        DatabaseClient client = mock(DatabaseClient.class);
        Command command = new GetCommand(ANY_ID);

        command.executeOn(client);

        verify(client).read(ANY_ID);
    }

    @Test
    public void putCommandShouldCallReadOnDatabaseClient() throws Exception {
        DatabaseClient client = mock(DatabaseClient.class);
        Thing anyThing = new Thing(ANY_ID, ANY_STRING, ANY_TIMESTAMP);
        Command command = new PutCommand(anyThing);

        command.executeOn(client);

        verify(client).write(anyThing);
    }
}
