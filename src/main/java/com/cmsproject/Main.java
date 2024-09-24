package com.cmsproject;

import com.cmsproject.database.DatabaseInitializer;
import com.cmsproject.services.UserService;
import com.cmsproject.services.ContentService;
import com.cmsproject.utils.InputUtils;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        UserService userService = new UserService();

        if (userService.userExists()) {
            userService.login();
        } else {
            System.out.println("Nenhum usuário encontrado. Por favor, crie um usuário administrador.");
            userService.createUser();
        }
        ContentService contentService = new ContentService();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Sistema de Gerenciamento de Conteúdo ---");
            System.out.println("1. Login");
            System.out.println("2. Listar Conteúdos Públicos");
            System.out.println("3. Sair");
            int choice = InputUtils.getInt("Escolha uma opção: ");

            switch (choice) {
                case 1:
                    userService.login();
                    break;
                case 2:
                    contentService.listContents();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        System.out.println("Aplicação encerrada.");
    }
}
