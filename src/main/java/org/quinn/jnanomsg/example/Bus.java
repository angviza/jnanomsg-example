package org.quinn.jnanomsg.example;

import nanomsg.async.AsyncSocket;
import nanomsg.async.IAsyncCallback;
import nanomsg.bus.BusSocket;
import nanomsg.exceptions.IOException;


public class Bus {
    private static String url0 = "tcp://127.0.0.1:7780";
    private static String url1 = "tcp://127.0.0.1:7781";
    private static String url2 = "tcp://127.0.0.1:7782";
    private static String url3 = "tcp://127.0.0.1:7783";

    public static void main(String[] args) {
        BusSocket s0 = node("node0", url0, new String[]{url1, url2, url3});
        BusSocket s1 = node("node1", url1, new String[]{url2, url3});
        BusSocket s2 = node("node2", url2, new String[]{url3});
        BusSocket s3 = node("node3", url3, new String[]{});

        s0.send("client0 send a");
        s1.send("client1 send a");
        s2.send("client2 send a");
        s3.send("client3 send a");

    }

    private static BusSocket node(final String name, String self, String[] other) {
        final BusSocket socket = new BusSocket();
        socket.bind(self);
        for (String s : other){
            socket.connect(s);
        }
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println(name + ":"+ socket.recvString());
                        Thread.sleep(1);
                    } catch (IOException e) {

                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
        return socket;


    }

}
