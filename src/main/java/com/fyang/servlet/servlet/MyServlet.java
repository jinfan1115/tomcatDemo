package com.fyang.servlet.servlet;

import com.fyang.servlet.http.Request;
import com.fyang.servlet.http.Response;

import java.io.IOException;

public class MyServlet extends Servlet {
    @Override
    public void doGet(Request request, Response response) {
        try {
            response.write("这是一个GET请求测试方法");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(Request request, Response response) {
        try {
            response.write("这是一个POST请求测试方法");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
