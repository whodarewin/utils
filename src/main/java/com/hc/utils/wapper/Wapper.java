package com.hc.utils.wapper;

import java.util.function.Function;

public class Wapper {

    public void doThing(Function function){
        System.out.println("before");
        function.apply("t");
        System.out.println("end");
    }

    public String test(String out,String b){
        System.out.println(out);
        return out;
    }

    public static void main(String[] args){
        Wapper wapper = new Wapper();
        String a = "a";
        String b = "b";
        wapper.doThing(o -> wapper.test(a,b));
    }
}
