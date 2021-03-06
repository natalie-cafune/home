package com.Multithreading.SecondTask;

public class ThreadSinCos extends Thread {
    private CreateArray createArray;
    private double result;

    public ThreadSinCos(CreateArray createArray) {
        this.createArray = createArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < createArray.getSize(); i++) {
            result += Math.sin(i) + Math.cos(i);
        }
    }

    public double getResult() {
        return result;
    }
}
