package me.projectzero.service.common.abstraction.objectmapping;

@FunctionalInterface
public interface Mapper<S, D> {

    D map(S source);

}
