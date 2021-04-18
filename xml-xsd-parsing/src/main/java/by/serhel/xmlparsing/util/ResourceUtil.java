package by.serhel.xmlparsing.util;

import by.serhel.xmlparsing.exception.ResourceNotFoundException;

import java.net.URL;

public class ResourceUtil {
    public String getPathToResources(String path) throws ResourceNotFoundException {
        URL url = getClass().getClassLoader().getResource(path);
        if(url == null){
            throw new ResourceNotFoundException("Resource not found: " + path);
        }
        return url.getPath();
    }
}
