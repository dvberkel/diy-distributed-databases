package com.github.seeemilyplay.diydistdb.ui;

public class NOOPCommand implements Command {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;

    }

    @Override
    public int hashCode() {
        return 31;
    }
}
