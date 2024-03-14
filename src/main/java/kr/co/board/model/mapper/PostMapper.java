package kr.co.board.model.mapper;

import kr.co.board.model.dtos.request.PostReqDto;
import kr.co.board.persistence.entity.Post;
import lombok.Getter;

@Getter
public class PostMapper {

    public static Post create(PostReqDto dto) {
        return new Post(dto.title(), dto.content());
    }
}
