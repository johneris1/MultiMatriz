package br.edu.ifce.server;

import java.io.*;
import java.net.*;

public class SocketServerExample {

	private static final int PORT = 9876;

	public static void main(String[] args) throws IOException {
	    try (ServerSocket server = new ServerSocket(PORT)) {
			System.out.println("Servidor iniciado na porta " + PORT);

			while (true) {
			    System.out.println("Aguardando cliente");

			    Socket clientSocket = server.accept();
			    System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

			    try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			         ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {
			        int[][] matriz = (int[][]) ois.readObject();
			        System.out.println("Matriz recebida: ");
			        print(matriz);

			        int[][] result = multiply(matriz);
			        oos.writeObject(result);
			        oos.close();
			        //server.close();
			        System.out.println("Resultado enviado ao cliente: ");
			        print(result);
			    } catch (ClassNotFoundException e) {
			        e.printStackTrace();
			    }
			}
		}
	}

	private static int[][] multiply(int[][] matriz) {
	    int[][] result = new int[2][2];
	    result[0][0] = matriz[0][0] * matriz[1][1];
	    result[0][1] = matriz[0][1] * matriz[1][0];
	    result[1][0] = matriz[1][0] * matriz[0][1];
	    result[1][1] = matriz[1][1] * matriz[0][0];
	    return result;
	}

	private static void print(int[][] matriz) {
	    for (int[] row : matriz) {
	        for (int value : row) {
	            System.out.print(value + " ");
	        }
	        System.out.println();
	    }
	}

}
