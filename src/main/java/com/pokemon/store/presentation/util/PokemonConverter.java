package com.pokemon.store.presentation.util;

import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.presentation.dto.PokemonDto;
import com.pokemon.store.presentation.dto.PokemonUserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PokemonConverter {

    private final ModelMapper modelMapper;

    public PokemonConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PokemonDto createFrom(Pokemon entity) {
        return modelMapper.map(entity, PokemonDto.class);

    }

}
