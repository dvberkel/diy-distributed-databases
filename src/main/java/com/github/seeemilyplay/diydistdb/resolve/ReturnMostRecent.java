package com.github.seeemilyplay.diydistdb.resolve;

import com.github.seeemilyplay.diydistdb.Thing;
import com.github.seeemilyplay.diydistdb.resolve.Resolver;

import java.util.List;

public class ReturnMostRecent implements Resolver {
    public Thing resolve(List<Thing> things) {
        Thing mostRecent = things.get(0);
        for (Thing candidate: things) {
            if (mostRecent.getTimestamp() < candidate.getTimestamp()) {
                mostRecent = candidate;
            }
        }
        return mostRecent;
    }
}
