package com.nnam01.MyStudy.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate // 변경된 필드만 업데이트
@SQLRestriction("deleted = false") // 삭제되지 않은 것만 조회
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?") // Delete를 deleted 플래그를 true로 대신
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 70)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    @Column(nullable = false)
    private Boolean deleted = false;

    public User(String username, String password, String email, String imageUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.imageUrl = imageUrl;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
        this.deleted = false;
    }
}
