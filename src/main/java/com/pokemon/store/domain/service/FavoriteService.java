package com.pokemon.store.domain.service;

import com.pokemon.store.domain.model.FavoriteAnnotation;
import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.model.User;
import com.pokemon.store.domain.repository.FavoriteAnnotationRepository;

import com.pokemon.store.presentation.dto.RequestPokemonFavoriteUserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteAnnotationRepository favoriteAnnotationRepository;

    private final UserService userService;

    private final PokemonService pokemonService;

    public FavoriteService ( final FavoriteAnnotationRepository favoriteAnnotationRepository,
                             final PokemonService pokemonService, final UserService userService) {
        this.favoriteAnnotationRepository = favoriteAnnotationRepository;
        this.pokemonService = pokemonService;
        this.userService = userService;

    }


    public List<Pokemon> getFavoritesPokemon4User(String userName) {
        List<FavoriteAnnotation> favoriteAnnotations = favoriteAnnotationRepository.findByUser(userName);
        return favoriteAnnotations.stream().map(FavoriteAnnotation::getPokemon).collect(Collectors.toList());
    }


    public void addFavorite(RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto) {
        User user = userService.getUserByName(requestPokemonFavoriteUserDto.getUserName());
        Pokemon pokemon = pokemonService.getPokemonByName(requestPokemonFavoriteUserDto.getPokemonName());
        FavoriteAnnotation favoriteAnnotation = FavoriteAnnotation.builder().user(user).pokemon(pokemon).build();
        favoriteAnnotationRepository.save(favoriteAnnotation);
    }

    public void removeFavorite(RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto) {
        User user = userService.getUserByName(requestPokemonFavoriteUserDto.getUserName());
        Pokemon pokemon = pokemonService.getPokemonByName(requestPokemonFavoriteUserDto.getPokemonName());
        FavoriteAnnotation favoriteAnnotation = FavoriteAnnotation.builder().user(user).pokemon(pokemon).build();
        favoriteAnnotationRepository.delete(favoriteAnnotation);
    }
}
