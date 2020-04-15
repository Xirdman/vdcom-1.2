package org.example.Threads;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class ExecuteThreads {
    public static void Execute(String fileName, int incrementNumber) throws InputDataException {
        if(incrementNumber<=0){
            throw new InputDataException("Введенное n - меньше или равно нулю ,это протеворечит условиям задачи");
        }
        if (incrementNumber % 2 != 0) {
            throw new InputDataException("Введенное n - нечетное ,это протеворечит условиям задачи");
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("0");
            fileWriter.flush();
            fileWriter.close();
            Semaphore semaphore = new Semaphore(1);
            RewriterThread thread1 = new RewriterThread("thread1", fileName, incrementNumber, semaphore);
            thread1.start();
            RewriterThread thread2 = new RewriterThread("thread2", fileName, incrementNumber, semaphore);
            thread2.start();
            /*FileReader fileReader = new FileReader(fileName);
            Scanner scan = new Scanner(fileReader);
            if (scan.hasNextLine()) {
                System.out.print("Содержимое файла " + fileName + " " + scan.nextLine());
            }*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
