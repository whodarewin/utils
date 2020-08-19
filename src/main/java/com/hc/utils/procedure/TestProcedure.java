package com.hc.utils.procedure;

import org.apache.hadoop.hbase.procedure2.*;

import java.io.IOException;

public class TestProcedure extends StateMachineProcedure<Object,TestState> {

    @Override
    protected Flow executeFromState(Object env, TestState testState) throws ProcedureSuspendedException, ProcedureYieldException, InterruptedException {
        if(testState.equals(TestState.FIRST)){
            System.out.println("exec state 1");
            setNextState(TestState.SECOND);
            return Flow.HAS_MORE_STATE;
        }else if(testState.equals(TestState.SECOND)){
            System.out.println("exec state 2");
            return Flow.NO_MORE_STATE;
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected void rollbackState(Object env, TestState testState) throws IOException, InterruptedException {
        if(testState.equals(TestState.FIRST)){
            System.out.println("rollback 1");

        }else if(testState.equals(TestState.SECOND)){
            System.out.println("rollback 2");

        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    protected TestState getState(int stateId) {
        return TestState.getState(stateId);
    }

    @Override
    protected int getStateId(TestState testState) {
        return testState.value;
    }

    @Override
    protected TestState getInitialState() {
        return TestState.FIRST;
    }
}
