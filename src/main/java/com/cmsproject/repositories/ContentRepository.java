package com.cmsproject.repositories;

import com.cmsproject.entities.Content;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContentRepository {
    private final List<Content> contents = new ArrayList<>();
    private Long currentId = 1L;

    public List<Content> findAll() {
        return new ArrayList<>(contents);
    }

    public Optional<Content> findById(Long id) {
        return contents.stream()
                .filter(content -> content.getId().equals(id))
                .findFirst();
    }

    public void addContent(Content content) {
        content.setId(currentId++);
        contents.add(content);
    }

    public void updateContent(Content content) {
        // Atualiza o conteúdo na lista, se necessário
        // (Neste exemplo, estamos apenas substituindo, mas você pode ajustar conforme necessário)
        int index = contents.indexOf(content);
        if (index != -1) {
            contents.set(index, content);
        }
    }

    public void deleteContent(Long id) {
        contents.removeIf(content -> content.getId().equals(id));
    }
}
