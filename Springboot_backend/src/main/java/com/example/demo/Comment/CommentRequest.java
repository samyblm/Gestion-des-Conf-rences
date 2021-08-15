package com.example.demo.Comment;

import org.springframework.lang.NonNull;

import lombok.Data;

@Data
public class CommentRequest {
	@NonNull
	private String body;
	public  CommentRequest(){}

    public CommentRequest(@NonNull String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}

