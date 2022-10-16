package com.pokemon.store.domain.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {

    private final StoreImageService storeImageService;

    public ImageService (final  StoreImageService storeImageService) {
        this.storeImageService = storeImageService;
    }

    public byte[] getImage(String url) {

        byte[] imageFile = null;
        try {
            imageFile = storeImageService.getFile(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imageFile;
    }
}
