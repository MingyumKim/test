package com.test.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@MappedSuperclass
public class JpaBaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createDate = now;
    }
}
