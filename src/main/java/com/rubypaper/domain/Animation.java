package com.rubypaper.domain;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "ANIMATION")
@Data
@ToString(exclude = "category")
public class Animation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANIMATION_ID")
    private Long animationId;
    
    @Column(name = "TITLE", nullable = false)
    private String title;
    
    @Column(name = "DIRECTOR")
    private String director;
    
    @Column(name = "DESCRIPTION", length = 2000)
    private String description;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "RELEASE_DATE")
    private Date releaseDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

	public Long getAnimationId() {
		return animationId;
	}

	public void setAnimationId(Long animationId) {
		this.animationId = animationId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Animation [animationId=" + animationId + ", title=" + title + ", director=" + director
				+ ", description=" + description + ", releaseDate=" + releaseDate + ", category=" + category + "]";
	}
    
    
}