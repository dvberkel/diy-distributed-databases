package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import com.github.seeemilyplay.diydistdb.ui.result.Result;

public interface Command {
    Result executeOn(DatabaseClient client) throws Exception;
}
