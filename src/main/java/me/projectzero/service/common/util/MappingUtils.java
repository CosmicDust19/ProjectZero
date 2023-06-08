package me.projectzero.service.common.util;


import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.projectzero.domain.common.abstraction.entity.Identifiable;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.function.Supplier;

@UtilityClass
public class MappingUtils {

    /**
     * @param <T>       Entity type
     * @param <I>       New entity id type
     * @param oldEntity Old entity
     * @param newEntity New entity supplier
     * @param entityId  ID of the new entity
     * @return A new entity with this new ID if the new ID is not null, otherwise it returns the old entity.
     */
    public <T extends Identifiable<I>, I> T getEntityWithId(@NonNull Supplier<T> newEntity, @Nullable I entityId, T oldEntity) {
        return entityId != null ? Identifiable.getInstance(newEntity, entityId) : oldEntity;
    }

    /**
     * @param <T>           Entity type
     * @param <I>           Entity id type
     * @param <C>           Entity collection type
     * @param newCollection Entity collection supplier
     * @param newEntity     Entity supplier
     * @param entityIdList  New entities' ID list
     * @return Collection of entities with these IDs.
     * @implNote Collection supplier cannot be null.
     */
    public <T extends Identifiable<I>, I, C extends Collection<T>> C getEntitiesWithIds(@NonNull Supplier<C> newCollection, Supplier<T> newEntity, Collection<I> entityIdList) {
        if (entityIdList == null || newEntity == null) return newCollection.get();
        C entities = newCollection.get();
        for (I id : entityIdList) entities.add(Identifiable.getInstance(newEntity, id));
        return entities;
    }

}
