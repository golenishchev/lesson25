package com.example.lesson25;

public class MessageLoop implements Runnable {
    Thread thread;
    private String[] messagesArray = {"Первая строка из массива,", "Вторая строка из массива,",
            "Третья строка из массива,"};

    public void run() {
        thread = new Thread(this, "Thread-0");
        try {
            for (String message : messagesArray) {
                Thread.sleep(4000);
                ThreadMessage.threadMessage(message);
            }
        } catch (InterruptedException e) {
            ThreadMessage.threadMessage("InterruptedException");
        }
    }
}
