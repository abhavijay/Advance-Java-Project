package com.shopsphere.demo.serialization;

import java.io.*;

public class SerializationDemo {
    public static void main(String[] args) throws Exception {
        ProductPacket original=new ProductPacket(1,"Cloud Runner Sneakers",2499.00);
        try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("product-packet.bin"))) {
            out.writeObject(original);
        }
        try(ObjectInputStream in=new ObjectInputStream(new FileInputStream("product-packet.bin"))) {
            System.out.println("Deserialized: "+in.readObject());
        }
    }
}
