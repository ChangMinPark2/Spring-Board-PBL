package kr.co.board.persistence.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kr.co.board.persistence.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
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
}
