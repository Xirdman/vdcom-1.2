package org.example;

import static org.junit.Assert.assertTrue;

import org.example.Threads.ExecuteThreads;
import org.example.Threads.InputDataException;
import org.example.Threads.RewriterThread;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class AppTest {

    @Test(expected = InputDataException.class)
    public void excectInputDataExceptionCauseNNotEven() throws InputDataException {
        ExecuteThreads.Execute("test.txt", 3);
    }

    @Test(expected = InputDataException.class)
    public void excectInputDataExceptionCauseNIsZeroOrLess() throws InputDataException {
        ExecuteThreads.Execute("test2.txt", -1);
    }

    @Test
    public void resultMustBe15() {
        try {
            ExecuteThreads.Execute("test3.txt", 15);
            FileReader fileReader = new FileReader("test3.txt");
            Scanner scanner = new Scanner(fileReader);
            int expectedResult = 0;
            if(scanner.hasNextLine()){
                expectedResult =Integer.parseInt( scanner.nextLine());
            }
            int rightResult = 15;
            Assert.assertEquals(expectedResult,rightResult);
        } catch (Exception e) {

        }
    }
    @Test
    public void resultMustBeDifferent(){
        try {
            ExecuteThreads.Execute("test4.txt", 19);
            FileReader fileReader = new FileReader("test3.txt");
            Scanner scanner = new Scanner(fileReader);
            int expectedResult = 0;
            if(scanner.hasNextLine()){
                expectedResult =Integer.parseInt( scanner.nextLine());
            }
            int rightResult = 20;
            Assert.assertFalse(expectedResult == rightResult);
        } catch (Exception e) {

        }
    }
}
