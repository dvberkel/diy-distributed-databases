package com.github.seeemilyplay.diydistdb.ui.result;

import com.github.seeemilyplay.diydistdb.model.Thing;

public class ReadResult implements Result {
    private final Thing thing;

    public ReadResult(Thing thing) {
        this.thing = thing;
    }

    @Override
    public void report() {
        System.out.printf("Successfully read %s\n", thing);
    }
}
