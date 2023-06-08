package me.projectzero.service.common.abstraction.objectmapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface MultiMapper<S, D> extends Mapper<S, D> {

    default List<D> map(Collection<? extends S> collection) {
        if (collection == null) return new ArrayList<>();
        List<D> destinations = new ArrayList<>(collection.size());
        for (S source : collection)
            destinations.add(map(source));
        return destinations;
    }

    default List<D> map(S[] array) {
        if (array == null) return new ArrayList<>();
        List<D> destinations = new ArrayList<>(array.length);
        for (S source : array)
            destinations.add(map(source));
        return destinations;
    }

}
