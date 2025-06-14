package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Review;
import com.rubypaper.repository.ReviewRepository;

@Service
public class ReviewService {
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    // 모든 리뷰 조회
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    
    // ID로 리뷰 조회
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }
    
    // 애니메이션별 리뷰 조회
    public List<Review> getReviewsByAnimationId(Long animationId) {
        return reviewRepository.findByAnimation_AnimationIdOrderByReviewDateDesc(animationId);
    }
    
    // 사용자별 리뷰 조회
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUser_UserIdOrderByReviewDateDesc(userId);
    }
    
    // 리뷰 저장
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
    
    // 리뷰 삭제
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
    
    // 최근 리뷰 조회
    public List<Review> getRecentReviews() {
        return reviewRepository.findTop10ByOrderByReviewDateDesc();
    }
    
    // 사용자가 작성한 리뷰 개수
    public long getReviewCountByUserId(Long userId) {
        return reviewRepository.countByUser_UserId(userId);
    }
    
    // 리뷰 제목으로 검색
    public List<Review> searchReviewsByTitle(String keyword) {
        return reviewRepository.findByTitleContaining(keyword);
    }
    
    // 리뷰 내용으로 검색
    public List<Review> searchReviewsByContent(String keyword) {
        return reviewRepository.findByContentContaining(keyword);
    }
    
    // 특정 사용자가 특정 애니메이션에 대한 리뷰를 작성했는지 확인
    public boolean hasUserReviewedAnimation(Long userId, Long animationId) {
        return reviewRepository.existsByUser_UserIdAndAnimation_AnimationId(userId, animationId);
    }
}
