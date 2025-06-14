package com.rubypaper.util;

import java.util.HashMap;
import java.util.Map;

public class ImageUtil {
    
    private static final Map<String, String> titleToImageMap = new HashMap<>();
    
    static {
        // 애니메이션 제목과 이미지 파일명 매핑
    	titleToImageMap.put("마루 밑 아리에티", "arietty");
    	titleToImageMap.put("아야와 마녀", "aya");
    	titleToImageMap.put("아야와 마녀", "ayato");
    	titleToImageMap.put("털벌레 보로", "boro");
    	titleToImageMap.put("게드전기: 어스시의 전설", "gedo");
    	titleToImageMap.put("기브리즈", "ghiblies");
    	titleToImageMap.put("기브리즈 에피소드 2", "ghiblies2");
    	titleToImageMap.put("고로의 대산책", "goro");
    	titleToImageMap.put("필름이 빙글빙글", "guru");
    	titleToImageMap.put("상상의 기계들 중 파괴의 발명", "hakai");
    	titleToImageMap.put("하울의 움직이는 성", "haul");
    	titleToImageMap.put("비행 도시 계획", "hiko");
    	titleToImageMap.put("별을 산 날", "hoshi");
    	titleToImageMap.put("반딧불이의 묘", "hotaru");
    	titleToImageMap.put("집 찾기", "ie");
    	titleToImageMap.put("카구야 공주 이야기", "kaguya");
    	titleToImageMap.put("붉은 거북", "kame");
    	titleToImageMap.put("바람이 분다", "kaze");
    	titleToImageMap.put("마녀배달부 키키", "kiki");
    	titleToImageMap.put("그대들은 어떻게 살 것인가", "kimi");
    	titleToImageMap.put("코쿠리코 언덕에서", "kokuriko");
    	titleToImageMap.put("고래 잡기", "kugira");
    	titleToImageMap.put("상상 속의 비행 기계들", "kusou");
    	titleToImageMap.put("천공의 섬 라퓨타", "laputa");
    	titleToImageMap.put("추억의 마니", "mani");
    	titleToImageMap.put("메이와 아기고양이버스", "mei");
    	titleToImageMap.put("귀를 기울이면", "mimi");
    	titleToImageMap.put("물거미 몽몽", "monmon");
    	titleToImageMap.put("모노노케 히메", "mononoke");
    	titleToImageMap.put("바람계곡의 나우시카", "nausica");
    	titleToImageMap.put("고양이의 보은", "neko");
    	titleToImageMap.put("추억은 방울방울", "omoi");
    	titleToImageMap.put("On Your Mark", "oym");
    	titleToImageMap.put("빵반죽과 계란 공주", "pan");
    	titleToImageMap.put("붉은 돼지", "Pig");
    	titleToImageMap.put("벼랑 위의 포뇨", "ponyo");
    	titleToImageMap.put("Portable Airport", "Portable");
    	titleToImageMap.put("보물찾기", "sagashi");
        titleToImageMap.put("센과 치히로의 행방불명", "sen");
        titleToImageMap.put("하늘색의 씨앗", "sora");
        titleToImageMap.put("Space Station No.9", "space");
        titleToImageMap.put("쥐의 스모", "sumo");
        titleToImageMap.put("폼포코 너구리 대작전", "tanuki");
        titleToImageMap.put("이웃집 토토로", "totoro");
        titleToImageMap.put("바다가 들린다", "umi");
        titleToImageMap.put("이웃집 야마다군", "yamada");
        titleToImageMap.put("젠-그로구과 더스트 버니", "zen");
        
    }
    
    public static String getImageName(String title) {
        return titleToImageMap.getOrDefault(title, "default");
    }
}