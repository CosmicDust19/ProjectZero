package me.projectzero.service.common.objectmapping.interfaces;

@FunctionalInterface
public interface Mapper<S, D> {

    D map(S source);

}
