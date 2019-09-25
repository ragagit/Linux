package com.raga.main;

import com.raga.httpjson.HttpURLConnectionExample;

public class HttpJsonExample {

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

    }
}
