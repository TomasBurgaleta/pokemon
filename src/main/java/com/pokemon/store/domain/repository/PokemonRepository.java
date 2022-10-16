package com.pokemon.store.domain.repository;

import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    @Query("Select pokemon from Pokemon pokemon WHERE pokemon.pokemonName = ?1")
    Optional<Pokemon> findByPokemonName(String pokemonName);


}
