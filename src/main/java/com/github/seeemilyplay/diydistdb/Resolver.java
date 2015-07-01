package com.github.seeemilyplay.diydistdb;

import java.util.List;

public interface Resolver {
    Thing resolve(List<Thing> things);
}
