package kr.co.board.persistence.entity;

import jakarta.persistence.*;
import kr.co.board.persistence.BaseEntity;
import lombok.Getter;

@Entity
@Table
@Getter
@AttributeOverride(
        name = "id",
        column = @Column(name = "comment_id")
)
public class Comment extends BaseEntity {

    @Column(name = "post_content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}
