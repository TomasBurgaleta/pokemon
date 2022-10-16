package com.pokemon.store.presentation.controller;

import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.service.FavoriteService;
import com.pokemon.store.domain.service.PokemonService;
import com.pokemon.store.presentation.dto.PokemonDto;
import com.pokemon.store.presentation.dto.PokemonUserDto;
import com.pokemon.store.presentation.util.PokemonConverter;
import com.pokemon.store.presentation.util.PokemonUserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private PokemonConverter pokemonConverter;

    @Autowired
    private PokemonUserConverter pokemonUserConverter;

    @Autowired
    private FavoriteService favoriteService;


    @GetMapping(value = "/image/{idPokemon}" ,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("idPokemon") Integer idPokemon) {
        byte[] image =  pokemonService.getPokemonImage(idPokemon);
        return ResponseEntity.ok(image);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PokemonDto>> getAllPokemon() {
        List<Pokemon> pokemonList = pokemonService.getPokemons();
        return ResponseEntity.ok(pokemonList.stream()
                .map(pokemonConverter::createFrom)
                .map(this::addImageLink)
                .map(this::addSelfLink)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/favorites/{userName}" ,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<List<PokemonDto>> getFavoritePokemonByUser(@PathVariable("userName") String userName) {
        List<Pokemon> pokemonUser =  favoriteService.getFavoritesPokemon4User(userName);

        return ResponseEntity.ok(pokemonUser.stream()
                .map(pokemonConverter::createFrom)
                .map(this::addImageLink)
                .map(this::addSelfLink)
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/AllPokemonByUser/{userName}" ,
            produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<List<PokemonUserDto>> getAllPokemonByUser(@PathVariable("userName") String userName) {
        List<Pokemon> pokemonList = pokemonService.getPokemons();
        List<Pokemon> pokemonUser =  favoriteService.getFavoritesPokemon4User(userName);
        List<Integer> pokemonsId = pokemonUser.stream().map(Pokemon::getIdPokemon).collect(Collectors.toList());

        return ResponseEntity.ok(pokemonList.stream()
                .map(pokemonUserConverter::createFrom)
                .map(this::addImageLink)
                .map(this::addSelfLink)
                .map(pokemonUserDto -> addFavorite(pokemonsId, pokemonUserDto))
                .collect(Collectors.toList()));
    }


    private PokemonUserDto addFavorite(List<Integer> pokemonsId, PokemonUserDto pokemonDto) {
        if(pokemonsId.contains(pokemonDto.getIdPokemon())) {
            pokemonDto.setLikeUser(true);
        }
        return pokemonDto;
    }

    @GetMapping("/{idPokemon}")
    public ResponseEntity<PokemonDto> getPokemon(@PathVariable("idPokemon") Integer idPokemon) {
        Optional<Pokemon> pokemon = pokemonService.getPokemon(idPokemon);

        return pokemon.map(pokemonConverter::createFrom)
                .map(this::addImageLink)
                .map(this::addSelfLink)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }



    private PokemonDto addImageLink(PokemonDto pokemonDto) {
        Link imageLink = linkTo(methodOn(PokemonController.class)
                .getImage(pokemonDto.getIdPokemon())).withSelfRel();
        pokemonDto.add(imageLink);
        return pokemonDto;
    }

    private PokemonDto addSelfLink(PokemonDto pokemonDto) {
        Link selfLink = linkTo(methodOn(PokemonController.class)
                .getPokemon(pokemonDto.getIdPokemon())).withSelfRel();
        pokemonDto.add(selfLink);
        return pokemonDto;
    }

    private PokemonUserDto addImageLink(PokemonUserDto pokemonDto) {
        Link imageLink = linkTo(methodOn(PokemonController.class)
                .getImage(pokemonDto.getIdPokemon())).withSelfRel();
        pokemonDto.add(imageLink);
        return pokemonDto;
    }

    private PokemonUserDto addSelfLink(PokemonUserDto pokemonDto) {
        Link selfLink = linkTo(methodOn(PokemonController.class)
                .getPokemon(pokemonDto.getIdPokemon())).withSelfRel();
        pokemonDto.add(selfLink);
        return pokemonDto;
    }



}
