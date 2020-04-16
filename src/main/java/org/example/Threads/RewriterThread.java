package org.example.Threads;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class RewriterThread extends Thread {
    String fileName;
    int incrementNumber;
    Semaphore semaphore;

    public RewriterThread(String threadName, String fileName, int incrementNumber, Semaphore semaphore) {
        this.setName(threadName);
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
                System.out.print("Поток с ID: " + this.getName() + this.getId() + " считал " + i + ". ");
                if (i < incrementNumber) {
                    FileWriter fileWriter = new FileWriter(fileName);
                    i++;
                    fileWriter.write("" + i);
                    fileWriter.flush();
                    fileWriter.close();
                    System.out.print("Поток с ID: "+ this.getName() + this.getId()  + " записал " + i + "\n");

                } else {
                    flag = false;
                    System.out.print("Содержимое файла " + fileName + " - " + incrementNumber + " \n");
                    return;
                }
            }
            fileReader.close();
            semaphore.release();
            sleep(100);
            if (flag) {
                this.run();
            }


        } catch (InterruptedException e) {
            return;
        } catch (IOException e) {
            System.out.print("Невозможно записать или считать из файла "+ fileName);
            return;
        }
    }
}

