package com.github.seeemilyplay.diydistdb.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandLineParser {
    private static final Pattern GET_PATTERN = Pattern.compile("get\\s+(\\d+)", Pattern.CASE_INSENSITIVE);
    public Command parse(String line) {
        Matcher matcher = GET_PATTERN.matcher(line);
        if (matcher.matches()) {
            return new GetCommand(Integer.valueOf(matcher.group(1)));
        }
        return null;
    }
}
