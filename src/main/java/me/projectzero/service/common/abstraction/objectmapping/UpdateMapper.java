package me.projectzero.service.common.abstraction.objectmapping;

public interface UpdateMapper<S, D> extends Mapper<S, D> {

    D map(S source, D destination);

}
