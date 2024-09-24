package com.cmsproject.services;

import com.cmsproject.entities.Content;
import com.cmsproject.repositories.ContentRepository;
import com.cmsproject.utils.InputUtils;

public class ContentService {
    private final ContentRepository contentRepository = new ContentRepository();

    public void listContents() {
        System.out.println("\n--- Lista de Conteúdos ---");
        for (Content content : contentRepository.findAll()) {
            System.out.println(content);
        }
    }

    public void createContent(String author) {
        String title = InputUtils.getString("Digite o título do conteúdo: ");
        String body = InputUtils.getString("Digite o corpo do conteúdo: ");
        Content content = new Content(null, title, body, author);
        contentRepository.addContent(content);
        System.out.println("Conteúdo criado com sucesso!");
    }

    public void updateContent() {
        Long id = (long) InputUtils.getInt("Digite o ID do conteúdo a ser atualizado: ");
        contentRepository.findById(id).ifPresentOrElse(content -> {
            String newTitle = InputUtils.getString("Novo título: ");
            String newBody = InputUtils.getString("Novo corpo: ");
            content.setTitle(newTitle);
            content.setBody(newBody);
            contentRepository.updateContent(content);
            System.out.println("Conteúdo atualizado com sucesso!");
        }, () -> System.out.println("Conteúdo não encontrado!"));
    }

    public void deleteContent() {
        Long id = (long) InputUtils.getInt("Digite o ID do conteúdo a ser deletado: ");
        contentRepository.deleteContent(id);
        System.out.println("Conteúdo deletado com sucesso!");
    }
}