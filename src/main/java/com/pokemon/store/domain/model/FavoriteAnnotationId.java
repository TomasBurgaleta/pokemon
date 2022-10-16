package com.pokemon.store.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class FavoriteAnnotationId implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_POKEMON", referencedColumnName = "ID_POKEMON")
    private Pokemon pokemon;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteAnnotationId that = (FavoriteAnnotationId) o;

        if (!Objects.equals(user, that.user)) return false;
        return Objects.equals(pokemon, that.pokemon);
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (pokemon != null ? pokemon.hashCode() : 0);
        return result;
    }
}
