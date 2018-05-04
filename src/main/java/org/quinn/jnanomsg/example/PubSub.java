package org.quinn.jnanomsg.example;

import nanomsg.exceptions.IOException;
import nanomsg.pubsub.PubSocket;
import nanomsg.pubsub.SubSocket;


public class PubSub {
    private static String url = "tcp://127.0.0.1:7789";

    public static void main(String[] args) {
        service();
        client0();
        client1();
        client2();
    }

    private static void client0() {
        client("client0");
    }

    private static void client1() {
        client("client1");

    }

    private static void client2() {
        client("client2");
    }

    private static void client(final String name) {
        final SubSocket socket = new SubSocket();
        socket.connect(url);
        socket.subscribe("test");
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try{
                        System.out.println(name + ":" + socket.recvString());
                    }catch (IOException e) {

                    }
                }
            }
        }).start();



    }

    private static void service() {
        final PubSocket socket = new PubSocket();
        socket.bind(url);

        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try{
                        socket.send("test1 msg");
                        Thread.sleep(2000);
                    }catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}