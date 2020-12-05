package io.github.darryring.learn.java.socket;

import io.github.darryring.util.io.IOStreamUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author darryring
 **/
class Socket1Test {

    @Test
    void t1() throws IOException {
        ServerSocket socket = new ServerSocket();
        socket.bind(new InetSocketAddress(6666)); // bind
        Socket accept = socket.accept(); // blocks until a connection is made.

        System.out.println(IOStreamUtil.read(accept.getInputStream())); // read
        // java.net.SocketException: Connection reset 连接被对端重置
        accept.getInputStream().close(); // response close

        accept.close(); // accept close
        socket.close(); // server close
    }

    @Test
    void t2() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(InetAddress.getLocalHost(), 6666)); // connect

        IOStreamUtil.write(socket.getOutputStream(), "hello socket program");
        socket.getOutputStream().close(); // request close

        socket.close(); // client close
    }

}
