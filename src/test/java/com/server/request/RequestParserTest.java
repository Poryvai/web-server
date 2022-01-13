package com.server.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestParserTest {

    @Test
    void testInjectUriAndMethodSetsGetMethodToRequest() {
        Request request = new Request();
        String testLine = "GET /test HTTP/1.1";

        RequestParser.injectUriAndMethod(testLine, request);

        assertEquals(HttpMethod.GET, request.getMethod());
    }

    @Test
    void testInjectUriAndMethodSetsUriToRequest() {
        Request request = new Request();
        String testLine = "GET /test HTTP/1.1";

        RequestParser.injectUriAndMethod(testLine, request);

        assertEquals("/test", request.getUri());
    }

    @Test
    void testSetsEmptyUriToRequest() {
        Request request = new Request();
        String testLine = "GET / HTTP/1.1";

        RequestParser.injectUriAndMethod(testLine, request);

        assertEquals("/", request.getUri());
    }





}

