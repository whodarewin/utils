package com.hc.utils.procedure;

public enum TestState {
    FIRST(1),
    SECOND(2);

    int value;
    TestState(int value){
        this.value = value;
    }

    public static TestState getState(int i){
        if(i == 1){
            return FIRST;
        }else{
            return SECOND;
        }
    }
}
