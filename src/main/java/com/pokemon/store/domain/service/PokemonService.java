package com.pokemon.store.domain.service;

import com.pokemon.store.domain.error.NoEntityException;
import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final ImageService imageService;

    public PokemonService(final PokemonRepository pokemonRepository, final ImageService imageService) {

        this.pokemonRepository = pokemonRepository;
        this.imageService = imageService;

    }

    public byte[] getPokemonImage(Integer idPokemon) {
        Pokemon pokemon = pokemonRepository.getReferenceById(idPokemon);
        return imageService.getImage(pokemon.getPokemonUrl());
    }


    public List<Pokemon> getPokemons() {
        return pokemonRepository.findAll();
    }
    public Optional<Pokemon> getPokemon(Integer idPokemon) {
        return pokemonRepository.findById(idPokemon);
    }

    public Pokemon getPokemonByName(String pokemonName) {
        Optional<Pokemon> pokemonOptional = pokemonRepository.findByPokemonName(pokemonName);
        return pokemonOptional.orElseThrow(() -> new NoEntityException("El pokemon no existe"));
    }
}
