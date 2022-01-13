package com.server;

import com.server.request.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String webAppPath;
    private int port;
    public void setPort(int port) {
        this.port = port;
    }

    public void setWebAppPath(String path) {
        webAppPath = path;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    RequestHandler requestHandler = new RequestHandler(writer, reader, webAppPath);
                    requestHandler.handle();
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
