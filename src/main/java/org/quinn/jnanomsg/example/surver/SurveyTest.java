package org.quinn.jnanomsg.example.surver;

import nanomsg.async.AsyncSocket;
import nanomsg.async.IAsyncCallback;
import nanomsg.exceptions.IOException;


public class SurveyTest {
    private static String url = "tcp://127.0.0.1:3000";

    public static void main(String[] args) {
        service();
        client();
    }

    public static void service() {
        final Surver service = new Surver();
        service.bind(url);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("service:"+service.recvString());
                        Thread.sleep(1);
                    } catch (IOException e) {

                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        service.send("sup");
                        Thread.sleep(2000);
                    } catch (IOException e) {

                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }


    public static void client() {
        final Respondent client = new Respondent();
        client.connect(url);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        client.recvString();
                        Thread.sleep(1);
                        client.send("y");
                    } catch (IOException e) {

                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }


}
