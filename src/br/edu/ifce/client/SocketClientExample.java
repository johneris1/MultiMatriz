package br.edu.ifce.client;

import java.io.*;
import java.net.*;
import java.util.*;

public class SocketClientExample {

	public static void main(String[] args) throws Exception {
	    InetAddress host = InetAddress.getLocalHost();
	    try (Socket socket = new Socket(host.getHostName(), 9876);
	         Scanner scanner = new Scanner(System.in);
	         ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

	        System.out.println("Enviando requisição para o socket");

	       
	        int[][] matriz = new int[2][2];
	        for (int i = 0; i < matriz.length; i++) {
	            for (int j = 0; j < matriz[i].length; j++) {
	                System.out.printf("Digite o valor da posição [%d][%d]:%n", i, j);
	                matriz[i][j] = scanner.nextInt();
	            }
	        }
	        oos.writeObject(matriz);

	        int[][] resultado = (int[][]) ois.readObject();
	        System.out.println("Resultado recebido do servidor:");
	        imprimirResultadoMatriz(resultado);
	    }
	}

	private static void imprimirResultadoMatriz(int[][] matriz) {
	    for (int i = 0; i < matriz.length; i++) {
	        for (int j = 0; j < matriz[i].length; j++) {
	            System.out.print(matriz[i][j] + " ");
	        }
	        System.out.println();
	    }
	}
}