package com.sparkExample.sparkStreaming;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SocketServer {

    public static void main(String[] args) {

        ServerSocket socServer = null;
        String line;
        DataInputStream is;
        PrintStream os;
        Socket clientSocket = null;

        Path path = FileSystems.getDefault()
                .getPath("in/all-shakespeare.txt");
        List<String> lines = null;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
            lines = lines.stream().filter(l -> l.trim().length() > 0).collect(toList());

            socServer = new ServerSocket(9000);
            System.out.println("Socket opened");

            System.out.println("Total records read :" + lines.size());


            clientSocket = socServer.accept();
            System.out.println("Accepted client request from : " + clientSocket.getInetAddress());
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());

            while (true) {

                //Pick a random line
                int randomline = (int) (Math.random() * lines.size() - 10);

                StringBuffer sb = new StringBuffer();
                for (int i = randomline; i < randomline + 10; i++) {
                    sb.append(lines.get(i));
                }
                System.out.println("Publishing " + sb);
                os.println(sb);
                os.flush();
                //Randomly sleep 1 - 3 seconds
                Thread.sleep((long) (Math.random() * 3000));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
