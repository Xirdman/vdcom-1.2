package org.example.Threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class RewriterThread extends Thread {
    String threadName, fileName;
    int incrementNumber;
    Semaphore semaphore;

    public RewriterThread(String threadName, String fileName, int incrementNumber, Semaphore semaphore) {
        this.threadName = threadName;
        this.fileName = fileName;
        this.incrementNumber = incrementNumber;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            boolean flag = true;
            semaphore.acquire();
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            if (scanner.hasNextLine()) {
                int i = Integer.parseInt(scanner.nextLine());
                System.out.print("Поток с ID: " + threadName + " считал " + i + ". ");
                if (i < incrementNumber) {
                    FileWriter fileWriter = new FileWriter(fileName);
                    i++;
                    fileWriter.write("" + i);
                    fileWriter.flush();
                    fileWriter.close();
                    System.out.print("Поток с ID: " + threadName + " записал " + i + "\n");

                } else {
                    flag = false;
                    System.out.print("Содержимое файла " + fileName + " - " + incrementNumber + " \n");
                }
            }
            fileReader.close();
            semaphore.release();
            sleep(100);
            if (flag) {
                this.run();
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

