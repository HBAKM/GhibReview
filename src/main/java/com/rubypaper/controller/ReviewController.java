package com.rubypaper.controller;

import java.util.Date;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rubypaper.domain.Animation;
import com.rubypaper.domain.Review;
import com.rubypaper.domain.User;
import com.rubypaper.repository.AnimationRepository;
import com.rubypaper.repository.ReviewRepository;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private AnimationRepository animationRepository;
    
    // 리뷰 상세 페이지 조회
    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model, HttpSession session) {
    	// url에서 받은 id로 리뷰 조회
        reviewRepository.findById(id).ifPresent(review -> {
        	// 리뷰가 존재하면 모델에 추가
            model.addAttribute("review", review);
            
            // 현재 로그인한 사용자 정보 가져오기
            User loginUser = (User) session.getAttribute("loggedInUser");
            
            // 로그인한 사용자가 리뷰 작성자인지 확인
            if (loginUser != null && loginUser.getUserId().equals(review.getUser().getUserId())) {
                // 작성자라면 수정/삭제 버튼을 보여주기 위한 플래그 설정
                model.addAttribute("isAuthor", true);
            }
        });
        
        return "review/detail";
    }
    
    // 리뷰 작성 폼 페이지 조회
    @GetMapping("/write")
    public String writeForm(Model model, HttpSession session, 
                           @RequestParam(required = false) Long animationId) {
    	 
    	//　로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
    	if (session.getAttribute("loggedInUser") == null) {
            return "redirect:/login";
        }
        
        // 리뷰 작성 시 선택할 수 있는 모든 애니메이션 목록을 모델에 추가
        model.addAttribute("animations", animationRepository.findAll());
        
        // URL 파라미터로 특정 애니메이션 ID가 전달된 경우
        if (animationId != null) {
            // 해당 애니메이션을 미리 선택된 상태로 설정
            model.addAttribute("selectedAnimationId", animationId);
        }
        
        return "review/write";
    }
    
    // 리뷰 작성 처리 (폼 데이터 저장)
    @PostMapping("/write")
    public String write(@RequestParam Long animationId,
                       @RequestParam String title,
                       @RequestParam String content,
                       HttpSession session) {
    	
    	// 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
    	User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        
        // 선택된 애니메이션이 실제로 존재하는지 확인
        Animation animation = animationRepository.findById(animationId).orElse(null);
        if (animation == null) {
            // 애니메이션이 존재하지 않으면 메인 페이지로 리다이렉트
            return "redirect:/";
        }
        
        // 새로운 리뷰 객체 생성 및 데이터 설정
        Review review = new Review();
        review.setAnimation(animation);  // 선택된 애니메이션 설정
        review.setUser(user);           // 현재 로그인한 사용자 설정
        review.setTitle(title);         // 폼에서 입력받은 제목 설정
        review.setContent(content);     // 폼에서 입력받은 내용 설정
        review.setReviewDate(new Date()); // 현재 시간을 작성일로 설정
        
        // 데이터베이스에 리뷰 저장
        reviewRepository.save(review);
        
        // 리뷰 작성 완료 후 해당 애니메이션의 상세 페이지로 리다이렉트
        return "redirect:/animation/" + animationId;
    }
    
    // 리뷰 수정 폼 페이지 조회
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, HttpSession session) {
    	// 로그인 체크
    	User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        
        // 수정할 리뷰 조회
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            // 리뷰가 존재하지 않으면 메인 페이지로 리다이렉트
            return "redirect:/";
        }
        
        // 작성자 권한 체크
        if (!user.getUserId().equals(review.getUser().getUserId())) {
            // 작성자가 아니면 리뷰 상세 페이지로 리다이렉트
            return "redirect:/review/" + id;
        }
        
        // 수정할 리뷰 데이터를 모델에 추가
        model.addAttribute("review", review);
        
        return "review/edit";
    }
    
    // 리뷰 수정 처리 
    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id,
                      @RequestParam String title,
                      @RequestParam String content,
                      HttpSession session) {
    	// 로그인 체크
    	User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        
        // 수정할 리뷰 조회
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return "redirect:/";
        }
        
        // 작성자 권한 체크
        if (!user.getUserId().equals(review.getUser().getUserId())) {
            return "redirect:/review/" + id;
        }
        
        // 리뷰 데이터 업데이트
        review.setTitle(title);
        review.setContent(content);
        
        // 수정된 리뷰를 데이터베이스에 저장
        reviewRepository.save(review);
        
        return "redirect:/review/" + id;
    }
    
    // 리뷰 삭제 처리
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
    	// 로그인 체크
    	User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        
        // 삭제할 리뷰 조회
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return "redirect:/";
        }
        
        // 작성자 권한 체크
        if (!user.getUserId().equals(review.getUser().getUserId())) {
            return "redirect:/review/" + id;
        }
        
        // 리뷰 삭제 후에는 review 객체에 접근할 수 없으므로 삭제 후 리다이렉트할 애니메이션 ID 미리 저장
        Long animationId = review.getAnimation().getAnimationId();
        
        // 데이터베이스에서 리뷰 삭제
        reviewRepository.delete(review);
        
        return "redirect:/animation/" + animationId;
    }
    
}