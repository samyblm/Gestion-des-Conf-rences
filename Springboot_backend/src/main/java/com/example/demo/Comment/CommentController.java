package com.example.demo.Comment;



import com.example.demo.AppUser.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/conference/{conferenceId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<PagedResponse<Comment>> getAllComments(@PathVariable(name = "conferenceId") Long conferenceId,
    @RequestParam(name = "page", required = false) Integer page,
	@RequestParam(name = "size", required = false) Integer size) {
    PagedResponse<Comment> allComments = commentService.getAllComments(conferenceId,page,size);

    return new ResponseEntity<>(allComments,HttpStatus.OK);
    }

	@GetMapping("/get")
	public ResponseEntity<java.util.List<Comment>> getAllComment(@PathVariable(name = "conferenceId") Long conferenceId) {
		java.util.List<Comment> allComments =  commentService.getAllComment(conferenceId);

		return new ResponseEntity(allComments,HttpStatus.OK);
	}






    @PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Comment> addComment(@Validated @RequestBody CommentRequest commentRequest,
			@PathVariable(name = "conferenceId") Long conferenceId, @CurrentUser AppUser currentUser) {
		Comment newComment = commentService.addComment(commentRequest, conferenceId, currentUser);

		return new ResponseEntity<>(newComment, HttpStatus.CREATED);
	}




	@GetMapping("/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable(name = "conferenceId") Long conferenceId,
			@PathVariable(name = "id") Long id) {
		Comment comment = commentService.getComment(conferenceId, id);

		return new ResponseEntity<>(comment, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<Comment> updateComment(@PathVariable(name = "conferenceId") Long conferenceId,
			@PathVariable(name = "id") Long id, @Validated @RequestBody CommentRequest commentRequest,
			@CurrentUser AppUser currentUser) {

		Comment updatedComment = commentService.updateComment(conferenceId, id, commentRequest, currentUser);

		return new ResponseEntity<>(updatedComment, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable(name = "conferenceId") Long conferenceId,
			@PathVariable(name = "id") Long id, @CurrentUser AppUser currentUser) {

		ApiResponse response = commentService.deleteComment(conferenceId, id, currentUser);

		HttpStatus status = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;

		return new ResponseEntity<>(response, status);
	}
}