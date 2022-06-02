package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        String fileName = args[0];
        if(!fileName.endsWith(".txt")) {
            System.out.println("Please input a text(*.txt) file!");
            return;
        }

        try {
            List<String> names = Files.lines(Paths.get(fileName))
                    .filter( name -> name.length() > 0)
                    .sorted(Comparator.comparing((String name) -> (name.indexOf(",") > 0 ? name.substring(0, name.indexOf(",")).toUpperCase(Locale.ROOT) : name.toUpperCase(Locale.ROOT)))
                                        .thenComparing((String name) -> (name.indexOf(",") > 0 ? name.substring(name.indexOf(",")+1, name.length()-1).trim().toUpperCase(Locale.ROOT) : "")))
                    .collect(Collectors.toList());

            names.forEach(System.out::println);

            Path fileNameOut = Files.write(Paths.get(fileName.replace(".", "-sorted.")), names);
            System.out.println("Finished: created " + fileNameOut);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
