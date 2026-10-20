package com.shopsphere.demo.network;

import java.io.*;
import java.net.*;

/** Tiny localhost socket server for the networking viva demonstration. */
public class InventoryServer {
    public static void main(String[] args) throws IOException {
        int port = 5050;
        try(ServerSocket server=new ServerSocket(port)) {
            System.out.println("ShopSphere Inventory Server listening on "+port);
            while(true) {
                try(Socket client=server.accept();
                    BufferedReader in=new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter out=new PrintWriter(client.getOutputStream(),true)) {
                    String request=in.readLine();
                    out.println("Inventory server received: "+request+" | demo-stock=25");
                }
            }
        }
    }
}
