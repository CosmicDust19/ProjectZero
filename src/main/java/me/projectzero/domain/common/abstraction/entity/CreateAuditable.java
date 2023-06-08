package me.projectzero.domain.common.abstraction.entity;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public interface CreateAuditable extends Entity {

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime createdAt);

    String getCreator();

    void setCreator(String creator);

}
