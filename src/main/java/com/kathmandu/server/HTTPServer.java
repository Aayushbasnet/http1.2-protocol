package com.kathmandu.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Simple HTTP Server (Version 1.2) implemented in Java.
 * This server listens on a specified port and handles incoming HTTP requests.
 */
public class HTTPServer {

    private int port;

    public HTTPServer(int port){
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
         // Server is running and ready to accept connections
         while (true) {
            // Accept incoming connections
            Socket clientSocket = serverSocket.accept();

            // Handle each connection in a separate thread
            Thread thread = new Thread(() -> handleRequest(clientSocket));
            thread.start();
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    } 

    private void handleRequest(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)
        ) {
            // Read the request
            String requestLine = reader.readLine();

            RequestHandler requestHandler = new DefaultRequestHandler();

            // Process the request and send a response
            String response = requestHandler.handleRequest(requestLine);

            // Send the HTTP response
            writer.println(response);

            // Close the clientSocket when done processing the request
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class DefaultRequestHandler implements RequestHandler {

        @Override
        public String handleRequest(String request) {
            return "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello from DefaultRequestHandler!";
        }
    }
}
