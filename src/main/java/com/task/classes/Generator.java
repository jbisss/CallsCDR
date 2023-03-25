package com.task.classes;

import java.io.*;

public class Generator {
    private void parseFile(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        while(line != null){
            line = line.replaceAll(" ", "");
            String[] tokens = line.split(",");

            line = reader.readLine();
        }
    }
    /**
     * Method to execute generator and create reports
     *
     * @param fileName the file's path
     */
    public void execute(String fileName){
        try (FileReader fr = new FileReader(fileName); BufferedReader reader = new BufferedReader(fr)){
            parseFile(reader);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
