package com.fyang.servlet.http;

import lombok.Data;

import java.io.IOException;
import java.io.OutputStream;

@Data
public class Response {
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content) throws IOException {
        StringBuilder httpResponse = new StringBuilder();
        httpResponse.append("Http/1.1 200 OK\n");
        httpResponse.append("Content-Type: application/json;charset=utf-8\n");
        httpResponse.append("\r\n");
        httpResponse.append(content);

        outputStream.write(httpResponse.toString().getBytes());
        outputStream.flush();
        outputStream.close();
    }

}
