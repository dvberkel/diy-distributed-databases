package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CommandTest {
    public static final int ANY_ID = 123;

    @Test
    public void getCommandShouldCallWriteOnDatabaseClient() throws Exception {
        DatabaseClient client = mock(DatabaseClient.class);
        Command command = new GetCommand(ANY_ID);

        command.executeOn(client);

        verify(client).read(ANY_ID);
    }
}
