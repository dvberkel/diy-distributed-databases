package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.ui.result.ReadResult;
import com.github.seeemilyplay.diydistdb.ui.result.Result;

public class GetCommand implements Command {
    private final int id;

    public GetCommand(int id) {
        this.id = id;
    }

    @Override
    public Result executeOn(DatabaseClient client) throws Exception {
        Thing thing = client.read(id);
        return new ReadResult(thing);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetCommand that = (GetCommand) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("GetCommand{id=%d})", id);
    }
}
