package com.github.seeemilyplay.diydistdb.ui.command;

import com.github.seeemilyplay.diydistdb.DatabaseClient;
import com.github.seeemilyplay.diydistdb.model.Thing;
import com.github.seeemilyplay.diydistdb.ui.result.Result;
import com.github.seeemilyplay.diydistdb.ui.result.WriteResult;

public class PutCommand implements Command {
    private final Thing thing;

    public PutCommand(Thing thing) {
        this.thing = thing;
    }

    @Override
    public Result executeOn(DatabaseClient client) throws Exception {
        client.write(thing);
        return new WriteResult(thing);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PutCommand that = (PutCommand) o;

        return thing.getId() == that.thing.getId() && thing.getValue().equals(that.thing.getValue());

    }

    @Override
    public int hashCode() {
        return thing.hashCode();
    }

    @Override
    public String toString() {
        return String.format("PutCommand{thing=%s}", thing);
    }
}
