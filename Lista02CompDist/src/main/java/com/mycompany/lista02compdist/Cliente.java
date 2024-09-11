/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista02compdist;

/**
 *
 * @author Eduardo
 */
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Mantém o cliente rodando até o usuário escolher sair
        boolean continuar = true;

        while (continuar) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                // Menu de operações
                System.out.println("Escolha uma operação: LISTAR, CADASTRAR, ATUALIZAR, REMOVER, SAIR");
                String operacao = scanner.nextLine().toUpperCase();

                // Enviar operação ao servidor
                out.println(operacao);

                switch (operacao) {
                    case "LISTAR":
                        String usuarios = in.readLine();
                        System.out.println("Usuários cadastrados: " + usuarios);
                        break;

                    case "CADASTRAR":
                        System.out.println("Nome:");
                        String nome = scanner.nextLine();
                        System.out.println("CPF:");
                        String cpf = scanner.nextLine();
                        System.out.println("Email:");
                        String email = scanner.nextLine();
                        System.out.println("CEP:");
                        String cep = scanner.nextLine();

                        // Enviar dados para o servidor
                        out.println(nome);
                        out.println(cpf);
                        out.println(email);
                        out.println(cep);

                        // Ler a resposta do servidor
                        System.out.println(in.readLine());
                        break;

                    case "ATUALIZAR":
                        System.out.println("CPF do usuário a atualizar:");
                        String cpfAtualizar = scanner.nextLine();
                        System.out.println("Nome atualizado:");
                        String nomeAtualizar = scanner.nextLine();
                        System.out.println("Email atualizado:");
                        String emailAtualizar = scanner.nextLine();
                        System.out.println("CEP atualizado:");
                        String cepAtualizar = scanner.nextLine();

                        // Enviar dados para o servidor
                        out.println(cpfAtualizar);
                        out.println(nomeAtualizar);
                        out.println(emailAtualizar);
                        out.println(cepAtualizar);

                        // Ler a resposta do servidor
                        System.out.println(in.readLine());
                        break;

                    case "REMOVER":
                        System.out.println("CPF do usuário a remover:");
                        String cpfRemover = scanner.nextLine();

                        // Enviar CPF para o servidor
                        out.println(cpfRemover);

                        // Ler a resposta do servidor
                        System.out.println(in.readLine());
                        break;

                    case "SAIR":
                        // Encerra o loop
                        continuar = false;
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Operação inválida. Tente novamente.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Erro de comunicação com o servidor: " + e.getMessage());
            }
        }

        scanner.close();
    }
}

