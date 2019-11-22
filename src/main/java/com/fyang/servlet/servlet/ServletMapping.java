package com.fyang.servlet.servlet;

import lombok.Data;

@Data
public class ServletMapping {
    private String url;
    private String clazz;

    public ServletMapping(String url, String clazz) {
        this.url = url;
        this.clazz = clazz;
    }
}
