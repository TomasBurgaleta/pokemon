package com.pokemon.store.presentation.dto;

import com.pokemon.store.domain.model.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PokemonUserDto extends RepresentationModel<PokemonDto> {

    private Integer idPokemon;
    private String pokemonName;
    private boolean likeUser = false;
}
