package org.example;


import org.example.Threads.ExecuteThreads;
import org.example.Threads.RewriterThread;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) {
        try {
            ExecuteThreads.Execute("out.txt", 10);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
