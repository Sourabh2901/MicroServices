package com.sourabh.restwebservices.payload;

import lombok.Data;
import lombok.ToString;

@Data @ToString
public class helloBean {
    String message;
    public helloBean(String msg) {
        this.message =  msg;
    }
}
