package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import com.github.seeemilyplay.diydistdb.ui.command.Command;
import com.github.seeemilyplay.diydistdb.ui.result.NOOPResult;
import com.github.seeemilyplay.diydistdb.ui.result.Result;

public class NOOPCommand implements Command {
    @Override
    public Result executeOn(DatabaseClient client) throws Exception {
        return new NOOPResult();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;

    }

    @Override
    public int hashCode() {
        return 31;
    }
}
