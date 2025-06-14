package com.rubypaper.domain;

import java.util.Date;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "REVIEW")
@Data
@ToString(exclude = {"animation", "user"})
public class Review {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_ID")
    private Long reviewId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANIMATION_ID")
    private Animation animation;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;
    
    @Column(name = "TITLE", nullable = false)
    private String title;
    
    @Column(name = "CONTENT", length = 4000)
    private String content;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "REVIEW_DATE")
    private Date reviewDate;

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", animation=" + animation + ", user=" + user + ", title=" + title
				+ ", content=" + content + ", reviewDate=" + reviewDate + "]";
	}
    
    
}