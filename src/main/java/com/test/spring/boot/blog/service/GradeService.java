package com.test.spring.boot.blog.service;

import com.test.spring.boot.blog.entity.Comment;

/**
 * Comment 服务接口.
 * 
 * @since 1.0.0 2017年4月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
public interface GradeService {
	/**
	 * 根据id获取 Comment
	 * @param id
	 * @return
	 */
	Comment getGradeById(Long id);
	/**
	 * 删除评论
	 * @param id
	 * @return
	 */
	void removeGrade(Long id);
}
