/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author 005-BCC-UESPI
 */
public class RPCClient {

    String nome = "Ozeias";

    RPCClient() {
        try {
            InetAddress ia = InetAddress.getByName("10.106.6.107");
            DatagramSocket ds = new DatagramSocket();
            DatagramSocket ds1 = new DatagramSocket(1300);
            System.out.println("\nRPC Client: \n");
            System.out.println("Insira a operação (ex: add, sub, mult, div) e os números que deseja calcular \n");
            while (true) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = br.readLine();
                byte b[] = str.getBytes();
                DatagramPacket dp = new DatagramPacket(b, b.length, ia, 1200);
                ds.send(dp);
                byte b1[] = new byte[8193];
                dp = new DatagramPacket(b1, b1.length);
                ds1.receive(dp);
                String s = new String(dp.getData(), 0, dp.getLength());
                System.out.println("\nResultado" + s + "\n");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        // Socket cliente = new Socket("10.106.6.107", 12345);
        //System.out.println("O cliente se conectou ao servidor!");
        new RPCClient();

//        Scanner teclado = new Scanner(System.in);
//        PrintStream saida = new PrintStream(cliente.getOutputStream());
//
//        while (teclado.hasNextLine()) {
//            saida.println(teclado.nextLine());
//            //new RPCClient();
//            saida.println(teclado.nextLine());
//        }
//        saida.close();
//        teclado.close();
        //cliente.close();  // lê a linha e faz algo com ela
    }

    //new RPCClient();
}
