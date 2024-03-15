package kr.co.board.api.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.co.board.api.persistence.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	@Query("SELECT p.title FROM Post p WHERE p.id > :lastId ORDER BY p.createAt ASC")
	List<String> findPostTitlesAfterLastId(@Param("lastId") Long lastId, Pageable pageable);
}
