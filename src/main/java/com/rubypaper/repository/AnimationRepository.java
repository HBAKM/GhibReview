package com.rubypaper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Animation;

@Repository
public interface AnimationRepository extends JpaRepository<Animation, Long> {
    
    // 카테고리별 애니메이션 조회
    List<Animation> findByCategoryCategoryId(Long categoryId);
    
    // 제목으로 검색 (대소문자 구분 없음)
    List<Animation> findByTitleContainingIgnoreCase(String title);
    
    // 감독으로 검색 (대소문자 구분 없음)
    List<Animation> findByDirectorContainingIgnoreCase(String director);
    
    // 개봉일 순으로 정렬
    List<Animation> findAllByOrderByReleaseDateDesc();
    
    // 제목 순으로 정렬
    List<Animation> findAllByOrderByTitleAsc();
}
