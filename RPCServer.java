/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculator;

import java.io.IOException;
import static java.lang.Math.pow;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

/**
 *
 * @author 005-BCC-UESPI
 */
public final class RPCServer {

    static Socket accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    DatagramSocket ds;
    DatagramPacket dp;
    String str, methodName, result;
    float val1, val2;

    String nome = "Ozeias";

    RPCServer() throws IOException {

//        ServerSocket servidor = new ServerSocket(12345);
//        System.out.println("Porta 12345 aberta!");
//        // a continuação do servidor deve ser escrita aqui      
//        Socket cliente = servidor.accept();
//        System.out.println("Nova conexão com o cliente: " + cliente.getInetAddress().getHostAddress()
//        );
//
//        Scanner s = new Scanner(cliente.getInputStream());
//        
//        
//        while (s.hasNextLine()) {
//        System.out.println(s.nextLine());
        try {
            ds = new DatagramSocket(1200);
            byte b[] = new byte[4096];
            while (true) {
                dp = new DatagramPacket(b, b.length);
                ds.receive(dp);
                str = new String(dp.getData(), 0, dp.getLength());
                if (str.equalsIgnoreCase("q")) {
                    System.exit(1);
                } else {
                    StringTokenizer st = new StringTokenizer(str, " ");
                    int i = 0;
                    while (st.hasMoreTokens()) {
                        String token = st.nextToken();
                        methodName = token;
                        val1 = Float.parseFloat(st.nextToken());
                        val2 = Float.parseFloat(st.nextToken());
                    }
                }
                System.out.println(str);
                InetAddress ia = InetAddress.getLocalHost();
                if (methodName.equalsIgnoreCase("add")) {
                    result = "" + add(val1, val2);
                } else if (methodName.equalsIgnoreCase("sub")) {
                    result = "" + sub(val1, val2);
                } else if (methodName.equalsIgnoreCase("mul")) {
                    result = "" + mul(val1, val2);
                } else if (methodName.equalsIgnoreCase("div")) {
                    result = "" + div(val1, val2);

                } else if (methodName.equalsIgnoreCase("mod")) {
                    result = "" + mod(val1, val2);

                } else if (methodName.equalsIgnoreCase("pow")) {
                    result = "" + pow(val1, val2);
                }
                result = "[Ozeias] = " + result;
                byte b1[] = result.getBytes();
                DatagramSocket ds1 = new DatagramSocket();
                DatagramPacket dp1 = new DatagramPacket(b1, b1.length, dp.getAddress(), 1300);
                System.out.println("result : " + result + "\n");
                System.out.println("Cliente: " + dp.getAddress());
                ds1.send(dp1);
            }
        } catch (IOException | NumberFormatException e) {
        }
//        }

//        s.close();
//        servidor.close();
//        cliente.close();
    }

    public float add(float val1, float val2) {
        return val1 + val2;
    }

    public float sub(float val3, float val4) {
        return val3 - val4;
    }

    public float mul(float val3, float val4) {
        return val3 * val4;
    }

    public float div(float val3, float val4) {
        return val3 / val4;
    }

    public float mod(float val3, float val4) {
        return val3 % val4;
    }

    public float power(float val3, float val4) {
        return (float) Math.pow(val3, val4);
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Servidor rodando!");

        new RPCServer();

    }

}
