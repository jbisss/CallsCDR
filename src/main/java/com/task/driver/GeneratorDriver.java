package com.task.driver;

import com.task.classes.Generator;

public class GeneratorDriver {
    /**
     * Main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.execute(".\\src\\main\\resources\\cdr.txt");
    }
}
