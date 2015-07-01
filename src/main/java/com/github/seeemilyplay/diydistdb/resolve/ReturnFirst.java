package com.github.seeemilyplay.diydistdb.resolve;

import com.github.seeemilyplay.diydistdb.Thing;
import com.github.seeemilyplay.diydistdb.resolve.Resolver;

import java.util.List;

public class ReturnFirst implements Resolver {
    public Thing resolve(List<Thing> things) {
        return things.get(0);
    }
}
