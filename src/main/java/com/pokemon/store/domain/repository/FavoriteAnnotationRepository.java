package com.pokemon.store.domain.repository;

import com.pokemon.store.domain.model.FavoriteAnnotation;
import com.pokemon.store.domain.model.FavoriteAnnotationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteAnnotationRepository extends JpaRepository<FavoriteAnnotation, FavoriteAnnotationId> {

    @Query("Select favorites from FavoriteAnnotation favorites WHERE favorites.user.userName = ?1")
    List<FavoriteAnnotation> findByUser(String userName);


}
