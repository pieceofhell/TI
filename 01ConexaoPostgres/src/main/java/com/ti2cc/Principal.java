package com.ti2cc;

import com.ti2cc.X;
import java.util.Scanner;

public class Principal {

  public static void main(String[] args) {
    DAO dao = new DAO();

    dao.conectar();

    X usuarioTeste = new X(10, "pablito", 21, "pablitones@gmail.com");

    System.out.println(
      "Escolha uma das opcoes abaixo para ser executada:\n1)Listar e Mostrar Usuarios\n2)Inserir\n3)Excluir\n4)Atualizar\n5)Sair"
    );

    Scanner sc = new Scanner(System.in);
    int opcao = sc.nextInt();

    if (opcao == 1) {
      //Mostrar usuarios
      X[] usuarios = dao.getUsuarios();
      System.out.println("==== Mostrar usuarios === ");
      for (int i = 0; i < usuarios.length; i++) {
        System.out.println(usuarios[i].toString());
      }
    } else if (opcao == 2) {
      X usuario = new X(10, "pablo", 21, "pablo@gmail.com");
      if (dao.inserirUsuario(usuario) == true) {
        System.out.println("Insercao com sucesso -> " + usuario.toString());
      }
    } else if (opcao == 3) {
      //Atualizar usuario
      usuarioTeste.setIdNum(12233445);
      dao.atualizarUsuario(usuarioTeste);
    } else if (opcao == 4) {
      //Excluir usuario
      dao.excluirUsuario(usuarioTeste.getIdNum());
    }

    dao.close();
  }
}