package com.github.seeemilyplay.diydistdb.resolve;

import com.github.seeemilyplay.diydistdb.Thing;
import com.github.seeemilyplay.diydistdb.resolve.Resolver;
import com.github.seeemilyplay.diydistdb.resolve.ReturnMostRecent;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ReturnMostRecentTest {
    @Test
    public void shouldResolveTheMostRecentThing() {
        Resolver resolver = new ReturnMostRecent();
        Thing expectedThing = new Thing(0, "baz", Long.MAX_VALUE);

        Thing resolvedThing = resolver.resolve(Arrays.asList(
                new Thing(0, "foo", 0L),
                new Thing(1, "bar", 1L),
                expectedThing
        ));

        assertThat(resolvedThing, is(expectedThing));

    }
}
