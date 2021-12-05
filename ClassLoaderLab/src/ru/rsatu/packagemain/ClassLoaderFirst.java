package ru.rsatu.packagemain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class ClassLoaderFirst extends ClassLoader {
    private final HashMap<String, Class<?> > cash;
    private String pathToClasses = "";
    
    ClassLoaderFirst(String path) {
        cash = new HashMap<>();
        pathToClasses = path;
    }
    
    @Override
    public Class<?> findClass(String name) {
        if (cash.containsKey(name))
            return cash.get(name);
        File classFile = new File(pathToClasses + name.replace('.', '/') + ".class");
        if (!classFile.exists() | !classFile.canRead())
            try {
                throw new FileNotFoundException();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        InputStream classFileStream = null;
        try {
            classFileStream = new FileInputStream(classFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            loadClassData(name, classFileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return cash.get(name);
    }   
    
    private void loadClassData(String name, InputStream classFileStream) throws IOException {        
        ByteBuffer b = ByteBuffer.wrap(classFileStream.readAllBytes());
        cash.put(name, defineClass(name, b, null));
    }   
}
