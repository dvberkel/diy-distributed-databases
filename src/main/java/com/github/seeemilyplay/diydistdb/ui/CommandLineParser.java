package com.github.seeemilyplay.diydistdb.ui;

import com.github.seeemilyplay.diydistdb.model.Thing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser {
    private static final Pattern GET_PATTERN = Pattern.compile("get\\s+(\\d+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern PUT_PATTERN = Pattern.compile("put\\s+(\\d+)\\s+(\\w+)", Pattern.CASE_INSENSITIVE);

    public Command parse(String line) {
        Matcher matcher1 = GET_PATTERN.matcher(line);
        Matcher matcher2 = PUT_PATTERN.matcher(line);
        if (matcher1.matches()) {
            return new GetCommand(Integer.valueOf(matcher1.group(1)));
        }
        if (matcher2.matches()) {
            return new PutCommand(new Thing(Integer.valueOf(matcher2.group(1)), matcher2.group(2)));
        }
        return null;
    }
}
