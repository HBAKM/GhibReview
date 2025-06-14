package com.rubypaper.service;

import com.rubypaper.domain.User;
import com.rubypaper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // 회원가입
    public boolean signupUser(String username, String loginId, String password) {
        // 로그인 ID 중복 확인
        if (userRepository.existsByLoginId(loginId)) {
            return false; // 로그인 ID 중복
        }
        
        // 사용자 생성 및 저장
        User user = new User();
        user.setUsername(username);
        user.setLoginId(loginId);
        user.setPassword(password);
        
        userRepository.save(user);
        return true;
    }
    
    // 로그인 검증
    public User authenticateUser(String loginId, String password) {
        Optional<User> userOptional = userRepository.findByLoginId(loginId);
        
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 비밀번호 평문 비교
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        
        return null; // 로그인 실패
    }
    
    // 로그인 ID 중복 확인
    public boolean isLoginIdExists(String loginId) {
        return userRepository.existsByLoginId(loginId);
    }
    
    // 사용자 ID로 사용자 찾기
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }
    
    // 로그인 ID로 사용자 찾기
    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }
}