package com.exdev.italent.service;

import java.util.Date;

import com.exdev.italent.model.Advertisement;
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

	private void fillComment(CommentObj obj, Comment comment) {
		comment.setComments(obj.getComments());
		comment.setName(obj.getName());
		comment.setDisabled(obj.isDisabled());
		comment.setRate(obj.getRate());
		Advertisement ad = em.find(Advertisement.class, obj.getAdvertisement().getId());
		comment.setAdvertisement(ad);
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
