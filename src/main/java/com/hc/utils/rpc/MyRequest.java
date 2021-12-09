package com.hc.utils.rpc;

import java.io.Serializable;

public class MyRequest implements Serializable {
    private static final long serialVersionUID = -7242884346498114969L;
    private String req;

    public String getReq() {
        return req;
    }

    public void setReq(String req) {
        this.req = req;
    }

    @Override
    public String toString() {
        return req;
    }
}
