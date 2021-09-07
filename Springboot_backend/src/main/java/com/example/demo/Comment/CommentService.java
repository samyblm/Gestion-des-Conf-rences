package com.example.demo.Comment;

//import com.example.;
//import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.Articles.Article;
import com.example.demo.AppUser.AppUserRole;
import com.example.demo.AppUser.AppUser;
// import com.example.demo.payload.ApiResponse;
// import com.example.demo.payload.CommentRequest;
// import com.example.demo.payload.PagedResponse;
import com.example.demo.Conference.Conference;
import com.example.demo.Conference.ConferenceRepository;
import com.example.demo.AppUser.AppUserRepository;
//import com.example.demo.security.UserPrincipal;
//import com.example.demo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentService {
    private static final String THIS_COMMENT = " this comment";

	private static final String YOU_DON_T_HAVE_PERMISSION_TO = "You don't have permission to ";

	private static final String ID_STR = "id";

	private static final String COMMENT_STR = "Comment";

	private static final String POST_STR = "Post";

	private static final String COMMENT_DOES_NOT_BELONG_TO_POST = "Comment does not belong to post";



	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Autowired
	private AppUserRepository appUserRepository;


	public PagedResponse<Comment> getAllComments(Long conferenceId, int page, int size) {
		AppUtils.validatePageNumberAndSize(page, size);
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,"name");

		Page<Comment> comments = commentRepository.findByConferenceId(conferenceId, pageable);

		return new PagedResponse<>(comments.getContent(), comments.getNumber(), comments.getSize(),
        comments.getTotalElements(), comments.getTotalPages(), comments.isLast());
    }


	public java.util.List<Comment> getAllComment(Long conferenceId) {
		java.util.List<com.example.demo.Comment.Comment> comments= commentRepository.findByConferenceIdEquals(conferenceId);;
		return  comments ;
	}

    
	public Comment addComment(CommentRequest commentRequest, Long conferenceId, AppUser currentUser) {
		Conference conference = conferenceRepository.findById(conferenceId)
				.orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, conferenceId));
		AppUser user = appUserRepository.getUser(currentUser);
		Comment comment = new Comment(commentRequest.getBody());
		comment.setUser(user);
		comment.setConference(conference);
        comment.setName(currentUser.getFirstName());
		comment.setEmail(currentUser.getUsername());
		comment.setCreated_At(LocalDateTime.now());
		return commentRepository.save(comment);
	}

	public Comment getComment(Long conferenceId, Long id) {
		Conference conference = conferenceRepository.findById(conferenceId)
				.orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, conferenceId));
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));
		if (comment.getConference().getId().equals(conference.getId())) {
			return comment;
		}

		throw new BlogapiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
	}
    public Comment updateComment(Long conferenceId, Long id, CommentRequest commentRequest,
			AppUser currentUser) {
		Conference conference = conferenceRepository.findById(conferenceId)
				.orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, conferenceId));
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));

		if (!comment.getConference().getId().equals(conference.getId())) {
			throw new BlogapiException(HttpStatus.BAD_REQUEST, COMMENT_DOES_NOT_BELONG_TO_POST);
		}

		if (comment.getUser().getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(AppUserRole.ADMIN.toString()))) {
			comment.setBody(commentRequest.getBody());
			return commentRepository.save(comment);
		}

		throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "update" + THIS_COMMENT);
	}
    public ApiResponse deleteComment(Long conferenceId, Long id, AppUser currentUser) {
		Conference conference = conferenceRepository.findById(conferenceId)
				.orElseThrow(() -> new ResourceNotFoundException(POST_STR, ID_STR, conferenceId));
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(COMMENT_STR, ID_STR, id));

		if (!comment.getConference().getId().equals(conference.getId())) {
			return new ApiResponse(Boolean.FALSE, COMMENT_DOES_NOT_BELONG_TO_POST);
		}

		if (comment.getUser().getId().equals(currentUser.getId())
				|| currentUser.getAuthorities().contains(new SimpleGrantedAuthority(AppUserRole.ADMIN.toString()))) {
			commentRepository.deleteById(comment.getId());
			return new ApiResponse(Boolean.TRUE, "You successfully deleted comment");
		}

		throw new BlogapiException(HttpStatus.UNAUTHORIZED, YOU_DON_T_HAVE_PERMISSION_TO + "delete" + THIS_COMMENT);
	}

}