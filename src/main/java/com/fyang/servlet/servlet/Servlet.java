package com.fyang.servlet.servlet;

import com.fyang.servlet.http.Request;
import com.fyang.servlet.http.Response;

public abstract class Servlet {
    public abstract void doGet(Request request, Response response);

    public abstract void doPost(Request request,Response response);

    public void service(Request request,Response response){
        if ("get".equalsIgnoreCase(request.getMethod())) {
            this.doGet(request, response);
        } else {
            this.doPost(request, response);
        }
    }

}
