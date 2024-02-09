package org.example;
import com.kathmandu.server.HTTPServer;

public class Main {
 public static void main(String[] args) {
        int port = 8080;

        HTTPServer httpServer = new HTTPServer(port);
        httpServer.start();
    }
}