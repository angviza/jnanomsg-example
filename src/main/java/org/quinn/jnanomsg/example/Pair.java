package org.quinn.jnanomsg.example;

import nanomsg.exceptions.IOException;
import nanomsg.pair.PairSocket;


public class Pair {
    private static String url = "tcp://127.0.0.1:7789";

    public static void main(String[] args) {
        node0();
        node1();
    }

    private static void node0() {
        PairSocket socket = new PairSocket();
        socket.connect(url);
        send(socket);
        recv(socket, "node0");
    }

    private static void node1() {
        PairSocket socket = new PairSocket();
        socket.bind(url);
        send(socket);
        recv(socket, "node1");
    }

    private static void recv(final PairSocket socket, final String nodeName) {
        socket.setRecvTimeout(2000);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println(nodeName + ":" + socket.recvString());
                        Thread.sleep(1000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private static void send(final PairSocket socket) {
        socket.setSendTimeout(1100);
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        socket.send("hello");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
