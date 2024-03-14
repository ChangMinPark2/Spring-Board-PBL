package kr.co.board.model.mapper;

import kr.co.board.model.dtos.request.CommentReqDto;
import kr.co.board.persistence.entity.Comment;
import kr.co.board.persistence.entity.Post;

public class CommentMapper {
    public static Comment create(Post post, CommentReqDto dto) {
        return new Comment(dto.content(), post);
    }
}
