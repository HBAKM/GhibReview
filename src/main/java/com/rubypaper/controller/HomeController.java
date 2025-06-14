package com.rubypaper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rubypaper.service.AnimationService;
import com.rubypaper.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.rubypaper.domain.Animation;
import com.rubypaper.domain.Category;
import com.rubypaper.domain.Review;
import com.rubypaper.repository.AnimationRepository;
import com.rubypaper.repository.CategoryRepository;
import com.rubypaper.util.ImageUtil;

@Controller
public class HomeController {
    
    @Autowired
    private AnimationRepository animationRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private AnimationService animationService;
    
    @Autowired
    private ReviewService reviewService;
    
    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) Long categoryId) {
        // 모든 카테고리 가져오기
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        
        // 애니메이션 목록 가져오기 (카테고리 필터링)
        List<Animation> animations;
        if (categoryId != null) {
            // 특정 카테고리의 애니메이션만 가져오기
            animations = animationRepository.findByCategoryCategoryId(categoryId);
            model.addAttribute("selectedCategoryId", categoryId);
        } else {
            // 모든 애니메이션 가져오기
            animations = animationRepository.findAll();
        }
        
        // 이미지 이름 매핑 추가
        Map<Long, String> imageNames = new HashMap<>();
        for (Animation animation : animations) {
            String imageName = ImageUtil.getImageName(animation.getTitle());
            imageNames.put(animation.getAnimationId(), imageName);
        }
        
        model.addAttribute("imageNames", imageNames);
        
        model.addAttribute("animations", animations);
        
        return "index";
    }
    
    @GetMapping("/animation/{id}")
    public String showAnimationDetail(@PathVariable("id") Long id, Model model) {
        Animation animation = animationService.getAnimationById(id);
        if (animation == null) {
            return "redirect:/"; // 애니메이션이 없으면 홈으로
        }

        // 애니메이션 정보
        model.addAttribute("animation", animation);

        // 리뷰 목록 최신순 정렬
        List<Review> reviewList = reviewService.getReviewsByAnimationId(id);
        model.addAttribute("reviewList", reviewList);

        // 이미지 이름
        Map<Long, String> imageNames = new HashMap<>();
        String imageName = ImageUtil.getImageName(animation.getTitle());
        imageNames.put(animation.getAnimationId(), imageName);
        model.addAttribute("imageNames", imageNames);


        return "animation/detail";
    }



}