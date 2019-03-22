package com.test.spring.boot.blog.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity // 实体
@Data
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id; // 用户的唯一标识

    @NotEmpty(message = "评分不能为空")
    @Size(min=1, max=50)
    @Column(nullable = false, length = 50) // 映射为字段，值不能为空
    private String  gradeContent;

    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

//    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
//    @JoinColumn(name="blog_id")
//    private Blog blog;
    @Column(nullable = false) // 映射为字段，值不能为空
      private Long blogId;

    @Column(nullable = false) // 映射为字段，值不能为空
    @CreationTimestamp  // 由数据库自动创建时间
    private Timestamp createTime;

    protected Grade() {
        // TODO Auto-generated constructor stub
    }
    public Grade(User user, String grade,Long  blogId) {
        this.gradeContent = grade;
        this.user = user;
        this.blogId = blogId;
    }
}
