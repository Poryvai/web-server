package com.server.resourceprovider;

import com.exception.ServerException;

import java.io.*;
import java.util.StringJoiner;

import static com.server.request.StatusCode.NOT_FOUND;


public class ResourceReader {
    private final String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public String readResource(String uri) throws IOException {
        try(BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(webAppPath + uri)))){
            StringJoiner result = new StringJoiner("\n");
            fileReader.lines().forEach(result::add);
            return result.toString();
        } catch (FileNotFoundException e) {
            throw new ServerException(NOT_FOUND);
        }


    }
}
