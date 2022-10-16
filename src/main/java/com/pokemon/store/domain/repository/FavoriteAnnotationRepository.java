package com.pokemon.store.domain.repository;

import com.pokemon.store.domain.model.FavoriteAnnotation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FavoriteAnnotationRepository extends JpaRepository<FavoriteAnnotation, UUID> {

    @Query("Select favorites from FavoriteAnnotation favorites WHERE favorites.user.userName = ?1")
    List<FavoriteAnnotation> findByUser(String userName);


}
