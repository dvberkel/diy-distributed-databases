package com.github.seeemilyplay.diydistdb.ui;

import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.ui.command.Command;
import com.github.seeemilyplay.diydistdb.ui.command.GetCommand;
import com.github.seeemilyplay.diydistdb.ui.command.NOOPCommand;
import com.github.seeemilyplay.diydistdb.ui.command.PutCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CommandLineParserTest {
    private final String line;
    private final Command expectedCommand;

    public CommandLineParserTest(String line, Command expectedCommand) {
        this.line = line;
        this.expectedCommand = expectedCommand;
    }

    @Test
    public void shouldParseCommands() {
        CommandLineParser parser = new CommandLineParser();

        Command command = parser.parse(line);

        assertThat(command, is(expectedCommand));
    }

    @Parameterized.Parameters(name = "should parse \"{1}\"")
    public static Collection<Object[]> data() {
        List<Object[]> data = new ArrayList<Object[]>();
        data.add(new Object[]{ "get 123", new GetCommand(123)});
        data.add(new Object[]{ "GET 456", new GetCommand(456)});
        data.add(new Object[]{ "put 789 foo", new PutCommand(new Thing(789, "foo"))});
        data.add(new Object[]{ "PUT 12 BAR", new PutCommand(new Thing(12, "BAR"))});
        data.add(new Object[]{ "unknown command", new NOOPCommand()});
        return data;
    }
}
