package me.projectzero.repository.common.dataseeder;

import lombok.Setter;
import me.projectzero.domain.entity.interfaces.IdentifiableEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @implSpec The entity type should have a {@link JpaRepository} bean.
 */
public abstract class AbstractDataSeeder<T extends IdentifiableEntity<I>, I> implements Ordered {

    @Setter(onMethod = @__(@Autowired))
    protected JpaRepository<T, I> repository;

    private final List<T> entities = new ArrayList<>();

    /**
     * {@code seed()} will run on this condition
     */
    protected boolean condition() {
        return repository.count() == 0;
    }

    /**
     * Add elements to {@code entities} list to seed
     *
     * @param entities entities to seed.
     */
    protected abstract void prepare(final List<T> entities);

    /**
     * @implNote All IDs will be set to null before saving.
     */
    public synchronized void seed() {
        if (!condition()) return;
        prepare(entities);
        entities.forEach(entity -> entity.setId(null));
        repository.saveAll(entities);
    }

    /**
     * Cleanup function to free up used space during seeding
     */
    public void cleanup() {
        entities.clear();
    }

}
