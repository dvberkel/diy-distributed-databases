package com.github.seeemilyplay.diydistdb.ui.result;

import com.github.seeemilyplay.diydistdb.model.Thing;

public class WriteResult implements Result {
    private final Thing thing;

    public WriteResult(Thing thing) {
        this.thing = thing;
    }

    @Override
    public void report() {
        System.out.printf("Successfully wrote %s\n", thing);
    }
}
