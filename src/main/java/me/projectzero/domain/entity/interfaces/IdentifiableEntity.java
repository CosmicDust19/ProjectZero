package me.projectzero.domain.entity.interfaces;

import lombok.NonNull;

import java.util.function.Supplier;

/**
 * The base interface for entities with an ID of type {@link I}
 */
public interface IdentifiableEntity<I> extends Entity {

    I getId();

    void setId(I newId);

    /**
     * Get an entity by the {@code supplier} and set its ID to the {@code id}
     */
    static <T extends IdentifiableEntity<I>, I> T getInstance(@NonNull Supplier<T> supplier, I id) {
        T entity = supplier.get();
        entity.setId(id);
        return entity;
    }

}
