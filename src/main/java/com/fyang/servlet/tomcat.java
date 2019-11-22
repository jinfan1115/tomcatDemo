package com.fyang.servlet;

import com.fyang.servlet.http.Request;
import com.fyang.servlet.http.Response;
import com.fyang.servlet.servlet.Servlet;
import lombok.Data;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
public class tomcat {
    private int port  = 8086;
    private static final Properties properties = new Properties();
    private Map<String, String> servletMap = new HashMap<String, String>();

    public tomcat(int port) {
        this.port = port;
    }

    private void start() {
        initServletMap();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Tomcat is start，port：" + port);
            while (true){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                Request request = new Request(inputStream);
                Response response = new Response(outputStream);

                dispath(request,response);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initServletMap() {
        try {
            String bastPath = tomcat.class.getResource("/").getPath();
            InputStream inputStream = new FileInputStream(bastPath + "web.properties");
            properties.load(inputStream);
            inputStream.close();

            Set<Object> keys = properties.keySet();
            for (Object key:keys) {
                if(key.toString().contains("url")){
                    System.out.println("读取到配置文件："+key.toString() + "===" + properties.get(key));
                    Object classname = properties.get(key.toString().replace("url", "clazz"));
                    servletMap.put(properties.get(key.toString()).toString(),classname.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispath(Request request, Response response) {
        String clazz = servletMap.get(request.getUrl());

        if(clazz != null){
            try {
                Class<Servlet> servletClass = (Class<Servlet>) Class.forName(clazz);
                Servlet myServlet = servletClass.newInstance();

                myServlet.service(request,response);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new tomcat(8086).start();
    }
}
