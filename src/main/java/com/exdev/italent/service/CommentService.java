package com.exdev.italent.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.exdev.italent.model.Advertisement;
import com.exdev.italent.model.Comment;
import com.exdev.italent.obj.AdvertisementObj;
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

	private void fillCommentObj(Comment comment, CommentObj obj) {
		obj.setId(comment.getId());
		obj.setRate(comment.getRate());
		obj.setCreateDate(comment.getCreateDate());
		obj.setDisabled(comment.getDisabled());
		obj.setName(comment.getName());
		obj.setComments(comment.getComments());
		
		AdvertisementObj advertisementObj = new AdvertisementObj();
		fillAdvertisementObj(comment.getAdvertisement(), advertisementObj);
		
		obj.setAdvertisement(advertisementObj);
	}

	public List<CommentObj> listComments(int adid,int start, int max) {
		
		List<Comment> comments = em.createNamedQuery("Comment.findAll",Comment.class).setParameter("adid", adid).setFirstResult(start).setMaxResults(max).getResultList();
		List<CommentObj> objs = new ArrayList<CommentObj>(comments.size());
		for (Comment comment : comments) {
			CommentObj obj = new CommentObj();
			fillCommentObj(comment, obj);
			objs.add(obj);
		}
		return objs;
	}

	public void deleteComment(int id) {
		Comment comment = em.find(Comment.class, id);
		em.getTransaction().begin();
		em.remove(comment);
		em.getTransaction().commit();
	}
}
