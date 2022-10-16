package com.pokemon.store.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "POKEMON" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Pokemon implements Serializable {

    @Id
    @Column(name = "ID_POKEMON", updatable = false, nullable = false)
    private Integer idPokemon;

    @Column(name = "POKEMON_NAME", updatable = false, nullable = false)
    private String pokemonName;

    @Column(name = "POKEMON_URL", updatable = false, nullable = false)
    private String pokemonUrl;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pokemon pokemon = (Pokemon) o;

        if (!Objects.equals(idPokemon, pokemon.idPokemon)) return false;
        return Objects.equals(pokemonName, pokemon.pokemonName);
    }

    @Override
    public int hashCode() {
        int result = idPokemon != null ? idPokemon.hashCode() : 0;
        result = 31 * result + (pokemonName != null ? pokemonName.hashCode() : 0);
        return result;
    }
}
