package com.nnam01.MyStudy.comment.domain;

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
@Table(name = "comments")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class) // 시간 자동 설정
@DynamicUpdate // 변경된 필드만 업데이트
@SQLRestriction("deleted = false") // 삭제되지 않은 것만 조회
@SQLDelete(sql = "UPDATE comments SET deleted = true WHERE id = ?") // Delete를 deleted 플래그를 true로 대신
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "post_id", nullable = false)
  private Long postId;

  @Column(name = "author_id", nullable = false)
  private Long authorId;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String content;

  @CreatedDate
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @Column(nullable = false)
  private Boolean deleted = false;

  public Comment(Long postId, Long authorId, String content) {
    this.postId = postId;
    this.authorId = authorId;
    this.content = content;
    this.createdAt = LocalDateTime.now();
    this.modifiedAt = LocalDateTime.now();
    this.deleted = false;
  }
}
