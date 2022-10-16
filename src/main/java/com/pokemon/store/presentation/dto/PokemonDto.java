package com.pokemon.store.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonDto extends RepresentationModel<PokemonDto> {

    private Integer idPokemon;
    private String pokemonName;
}
