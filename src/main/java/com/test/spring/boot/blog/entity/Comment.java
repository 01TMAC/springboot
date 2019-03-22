package com.test.spring.boot.blog.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * Comment 实体
 * 
 * @since 1.0.0 2017年4月9日
 * @author <a href="https://waylau.com">Way Lau</a>
 */
@Entity // 实体
@Data
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id // 主键
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
	private Long id; // 用户的唯一标识

	@NotEmpty(message = "评论内容不能为空")
	@Size(min=2, max=500)
	@Column(nullable = false) // 映射为字段，值不能为空
	private String content;

	@Column(nullable = false) // 映射为字段，值不能为空
	private long blogId;
//	@Range(min = 0,max = 5 ,message="分数范围: 0-5分")
//    @NotEmpty(message = "评分不能为空")
	@Column(nullable = false)
	@Range(min = 0,max = 5 ,message="分数范围: 0-5分")
	private int gradeContent;

	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(nullable = false) // 映射为字段，值不能为空
	@CreationTimestamp  // 由数据库自动创建时间
	private Timestamp createTime;
 
	protected Comment() {
		// TODO Auto-generated constructor stub
	}
	public Comment(User user, String content) {
		this.content = content;
		this.user = user;
	}
	public Comment(User user, String content,Long blogId,int gradeContent) {
		this.content = content;
		this.user = user;
		this.blogId = blogId;
		this.gradeContent = gradeContent;
	}
}
