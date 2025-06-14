package com.rubypaper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubypaper.domain.Animation;
import com.rubypaper.repository.AnimationRepository;

@Service
public class AnimationService {
    
    @Autowired
    private AnimationRepository animationRepository;
    
    // 모든 애니메이션 조회
    public List<Animation> getAllAnimations() {
        return animationRepository.findAll();
    }
    
    // ID로 애니메이션 조회
    public Animation getAnimationById(Long animationId) {
        return animationRepository.findById(animationId).orElse(null);
    }
    
    // 카테고리별 애니메이션 조회
    public List<Animation> getAnimationsByCategory(Long categoryId) {
        return animationRepository.findByCategoryCategoryId(categoryId);
    }
    
    // 애니메이션 저장
    public Animation saveAnimation(Animation animation) {
        return animationRepository.save(animation);
    }
    
    // 애니메이션 삭제
    public void deleteAnimation(Long animationId) {
        animationRepository.deleteById(animationId);
    }
    
    // 제목으로 애니메이션 검색
    public List<Animation> searchAnimationsByTitle(String title) {
        return animationRepository.findByTitleContainingIgnoreCase(title);
    }
    
    // 감독으로 애니메이션 검색
    public List<Animation> searchAnimationsByDirector(String director) {
        return animationRepository.findByDirectorContainingIgnoreCase(director);
    }
}
