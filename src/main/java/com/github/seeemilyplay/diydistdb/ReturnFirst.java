package com.github.seeemilyplay.diydistdb;

import java.util.List;

public class ReturnFirst implements Resolver {
    public Thing resolve(List<Thing> things) {
        return things.get(0);
    }
}
