package com.server.request;

import com.exception.ServerException;
import com.server.resourceprovider.ResourceReader;
import com.server.response.ResponseWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class RequestHandler {
    private final String webAppPath;
    private final BufferedReader socketReader;
    private final BufferedWriter socketWriter;

    public RequestHandler(BufferedWriter socketWriter, BufferedReader socketReader, String webAppPath) {
        this.socketWriter = socketWriter;
        this.socketReader = socketReader;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        try {
            Request request = RequestParser.parse(socketReader);
            ResourceReader resourceReader = new ResourceReader(webAppPath);
            String content = resourceReader.readResource(request.getUri());

            ResponseWriter.writeOk(content, socketWriter);
        } catch (ServerException e) {
            ResponseWriter.writeError(socketWriter, e.getStatusCode());
        }
    }
}
