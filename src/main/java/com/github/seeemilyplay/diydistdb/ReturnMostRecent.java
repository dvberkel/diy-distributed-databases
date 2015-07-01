package com.github.seeemilyplay.diydistdb;

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
