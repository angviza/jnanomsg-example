package org.quinn.jnanomsg.example;

import nanomsg.exceptions.IOException;
import nanomsg.pipeline.PushSocket;
import nanomsg.pipeline.PullSocket;


public class Pipeline {
    private static String url = "tcp://127.0.0.1:7789";

    public static void main(String[] args) {
        node1();
        node0();
    }

    private static void node1() {
        final PullSocket socket = new PullSocket();
        socket.bind(url);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println(socket.recvString());
                        Thread.sleep(1000);
                    } catch (IOException e) {

                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();

    }

    private static void node0() {
        final PushSocket socket = new PushSocket();
        socket.connect(url);
        socket.send("hello");
    }

}
