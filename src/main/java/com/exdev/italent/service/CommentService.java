package com.exdev.italent.service;

import java.util.Date;

import com.exdev.italent.model.Comment;
import com.exdev.italent.obj.CommentObj;

public class CommentService extends BaseService {
	public void createComment(CommentObj obj) {
		
		Comment comment = new Comment();
		
		comment.setCreateDate(new Date());
		
		fillComment(obj, comment);
		
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		
		obj.setId(comment.getId());
		
		

	}

	public void updateComment(int id, CommentObj obj) {
		Comment comment = em.find(Comment.class, id);
		if(comment == null) return;
		fillComment(obj, comment);

		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
	}

	public CommentObj getComment(int id) {
		Comment comment = em.find(Comment.class, id);
		if(comment == null) return null;
		CommentObj obj = new CommentObj();
		fillCommentObj(comment, obj);
		
		return obj;
	}

	public void deleteComment(int id) {
		Comment comment = em.find(Comment.class, id);
		em.getTransaction().begin();
		em.remove(comment);
		em.getTransaction().commit();
	}
}
