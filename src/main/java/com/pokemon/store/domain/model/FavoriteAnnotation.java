package com.pokemon.store.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "FAVORITE_ANNOTATION" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(FavoriteAnnotationId.class)
public class FavoriteAnnotation implements Serializable {

    @EmbeddedId
    public FavoriteAnnotationId id;

//    @Id
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
//    private User user;
//
//    @Id
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ID_POKEMON", referencedColumnName = "ID_POKEMON")
//    private Pokemon pokemon;



}
