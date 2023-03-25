package com.task.classes;

import com.task.enums.Tariff;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Generator {
    /**
     * This method gets a BufferedReader of file for line-by-line reading. If existing subscriber is faced (we define
     * it by number) - new Call instance is creating for this subscriber, otherwise new instance of Subscriber is
     * creating and the first Call instance for this subscriber is creating.
     *
     * @param reader gets BufferedReader of file
     * @throws IOException for line reading from file
     */
    private void parseFile(BufferedReader reader) throws IOException {
        long startTime = System.nanoTime();
        String line = reader.readLine();
        while(line != null){
            line = line.replaceAll(" ", "");
            String[] tokens = line.split(",");
            if(Subscriber.subscribers.containsKey(tokens[1])){
                Subscriber.subscribers.get(tokens[1]).addCall(tokens[0], tokens[2], tokens[3]);
            } else {
                Subscriber sub = new Subscriber(tokens[1], Tariff.getTariffByCode(tokens[4]));
                sub.addCall(tokens[0], tokens[2], tokens[3]);
                Subscriber.subscribers.put(sub.getNumber(), sub);
            }
            line = reader.readLine();
        }
        double elapsedTime = (System.nanoTime() - startTime) / 1e6;
        System.out.println(elapsedTime + " ms");
    }
    private void createReports(){

    }
    /**
     * Method to execute generator and create reports.
     *
     * @param fileName the file's path
     */
    public void execute(String fileName){
        try (FileReader fr = new FileReader(fileName); BufferedReader reader = new BufferedReader(fr)){
            parseFile(reader);
            // createReports();
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
