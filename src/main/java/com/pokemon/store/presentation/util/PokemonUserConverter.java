package com.pokemon.store.presentation.util;

import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.presentation.dto.PokemonUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class PokemonUserConverter {

    private final ModelMapper modelMapper;

    public PokemonUserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PokemonUserDto createFrom(Pokemon entity) {
        return modelMapper.map(entity, PokemonUserDto.class);

    }
}
