package com.task.classes;

import com.task.enums.Tariff;

import java.io.*;

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
        while (line != null) {
            line = line.replaceAll(" ", "");
            String[] tokens = line.split(",");
            if (Subscriber.subscribers.containsKey(tokens[1])) {
                Subscriber.subscribers.get(tokens[1]).addCall(tokens[0], tokens[2], tokens[3]);
            } else {
                Subscriber sub = new Subscriber(tokens[1], Tariff.getTariffByCode(tokens[4]));
                sub.addCall(tokens[0], tokens[2], tokens[3]);
                Subscriber.subscribers.put(sub.getNumber(), sub);
            }
            line = reader.readLine();
        }
        for (String key : Subscriber.subscribers.keySet()) {
            Subscriber.subscribers.get(key).countCallsCost();
        }
        double elapsedTime = (System.nanoTime() - startTime) / 1e6;
        System.out.println(elapsedTime + " ms");
    }

    public void createReports() {
        for (String key : Subscriber.subscribers.keySet()) {
            File file = null;
            try {
                String fileName = Subscriber.subscribers.get(key).getNumber() + ".txt";
                String path = "D:\\JavaProjects\\CallsCDR\\src\\main\\reports\\" + fileName;
                file = new File(path);
                if (file.createNewFile()) {
                    System.out.println("File created!");
                } else {
                    System.out.println("File exists!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                assert file != null;
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(String.valueOf(Subscriber.subscribers.get(key)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to execute generator and create reports.
     *
     * @param fileName the file's path
     */
    public void execute(String fileName) {
        try (FileReader fr = new FileReader(fileName); BufferedReader reader = new BufferedReader(fr)) {
            parseFile(reader);
            createReports();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
