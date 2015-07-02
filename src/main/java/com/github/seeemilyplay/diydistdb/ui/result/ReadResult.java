package com.github.seeemilyplay.diydistdb.ui.result;

import com.github.seeemilyplay.diydistdb.model.Thing;

public class ReadResult implements Result {
    private final Thing thing;

    public ReadResult(Thing thing) {
        this.thing = thing;
    }
}
