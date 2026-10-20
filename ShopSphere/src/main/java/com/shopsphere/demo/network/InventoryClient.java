package com.shopsphere.demo.network;

import java.io.*;
import java.net.Socket;

public class InventoryClient {
    public static void main(String[] args) throws IOException {
        try(Socket socket=new Socket("localhost",5050);
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            out.println("CHECK Cloud Runner Sneakers");
            System.out.println(in.readLine());
        }
    }
}
