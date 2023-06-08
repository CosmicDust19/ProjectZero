package me.projectzero.service.common.abstraction.objectmapping;

import org.mapstruct.MappingTarget;

public interface MapStructMapper<S, D> extends ReversibleMapper<S, D>, MultiMapper<S, D>, UpdateMapper<S, D> {

    @Override
    D map(S source, @MappingTarget D destination);

}
