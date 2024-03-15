package kr.co.board.api.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.board.api.dto.request.post.PostCreateDto;
import kr.co.board.api.dto.request.post.PostUpdateDto;
import kr.co.board.api.dto.response.post.PostResDto;
import kr.co.board.api.persistence.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_posts")
@Getter
@Where(clause = "post_deleted = false") // 쿼리 실행 시 삭제되지 않은 엔티티만 조회
@SQLDelete(sql = "UPDATE tbl_posts SET post_deleted = true WHERE post_id = ?")
@NoArgsConstructor
@AttributeOverride(
	name = "id",
	column = @Column(name = "post_id")
)
public class Post extends BaseEntity {

	@Column(name = "post_title")
	private String title;

	@Column(name = "post_content")
	private String content;

	@Column(name = "post_date")
	private LocalDateTime createAt;

	@Column(name = "post_deleted")
	private Boolean deleted = Boolean.FALSE;

	private Post(String title, String content) {
		this.title = title;
		this.content = content;
		this.createAt = LocalDateTime.now();
	}

	public static Post create(PostCreateDto dto) {
		return new Post(dto.title(), dto.content());
	}

	public static PostResDto read(Post post, List<String> comments) {
		return PostResDto.builder()
			.title(post.getTitle())
			.content(post.getContent())
			.commentContents(comments)
			.build();
	}

	public void update(PostUpdateDto dto) {
		this.title = dto.title();
		this.content = dto.content();
	}
}
