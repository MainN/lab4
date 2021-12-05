package ru.rsatu.packagemain;

import ru.rsatu.packageone.ClassOne;
import ru.rsatu.packagetwo.ClassTwo;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        ClassLoaderFirst clFirst = new ClassLoaderFirst("out/production/ClassLoaderLab/");

        Class cFirst;

        try {
            cFirst = clFirst.findClass("ru.rsatu.packageone.ClassOne");
            System.out.println("ClassLoader ClassOne = " + cFirst.getClassLoader().toString());

        } catch (SecurityException | IllegalArgumentException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
            return;
        }
        System.out.println("ClassLoader ClassTwo = " + ClassTwo.class.getClassLoader());
    }
}
