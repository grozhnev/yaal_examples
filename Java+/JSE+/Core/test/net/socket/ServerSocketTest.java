package net.socket;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Run ServerSocket and send a request from Client Socket.
 */
public class ServerSocketTest {
    private static final String BODY = "abc";
    private static final int PORT = 2512;

    @Test
    public void test() throws IOException, ExecutionException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Future<Void> serverFuture = Executors.newSingleThreadExecutor().submit(() -> {
            Socket socket = serverSocket.accept();
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write(BODY);
            pw.flush();
            socket.close();
            serverSocket.close();
            return null;
        });

        Socket socket = new Socket("127.0.0.1", PORT);
        BufferedReader bis = new BufferedReader(new InputStreamReader((socket.getInputStream())));
        String act = bis.lines().collect(Collectors.joining("\n"));
        socket.close();
        assertThat(act, equalTo(BODY));

        serverFuture.get();
    }
}
