package com.github.seeemilyplay.diydistdb.ui;

import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.ui.command.Command;
import com.github.seeemilyplay.diydistdb.ui.command.GetCommand;
import com.github.seeemilyplay.diydistdb.ui.command.NOOPCommand;
import com.github.seeemilyplay.diydistdb.ui.command.PutCommand;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser {

    public Command parse(String line) {
        List<CommandOption> options = Arrays.asList(
                new Get(line),
                new Put(line)
        );
        for (CommandOption option : options) {
            if (option.applies()) {
                return option.command();
            }
        }
        return new NOOPCommand();
    }
}

interface CommandOption {
    boolean applies();
    Command command();
}

abstract class AbstractCommandOption implements CommandOption {
    protected final Matcher matcher;

    public AbstractCommandOption(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public boolean applies() {
        return matcher.matches();
    }
}

class Get extends AbstractCommandOption {
    private static final Pattern GET_PATTERN = Pattern.compile("get\\s+(\\d+)", Pattern.CASE_INSENSITIVE);

    public Get(String line) {
        super(GET_PATTERN.matcher(line));
    }

    @Override
    public Command command() {
        return new GetCommand(Integer.valueOf(matcher.group(1)));
    }
}

class Put extends AbstractCommandOption {
    private static final Pattern PUT_PATTERN = Pattern.compile("put\\s+(\\d+)\\s+(\\w+)", Pattern.CASE_INSENSITIVE);

    public Put(String line) {
        super(PUT_PATTERN.matcher(line));
    }

    @Override
    public Command command() {
        return new PutCommand(new Thing(Integer.valueOf(matcher.group(1)), matcher.group(2)));
    }
}