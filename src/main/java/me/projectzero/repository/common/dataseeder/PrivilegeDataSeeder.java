package me.projectzero.repository.common.dataseeder;

import me.projectzero.domain.common.objectcache.PrivilegeCache;
import me.projectzero.domain.entity.Privilege;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrivilegeDataSeeder extends AbstractDataSeeder<Privilege, Integer> {

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    protected void prepare(final List<Privilege> entities) {
        entities.addAll(PrivilegeCache.all());
    }

}
