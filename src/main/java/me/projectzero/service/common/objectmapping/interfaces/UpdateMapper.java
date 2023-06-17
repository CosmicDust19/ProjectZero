package me.projectzero.service.common.objectmapping.interfaces;

public interface UpdateMapper<S, D> extends Mapper<S, D> {

    D map(S source, D destination);

}
