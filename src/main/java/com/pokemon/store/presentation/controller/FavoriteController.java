package com.pokemon.store.presentation.controller;

import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.service.FavoriteService;
import com.pokemon.store.presentation.dto.PokemonDto;
import com.pokemon.store.presentation.dto.RequestPokemonFavoriteUserDto;
import com.pokemon.store.presentation.util.PokemonUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private PokemonUserConverter pokemonUserConverter;

    @PostMapping(value = "/addPokemon")
    public ResponseEntity<List<PokemonDto>> addPokemonByUser(@RequestBody final RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto) {
        favoriteService.addFavorite(requestPokemonFavoriteUserDto);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping (value = "/removePokemon")
    public ResponseEntity<List<PokemonDto>> removePokemonByUser(@RequestBody final RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto) {
        favoriteService.removeFavorite(requestPokemonFavoriteUserDto);
        return ResponseEntity.accepted().build();
    }





}
