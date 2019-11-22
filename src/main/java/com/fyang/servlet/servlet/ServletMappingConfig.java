package com.fyang.servlet.servlet;

import java.util.ArrayList;
import java.util.List;

public class ServletMappingConfig {
    private static List<ServletMapping> servletMappingList = new ArrayList<ServletMapping>();

    static {
        servletMappingList.add(new ServletMapping("/hellWorld","com.fyang.servlet.servlet.MyServlet"));
    }
}
