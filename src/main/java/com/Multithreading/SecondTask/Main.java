package com.Multithreading.SecondTask;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws Exception {

        CreateArray createArray = new CreateArray(80000000);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of repetitions : ");
        int number = scanner.nextInt();

        threadsPoolMode(number, createArray);
        threadMode(number, createArray);

    }

    public static void threadsPoolMode(int n, CreateArray createArray) {
        System.out.println("Mode ThreadsPool : ");

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future futureOne = service.submit((Callable) (new ThreadSinCosPool(createArray)));

        long timePool = 0;
        for (int i = 0; i < n; i++) {

            try {
                long startTime = System.nanoTime();
                System.out.println("Result " + i + " : " + futureOne.get());
                long stopTime = System.nanoTime();
                long timeRes = stopTime - startTime;
                timePool += timeRes;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Time spent : " + timePool / 1000000000 + " sec ");
        service.shutdown();

    }

    public static void threadMode(int n, CreateArray createArray) throws InterruptedException {
        System.out.println("Mode Thread : ");
        long timeThread = 0;

        for (int k = 0; k < n; k++) {
            long startTime = System.nanoTime();

            ThreadSinCos threadSinCos = new ThreadSinCos(createArray);
            threadSinCos.start();

            if (threadSinCos.isAlive()) {
                threadSinCos.join();
                System.out.println("Result " + k + " : " + threadSinCos.getResult());
                long stopTime = System.nanoTime();
                long timeRes = stopTime - startTime;
                timeThread += timeRes;
            }
        }
        System.out.println("Time spent : " + timeThread / 1000000000 + " sec ");
    }
}
