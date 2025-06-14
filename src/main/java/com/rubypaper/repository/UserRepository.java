package com.rubypaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.User;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	// 로그인 ID로 사용자 찾기
    Optional<User> findByLoginId(String loginId);
    
    // 로그인 ID 중복 체크
    boolean existsByLoginId(String loginId);
}