package com.server.request;


import com.exception.ServerException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.server.request.StatusCode.*;

public class RequestParser {
    public static Request parse(BufferedReader reader) throws IOException {
        Request request = new Request();

        String firstLine = reader.readLine();
        injectUriAndMethod(firstLine, request);
        injectHeaders(reader, request);

        return request;
    }

    static void injectUriAndMethod(String line, Request request) {
        if (Objects.isNull(line)) {
            throw new ServerException(BAD_REQUEST);
        }

        String uri = line.substring(line.indexOf("/"), line.indexOf(" HTTP"));
        request.setUri(uri);

        String httpMethodValue = line.substring(0, line.indexOf(" "));
        HttpMethod httpMethod = HttpMethod.valueOf(httpMethodValue);
        if (httpMethod == HttpMethod.POST) {
            throw new ServerException(METHOD_NOT_ALLOWED);
        }
        request.setMethod(httpMethod);
    }

    private static void injectHeaders(BufferedReader reader, Request request) throws IOException {
        Map<String, String> headers = new HashMap<>();
        while (true) {
            String line = reader.readLine();
            if (Objects.isNull(line) || line.isBlank()) {
                break;
            }

            int separateIndex = line.indexOf(":");
            String headerName = line.substring(0, separateIndex);
            String headerContent = line.substring(separateIndex + 1).trim();

            headers.put(headerName, headerContent);
        }
        request.setHeaders(headers);
    }
}
