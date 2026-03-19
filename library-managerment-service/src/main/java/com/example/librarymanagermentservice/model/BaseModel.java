package com.example.librarymanagermentservice.model;

import com.example.librarymanagermentservice.common.message.GenreMessageError;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Information about base model.
 */
@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @NotNull(message = GenreMessageError.ACTIVE_IS_REQUIRED)
    @Column(nullable = false)
    private Boolean active = true;
}