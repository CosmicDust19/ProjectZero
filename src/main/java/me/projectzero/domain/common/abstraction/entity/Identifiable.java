package me.projectzero.domain.common.abstraction.entity;

import lombok.NonNull;

import java.util.function.Supplier;

public interface Identifiable<I> extends Entity {

    static <T extends Identifiable<I>, I> T getInstance(@NonNull Supplier<T> supplier, I id) {
        T entity = supplier.get();
        entity.setId(id);
        return entity;
    }

    I getId();

    void setId(I newId);

}
