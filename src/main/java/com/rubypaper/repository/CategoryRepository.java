package com.rubypaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rubypaper.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    
    // 카테고리명으로 조회
    Category findByCategoryName(String categoryName);
    
    // 카테고리명 존재 여부 확인
    boolean existsByCategoryName(String categoryName);
}
