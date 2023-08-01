package com.bfc.appmgmt.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.bfc.appmgmt.domain
 * fileName       : DbHealthy
 * author         : joyousang
 * date           : 2023/08/01
 * description    :
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@SequenceGenerator(
        name = "DB_HEALTHY_SEQ_GENERATOR"
        , sequenceName = "DB_HEALTHY_SEQ"
        , initialValue = 1
        , allocationSize = 1
)
@Getter
public class DbHealthy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "db_healthy_id")
    private Long id;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime checkedAt;

}
