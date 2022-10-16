package com.pokemon.store.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "FAVORITE_ANNOTATION" )
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAnnotation implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID_ANNOTATION", updatable = false, nullable = false)
    private UUID aonnotationId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private User user;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_POKEMON", referencedColumnName = "ID_POKEMON")
    private Pokemon pokemon;



}
