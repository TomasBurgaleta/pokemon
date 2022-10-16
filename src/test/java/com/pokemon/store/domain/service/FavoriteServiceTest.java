package com.pokemon.store.domain.service;

import com.pokemon.store.domain.model.FavoriteAnnotation;
import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.model.User;
import com.pokemon.store.domain.repository.FavoriteAnnotationRepository;
import com.pokemon.store.presentation.dto.RequestPokemonFavoriteUserDto;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceTest {


    @Mock
    private FavoriteAnnotationRepository favoriteAnnotationRepository;

    @Mock
    private UserService userService;

    @Mock
    private PokemonService pokemonService;

    Faker faker = new Faker();

    @InjectMocks
    private FavoriteService favoriteService;

    @Test
    void getFavoritesPokemon4User() {
        String userName = faker.random().hex(10);
        List<FavoriteAnnotation> result = createFavoriteAnnotationList(userName);
        when(favoriteAnnotationRepository.findByUser(userName)).thenReturn(result);
        List<Pokemon> pokemonList = favoriteService.getFavoritesPokemon4User(userName);
        assertThat(pokemonList).isNotEmpty();
    }



    @Test
    void addFavorite() {
        String userName = faker.random().hex(10);
        String pokemonName = faker.random().hex(10);
        User user = User.builder().userName(userName).idUser(UUID.randomUUID()).build();
        Pokemon pokemon = Pokemon.builder().pokemonName(pokemonName).idPokemon(faker.random().nextInt(10, 100)).build();

        RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto = new RequestPokemonFavoriteUserDto(pokemonName, userName);
        when(userService.getUserByName(userName)).thenReturn(user);
        when(pokemonService.getPokemonByName(pokemonName)).thenReturn(pokemon);

        favoriteService.addFavorite(requestPokemonFavoriteUserDto);

        Mockito.verify(favoriteAnnotationRepository, times(1)).save(any(FavoriteAnnotation.class));
    }

    @Test
    void removeFavorite() {
        String userName = faker.random().hex(10);
        String pokemonName = faker.random().hex(10);
        User user = User.builder().userName(userName).idUser(UUID.randomUUID()).build();
        Pokemon pokemon = Pokemon.builder().pokemonName(pokemonName).idPokemon(faker.random().nextInt(10, 100)).build();

        RequestPokemonFavoriteUserDto requestPokemonFavoriteUserDto = new RequestPokemonFavoriteUserDto(pokemonName, userName);
        when(userService.getUserByName(userName)).thenReturn(user);
        when(pokemonService.getPokemonByName(pokemonName)).thenReturn(pokemon);

        favoriteService.removeFavorite(requestPokemonFavoriteUserDto);

        Mockito.verify(favoriteAnnotationRepository, times(1)).delete(any(FavoriteAnnotation.class));

    }

    private List<FavoriteAnnotation> createFavoriteAnnotationList(String userName) {
        User user = User.builder().userName(userName).idUser(UUID.randomUUID()).build();
        return  IntStream.range(0,5).mapToObj(i -> creteFavoriteAnnotation(user)).collect(Collectors.toList());
    }

    private FavoriteAnnotation creteFavoriteAnnotation(User user){
        Pokemon pokemon = Pokemon.builder()
                .pokemonName(faker.random().hex(10))
                .idPokemon(faker.random().nextInt(10, 100)).build();
        return  FavoriteAnnotation.builder().pokemon(pokemon).user(user).build();
    }

}