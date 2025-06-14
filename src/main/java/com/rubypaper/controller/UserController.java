package com.rubypaper.controller;

import java.util.List;

import com.rubypaper.domain.Review;
import com.rubypaper.domain.User;
import com.rubypaper.repository.ReviewRepository;
import com.rubypaper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    // 회원가입 페이지 표시
    @GetMapping("/signup")
    public String showsignupPage() {
        return "signup";
    }
    
    // 회원가입 처리
    @PostMapping("/signup")
    public String signupUser(@RequestParam String username,
                              @RequestParam String loginId,
                              @RequestParam String password,
                              @RequestParam String confirmPassword,
                              Model model) {
        
        // 입력값 검증
        if (username.trim().isEmpty() || loginId.trim().isEmpty() || password.trim().isEmpty()) {
            model.addAttribute("error", "모든 필드를 입력해주세요.");
            return "signup";
        }
        
        // 비밀번호 확인
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "signup";
        }
        
        // 회원가입 시도
        boolean success = userService.signupUser(username, loginId, password);
        
        if (success) {
            model.addAttribute("success", "회원가입이 완료되었습니다. 로그인해주세요.");
            return "login";
        } else {
            model.addAttribute("error", "이미 존재하는 로그인 ID입니다.");
            return "signup";
        }
    }
    
    // 로그인 페이지 표시
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    // 로그인 처리
    @PostMapping("/login")
    public String loginUser(@RequestParam String loginId,
                           @RequestParam String password,
                           HttpSession session,
                           Model model) {
        
        // 로그인 시도
        User user = userService.authenticateUser(loginId, password);
        
        if (user != null) {
            // 세션에 사용자 정보 저장
            session.setAttribute("loggedInUser", user);
            return "redirect:/"; // 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "로그인 ID 또는 비밀번호가 올바르지 않습니다.");
            return "login";
        }
    }
    
    // 마이페이지 표시
    @GetMapping("/mypage")
    public String showMyPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        
        if (loggedInUser == null) {
            return "redirect:/login"; // 로그인되지 않은 경우 로그인 페이지로
        }
        
        // 최신 사용자 정보 가져오기
        User currentUser = userService.findById(loggedInUser.getUserId()).orElse(null);
        if (currentUser == null) {
            session.invalidate();
            return "redirect:/login";
        }
        
        model.addAttribute("user", currentUser);
        
        // 사용자의 리뷰 목록 가져오기
        List<Review> userReviews = reviewRepository.findByUser_UserIdOrderByReviewDateDesc(currentUser.getUserId());
        model.addAttribute("reviews", userReviews);
        
        // 리뷰 통계 정보 추가
        long reviewCount = reviewRepository.countByUser_UserId(currentUser.getUserId());
        model.addAttribute("reviewCount", reviewCount);
        
        return "mypage";
    }
    
    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/";
    }
    
    // 로그인 ID 중복 확인
    @GetMapping("/check-loginid")
    @ResponseBody
    public boolean checkLoginId(@RequestParam String loginId) {
        return !userService.isLoginIdExists(loginId);
    }
}
