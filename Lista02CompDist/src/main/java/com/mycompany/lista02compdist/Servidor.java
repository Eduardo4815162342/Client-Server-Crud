/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lista02compdist;

/**
 *
 * @author Eduardo
 */
import com.google.gson.Gson;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Servidor {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        UsuarioService usuarioService = new UsuarioService();

        System.out.println("Servidor iniciado...");

        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String operacao = in.readLine();

            switch (operacao) {
                case "LISTAR":
                    List<Usuario> usuarios = usuarioService.listarUsuarios();
                    out.println(new Gson().toJson(usuarios));
                    break;
                case "CADASTRAR":
                    String nome = in.readLine();
                    String cpf = in.readLine();
                    String email = in.readLine();
                    String cep = in.readLine();

                    String endereco = usuarioService.buscarEnderecoViaCep(cep);
                    Usuario usuario = new Usuario(nome, cpf, email, cep, endereco);
                    usuarioService.cadastrarUsuario(usuario);
                    out.println("Usuário cadastrado com sucesso!");
                    break;
                case "ATUALIZAR":
                    String cpfAtualizar = in.readLine();
                    nome = in.readLine();
                    email = in.readLine();
                    cep = in.readLine();
                    endereco = usuarioService.buscarEnderecoViaCep(cep);
                    Usuario usuarioAtualizado = new Usuario(nome, cpfAtualizar, email, cep, endereco);
                    usuarioService.atualizarUsuario(cpfAtualizar, usuarioAtualizado);
                    out.println("Usuário atualizado com sucesso!");
                    break;
                case "REMOVER":
                    String cpfRemover = in.readLine();
                    usuarioService.removerUsuario(cpfRemover);
                    out.println("Usuário removido com sucesso!");
                    break;
            }

            socket.close();
        }
    }
}

