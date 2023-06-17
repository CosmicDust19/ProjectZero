package me.projectzero.repository.common.dataseeder;

import me.projectzero.domain.common.objectcache.RoleCache;
import me.projectzero.domain.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDataSeeder extends AbstractDataSeeder<Role, Integer> {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    protected void prepare(final List<Role> entities) {
        entities.addAll(RoleCache.all(true));
    }
}
