package com.example.demo.Comment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>  {



	Page<Comment> findByConferenceId(Long conferenceId, Pageable pageable);

	java.util.List<Comment> findByConferenceIdEquals(Long conferenceId);

}
