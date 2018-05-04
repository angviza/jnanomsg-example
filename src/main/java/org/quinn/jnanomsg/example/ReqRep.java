package org.quinn.jnanomsg.example;

import nanomsg.exceptions.IOException;
import nanomsg.reqrep.RepSocket;
import nanomsg.reqrep.ReqSocket;


public class ReqRep {
    private static String url = "tcp://127.0.0.1:7789";

    public static void main(String[] args) {
        node1();
        node0();
    }
    private static void node1() {
        final RepSocket socket = new RepSocket();
        socket.bind(url);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println( "node1:" + socket.recvString());
                        Thread.sleep(1000);
                        socket.send("world");

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void node0() {
        final ReqSocket socket = new ReqSocket();
        socket.connect(url);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        socket.send("hello");
                        Thread.sleep(1000);
                        System.out.println( "node0:" + socket.recvString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {

                    }
                }

            }
        }).start();
    }

}