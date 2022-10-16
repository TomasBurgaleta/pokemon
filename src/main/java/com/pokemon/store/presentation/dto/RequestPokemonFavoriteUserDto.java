package com.pokemon.store.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestPokemonFavoriteUserDto {

    private String pokemonName;
    private String userName;
}
