package kr.co.board.api.persistence.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.co.board.api.persistence.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_comments")
@Getter
@Where(clause = "comment_deleted = false") // 쿼리를 실행할 때 삭제되지 않은 엔티티만 조회
@SQLDelete(sql = "UPDATE tbl_comments SET comment_deleted = true WHERE comment_id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(
	name = "id",
	column = @Column(name = "comment_id")
)
public class Comment extends BaseEntity {

	@Column(name = "comment_content")
	private String content;

	@Column(name = "comment_deleted")
	private Boolean deleted = Boolean.FALSE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	private Comment(String content, Post post) {
		this.content = content;
		this.post = post;
	}

	public static Comment create(String content, Post post) {
		return new Comment(content, post);
	}

	public void update(String content) {
		this.content = content;
	}
}
