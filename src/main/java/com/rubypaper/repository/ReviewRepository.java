package com.rubypaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    
	// 애니메이션별 리뷰 조회 (최신순)
    List<Review> findByAnimation_AnimationIdOrderByReviewDateDesc(Long animationId);
    
    // 사용자별 리뷰 조회 (최신순) 
    List<Review> findByUser_UserIdOrderByReviewDateDesc(Long userId);
    
    // 최근 리뷰 10개 조회
    List<Review> findTop10ByOrderByReviewDateDesc();
    
    // 특정 사용자의 특정 애니메이션에 대한 리뷰 여부 확인
    boolean existsByUser_UserIdAndAnimation_AnimationId(Long userId, Long animationId);
    
    // 리뷰 제목으로 검색
    @Query("SELECT r FROM Review r WHERE r.title LIKE %?1% ORDER BY r.reviewDate DESC")
    List<Review> findByTitleContaining(String keyword);
    
    // 리뷰 내용으로 검색
    @Query("SELECT r FROM Review r WHERE r.content LIKE %?1% ORDER BY r.reviewDate DESC")
    List<Review> findByContentContaining(String keyword);
    
    // 사용자가 작성한 리뷰 개수
    long countByUser_UserId(Long userId);
}