package me.projectzero.service.common.abstraction.objectmapping;

public interface ReversibleMapper<S, D> extends Mapper<S, D> {

    S reverse(D destination);

}
