package me.projectzero.service.common.objectmapping.interfaces;

public interface ReversibleMapper<S, D> extends Mapper<S, D> {

    S reverse(D destination);

}
