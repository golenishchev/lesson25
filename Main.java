package com.example.lesson25;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int waitTimeLimit = 60000;
        Scanner scanner = new Scanner(System.in);
        ThreadMessage.threadMessage("Сколько миллисекунд основной поток будет ожидать завершения выполнения второго потока?");
        int waitTime = scanner.nextInt();
        // if waitTime value is bigger than waitTimeLimit then use default value
        if (waitTimeLimit < waitTime) {
            waitTime = waitTimeLimit;
        }
        Thread thread = new Thread(new MessageLoop());
        ThreadMessage.threadMessage("Старт MessageLoop thread");
        ThreadMessage.threadMessage("Жду пока MessageLoop thread закончит свое исполнение");
        long startTime = System.currentTimeMillis();
        thread.start();
        ThreadMessage.threadMessage("ждать " + waitTime + " миллисекунд");
        while (thread.isAlive()) {
            try {
                ThreadMessage.threadMessage("Я жду...");
                thread.join(1000);
                if (((System.currentTimeMillis() - startTime) > waitTime) && thread.isAlive()) {
                    ThreadMessage.threadMessage("Больше ждать не буду");
                    thread.interrupt();
                    thread.join();
                }
            } catch (InterruptedException e) {
                ThreadMessage.threadMessage("You have InterruptedException");
            }
        }
        long passedTime = System.currentTimeMillis() - startTime;
        ThreadMessage.threadMessage("Конец!" + " Прошло времени " + passedTime);
    }
}
