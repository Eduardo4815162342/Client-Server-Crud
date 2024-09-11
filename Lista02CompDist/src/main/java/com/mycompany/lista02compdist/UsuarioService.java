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
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

    private static final String FILE_PATH = "usuarios.json";
    private List<Usuario> usuarios;
    private Gson gson;

    public UsuarioService() {
        this.gson = new Gson();
        this.usuarios = carregarUsuarios();
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        salvarUsuarios();
    }

    public void atualizarUsuario(String cpf, Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(cpf)) {
                usuarios.set(i, usuarioAtualizado);
                salvarUsuarios();
                break;
            }
        }
    }

    public void removerUsuario(String cpf) {
        usuarios.removeIf(usuario -> usuario.getCpf().equals(cpf));
        salvarUsuarios();
    }

    private List<Usuario> carregarUsuarios() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<ArrayList<Usuario>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarUsuarios() {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(usuarios, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String buscarEnderecoViaCep(String cep) throws IOException {
        String urlString = "https://viacep.com.br/ws/" + cep + "/json";
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
