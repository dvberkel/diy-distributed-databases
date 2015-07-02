package com.github.seeemilyplay.diydistdb.ui.result;

public class NOOPResult implements Result {
    @Override
    public void report() {
        System.out.println("could not execute that command");
    }
}
