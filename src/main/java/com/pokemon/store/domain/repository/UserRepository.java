package com.pokemon.store.domain.repository;

import com.pokemon.store.domain.model.FavoriteAnnotation;
import com.pokemon.store.domain.model.Pokemon;
import com.pokemon.store.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {


    @Query("Select user from User user WHERE user.userName = ?1")
    Optional<User> findByUserName(String userName);

}
