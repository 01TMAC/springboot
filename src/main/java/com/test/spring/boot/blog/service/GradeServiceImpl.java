package com.test.spring.boot.blog.service;

import com.test.spring.boot.blog.dao.CommentRepository;
import com.test.spring.boot.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Comment 服务.
 * 
 * @since 1.0.0 2017年4月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private CommentRepository GradeRepository;
	/* (non-Javadoc)
	 * @see com.waylau.spring.boot.blog.service.CommentService#removeComment(java.lang.Long)
	 */
	@Override
	@Transactional
	public void removeGrade(Long id) {
		GradeRepository.delete(id);
	}
	
	@Override
	public Comment getGradeById(Long id) {
		return GradeRepository.findOne(id);
	}

}
