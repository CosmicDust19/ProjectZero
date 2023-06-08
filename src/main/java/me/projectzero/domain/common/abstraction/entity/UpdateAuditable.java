package me.projectzero.domain.common.abstraction.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public interface UpdateAuditable extends Entity {

    LocalDateTime getModifiedAt();

    void setModifiedAt(LocalDateTime modifiedAt);

    String getModifier();

    void setModifier(String modifier);

}
