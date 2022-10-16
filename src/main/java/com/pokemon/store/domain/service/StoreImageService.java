package com.pokemon.store.domain.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class StoreImageService {

    private static final String BASE_PATH = "src/main/resources/static/pokemon/";

    public byte[] getFile(String url) throws IOException {
        String completeUrl = BASE_PATH + url;
        return Files.readAllBytes(Paths.get(completeUrl));
    }
}
