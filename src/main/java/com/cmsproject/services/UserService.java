package com.cmsproject.services;

import com.cmsproject.entities.User;
import com.cmsproject.repositories.UserRepository;
import com.cmsproject.utils.InputUtils;

public class UserService {
    private final UserRepository userRepository = new UserRepository();
    private User currentUser;

    public void login() {
        String username = InputUtils.getString("Digite o nome de usuário: ");
        String password = InputUtils.getString("Digite a senha: ");

        User user = userRepository.authenticate(username, password);

        if (user != null) {
            System.out.println("Login bem-sucedido!");
            currentUser = user;
            showUserMenu();
        } else {
            System.out.println("Nome de usuário ou senha incorretos.");
        }
    }

    public boolean userExists() {
        return userRepository.findAll().size() > 0;
    }

    public void logout() {
        currentUser = null;
        System.out.println("Logout bem-sucedido!");
    }

    public void createUser() {
        String username = InputUtils.getString("Digite o nome de usuário: ");
        String password = InputUtils.getString("Digite a senha: ");
        boolean isAdmin = InputUtils.getString("Usuário administrador? (s/n): ").equalsIgnoreCase("s");

        User user = new User(null, username, password, isAdmin);
        userRepository.addUser(user);
        System.out.println("Usuário criado com sucesso!");
    }

    public void showUserMenu() {
        if (currentUser == null) {
            System.out.println("Você precisa estar autenticado para acessar esta seção.");
            return;
        }

        ContentService contentService = new ContentService();
        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println("\n--- Menu do Usuário ---");
            System.out.println("1. Criar Conteúdo");
            System.out.println("2. Listar Conteúdos");
            System.out.println("3. Atualizar Conteúdo");
            System.out.println("4. Excluir Conteúdo");
            System.out.println("5. Criar Usuário");
            System.out.println("6. Logout");

            int choice = InputUtils.getInt("Escolha uma opção: ");

            switch (choice) {
                case 1:
                    contentService.createContent(currentUser.getUsername());
                    break;
                case 2:
                    contentService.listContents();
                    break;
                case 3:
                    contentService.updateContent();
                    break;
                case 4:
                    contentService.deleteContent();
                    break;
                case 5:
                    if (currentUser.isAdmin()) {
                        createUser();
                    } else {
                        System.out.println("Apenas administradores podem criar novos usuários.");
                    }
                    break;
                case 6:
                    logout();
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}