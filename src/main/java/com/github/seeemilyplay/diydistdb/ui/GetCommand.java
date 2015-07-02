package com.github.seeemilyplay.diydistdb.ui;

public class GetCommand implements Command {
    private final int id;

    public GetCommand(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GetCommand that = (GetCommand) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("GetCommand{id=%d})", id);
    }
}
