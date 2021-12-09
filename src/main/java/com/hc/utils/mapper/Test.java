package com.hc.utils.mapper;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Test {
    public void test(@Nonnull String test){
        System.out.println(test);
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture future = new CompletableFuture();
        future.complete("done");
        System.out.println(future.get());
        new Test().test(null);
    }
}
