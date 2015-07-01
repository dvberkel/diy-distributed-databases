package com.github.seeemilyplay.diydistdb.resolve;

import com.github.seeemilyplay.diydistdb.model.Thing;

import java.util.List;

public interface Resolver {
    Thing resolve(List<Thing> things);
}
