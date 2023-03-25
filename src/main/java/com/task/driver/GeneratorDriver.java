package com.task.driver;

import com.task.classes.Generator;

public class GeneratorDriver {
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.execute("cdr.txt");
    }
}
