package org.example;


import org.example.Threads.ExecuteThreads;
import org.example.Threads.InputDataException;
import org.example.Threads.RewriterThread;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) {

        System.out.print("Введите четное n больше 0\n");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        try {
            ExecuteThreads.Execute("out.txt", n);
        } catch (InputDataException e){
            System.out.print( e.getExceptionDescribe());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
