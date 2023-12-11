package org.example.api;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class ReadFile {
    public static String Read(String fileName){
        String content = null;
        try{
            content = Files.readString(Path.of("/home/chill/Documents/Projects/testgl/src/main/resources/shaderfiles/"+fileName));
            System.out.println(content);
        }catch(Exception e){
            System.out.println(e);
        }
        return content;
    }
}
