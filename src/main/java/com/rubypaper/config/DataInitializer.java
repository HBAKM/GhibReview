package com.rubypaper.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rubypaper.domain.Animation;
import com.rubypaper.domain.Category;
import com.rubypaper.domain.Review;
import com.rubypaper.domain.User;
import com.rubypaper.repository.AnimationRepository;
import com.rubypaper.repository.CategoryRepository;
import com.rubypaper.repository.ReviewRepository;
import com.rubypaper.repository.UserRepository;

@Configuration
public class DataInitializer {

	@Bean
    public CommandLineRunner initData(CategoryRepository categoryRepository, 
                                     AnimationRepository animationRepository,
                                     UserRepository userRepository,
                                     ReviewRepository reviewRepository) {
        return args -> {
        	// 이미 데이터가 있는지 확인
            if (categoryRepository.count() > 0) {
                System.out.println("데이터가 이미 존재합니다. 초기화를 건너뜁니다.");
                return;
            }
            
            // 카테고리 데이터 초기화
            Category feature = new Category();
            feature.setCategoryName("극장 장편");
            categoryRepository.save(feature);
            
            Category tv = new Category();
            tv.setCategoryName("TV");
            categoryRepository.save(tv);
            
            Category short_ = new Category();
            short_.setCategoryName("단편");
            categoryRepository.save(short_);
            
            // 극장 장편 애니메이션 24 
            Animation nausica = createAnimation(animationRepository, "바람계곡의 나우시카", "미야자키 하야오", 
                    "오염된 세계에서 자연과 인간의 공존을 그린 작품", "1984-03-11", feature);
            
            Animation Pig = createAnimation(animationRepository, "붉은 돼지", "미야자키 하야오", 
                    "돼지로 변한 조종사 포르코 로소의 이야기", "1992-07-18", feature);
            
            Animation neko = createAnimation(animationRepository, "고양이의 보은", "모리타 히로유키", 
                    "고양이를 구해준 소녀가 고양이 왕국에서 벌이는 모험", "2002-07-20", feature);
            
            Animation kaze = createAnimation(animationRepository, "바람이 분다", "미야자키 하야오", 
                    "비행기를 사랑한 한 남자의 꿈과 사랑, 그리고 전쟁 속에서의 삶을 그린 애니메이션", "2013-07-20", feature);
            
            Animation laputa = createAnimation(animationRepository, "천공의 섬 라퓨타", "미야자키 하야오", 
                    "하늘에 떠 있는 성을 찾아 떠나는 모험", "1986-08-02", feature);
            
            Animation tanuki = createAnimation(animationRepository, "폼포코 너구리 대작전", "타카하타 이사오", 
                    "너구리들이 인간과 공존을 위해 벌이는 유쾌하고도 씁쓸한 대작전", "1994-07-16", feature);
            
            Animation howl = createAnimation(animationRepository, "하울의 움직이는 성", "미야자키 하야오", 
                    "마법에 걸린 소녀 소피와 마법사 하울의 사랑 이야기", "2004-11-20", feature);
            
            Animation kaguya = createAnimation(animationRepository, "카구야 공주 이야기", "타카하타 이사오", 
                    "타케토리모노가타리를 바탕으로 한 카구야 공주의 이야기", "2013-11-23", feature);
            
            Animation hotaru = createAnimation(animationRepository, "반딧불이의 묘", "타카하타 이사오", 
                    "전쟁 속에서 가족을 잃은 남매의 짧고 안타까운 생존 이야기", "1988-04-16", feature);
            
            Animation whisper = createAnimation(animationRepository, "귀를 기울이면", "콘도 요시후미", 
                    "소설가를 꿈꾸는 소녀 시즈쿠와 바이올린 제작자를 꿈꾸는 소년 세이지의 이야기", "1995-07-15", feature);
            
            Animation gedo = createAnimation(animationRepository, "게드전기: 어스시의 전설", "미야자키 고로", 
                    "세상의 균형이 무너진 세계에서, 마법사와 왕자가 진정한 삶과 죽음의 의미를 찾아가는 이야기", "1984-03-11", feature);
            
            Animation mani = createAnimation(animationRepository, "추억의 마니", "요네바야시 히로마사", 
                    "외로운 소녀 안나가 신비로운 소녀 마니와의 만남을 통해 자신을 치유해가는 이야기", "2014-07-19", feature);
            
            Animation totoro = createAnimation(animationRepository, "이웃집 토토로", "미야자키 하야오", 
                    "시골로 이사온 두 자매가 토토로와 만나 벌이는 환상적인 모험", "1988-04-16", feature);
            
            Animation mononoke = createAnimation(animationRepository, "모노노케 히메", "미야자키 하야오", 
                    "자연과 인간의 대립을 그린 대서사시", "1997-07-12", feature);
            
            Animation ponyo = createAnimation(animationRepository, "벼랑 위의 포뇨", "미야자키 하야오", 
                    "물고기 소녀 포뇨와 소년 소스케의 우정", "2008-07-19", feature);
            
            Animation kame = createAnimation(animationRepository, "붉은 거북", "미카엘 두독 드 비트", 
                    "붉은 거북과의 만남을 통해 인생의 순환과 자연의 신비를 경험하는 이야기", "2016-09-17", feature);
            
            Animation kiki = createAnimation(animationRepository, "마녀배달부 키키", "미야자키 하야오", 
                    "13살 마녀 키키가 새로운 도시에서 독립하며 겪는 성장 이야기", "1989-07-29", feature);
            
            Animation yamada = createAnimation(animationRepository, "이웃집 야마다군", "타카하타 이사오", 
                    "평범한 가족의 일상을 그린 코미디", "1999-07-17", feature);
            
            Animation arietty = createAnimation(animationRepository, "마루 밑 아리에티", "요네바야시 히로마사", 
                    "마루 밑에 사는 작은 사람들의 이야기", "2010-07-17", feature);
            
            Animation aya = createAnimation(animationRepository, "아야와 마녀", "미야자키 고로", 
                    "벨라를 골탕 먹이기 위한 마녀지망생 아야와 말하는 고양이 토마스의 아주 특별한 주문", "2020-12-30", feature);
            
            Animation omoi = createAnimation(animationRepository, "추억은 방울방울", "타카하타 이사오", 
                    "도쿄에서 일하던 여성이 시골로 떠난 여정 속에서 어린 시절의 추억을 되돌아보며 자신의 삶을 돌아보는 이야기", "1991-07-20", feature);
            
            Animation sen = createAnimation(animationRepository, "센과 치히로의 행방불명", "미야자키 하야오", 
                    "10살 소녀 치히로가 신들의 세계에서 겪는 모험을 그린 작품", "2001-07-20", feature);
            
            Animation kokuriko = createAnimation(animationRepository, "코쿠리코 언덕에서", "미야자키 고로", 
                    "옛 기숙사를 지키려는 학생들과 그 속에서 피어나는 두 소년소녀의 풋풋한 사랑과 성장", "2011-07-16", feature);
            
            Animation kimi = createAnimation(animationRepository, "그대들은 어떻게 살 것인가", "미야자키 하야오", 
                    "상실과 슬픔을 안고 살아가는 소년이 신비한 세계를 통해 삶의 의미와 용기를 찾는 이야기", "2023-07-14", feature);
            
            
            // TV 작품 3
            Animation ayato = createAnimation(animationRepository, "아야와 마녀", "미야자키 고로", 
                    "벨라를 골탕 먹이기 위한 마녀지망생 아야와 말하는 고양이 토마스의 아주 특별한 주문", "2020-12-30", tv);
            
            Animation umi = createAnimation(animationRepository, "바다가 들린다", "모치즈키 토모미", 
                    "고등학생들의 풋풋한 사랑 이야기를 그린 TV 스페셜", "1993-05-05", tv);
            
            Animation ghiblies = createAnimation(animationRepository, "기브리즈", "모모세 요시유키", 
                    "지브리 스튜디오를 배경으로 한 단편", "2000-04-08", tv);
            
            
            // 단편 작품 20
            Animation oym = createAnimation(animationRepository, "On Your Mark", "미야자키 하야오", 
                    "CHAGE and ASKA의 뮤직비디오", "1995-07-15", short_);
            
            Animation ghiblies2 = createAnimation(animationRepository, "기브리즈 에피소드 2", "모모세 요시유키", 
                    "지브리 스튜디오를 배경으로 한 두 번째 이야기", "2002-07-20", short_);
            
            Animation ie = createAnimation(animationRepository, "집 찾기", "감독 미상", 
                    "설명 없음", "2006-01-03", short_);
            
            Animation guru = createAnimation(animationRepository, "필름이 빙글빙글", "감독 미상", 
                    "설명 없음", "2001", short_);
            
            Animation Portable = createAnimation(animationRepository, "Portable Airport", "모모세 요시유키", 
                    "CAPSULE의 아날로그 싱글 노래이자 스튜디오 지브리의 단편 애니메이션 뮤직 비디오", "2004-05-19", short_);
            
            Animation sumo = createAnimation(animationRepository, "쥐의 스모", "감독 미상", 
                    "설명 없음", "2010-01-03", short_);
            
            Animation kugira = createAnimation(animationRepository, "고래 잡기", "감독 미상", 
                    "설명 없음", "2001-10", short_);
            
            Animation space = createAnimation(animationRepository, "Space Station No.9", "모모세 요시유키", 
                    "CAPSULE의 아날로그 싱글 노래이자 스튜디오 지브리의 단편 애니메이션 뮤직 비디오", "2005-04", short_);
            
            Animation pan = createAnimation(animationRepository, "빵반죽과 계란 공주", "미야자키 하야오", 
                    "「빵씨」와 함께 무서운 바바 야가에서 도망치는 계란공주", "2010", short_);
            
            Animation goro = createAnimation(animationRepository, "고로의 대산책", "감독 미상", 
                    "설명 없음", "2002-01", short_);
            
            Animation hiko = createAnimation(animationRepository, "비행 도시 계획", "모모세 요시유키", 
                    "CAPSULE의 아날로그 싱글 AEROPOLIS 에 수록된 노래이자 스튜디오 지브리의 단편 애니메이션 뮤직 비디오", "2005-09-10", short_);
            
            Animation sagashi = createAnimation(animationRepository, "보물찾기", "감독 미상", 
                    "설명 없음", "2011-06-04", short_);
            
            Animation mei = createAnimation(animationRepository, "메이와 아기고양이버스", "미야자키 하야오", 
                    "토토로의 후속 단편", "2002-10-01", short_);
            
            Animation monmon = createAnimation(animationRepository, "물거미 몽몽", "감독 미상", 
                    "설명 없음", "2006-01", short_);
            
            Animation boro = createAnimation(animationRepository, "털벌레 보로", "미야자키 하야오", 
                    "어느 마을의 '가로수'에서 태어난 작은 털벌레 보로가 옆나무로 옮겨 가기까지의 모험", "2018-03-21", short_);
            
            Animation kusou = createAnimation(animationRepository, "상상 속의 비행 기계들", "감독 미상", 
                    "설명 없음", "2002", short_);
            
            Animation hoshi = createAnimation(animationRepository, "별을 산 날", "미야자키 하야오", 
                    "설명 없음", "2006-01-03", short_);
            
            Animation zen = createAnimation(animationRepository, "젠-그로구과 더스트 버니", "콘도 카츠야", 
                    "만달로리안의 그로구와 이웃집 토토로, 센과 치히로의 행방불명에 나온 숯검댕이들이 등장", "2022-11-12", short_);
            
            Animation hakai = createAnimation(animationRepository, "상상의 기계들 중 파괴의 발명", "감독 미상", 
                    "설명 없음", "2002-10-02", short_);
            
            Animation sora = createAnimation(animationRepository, "하늘색의 씨앗", "감독 미상", 
                    "설명 없음", "1992-11-23", short_);
            
            // 사용자 데이터 초기화
            User admin = new User();
            admin.setUsername("관리자");
            admin.setLoginId("admin");
            admin.setPassword("admin123");
            userRepository.save(admin);
            
            User user1 = new User();
            user1.setUsername("지브리팬");
            user1.setLoginId("ghibli_fan");
            user1.setPassword("user123");
            userRepository.save(user1);
            
            User user2 = new User();
            user2.setUsername("애니메이션러버");
            user2.setLoginId("anime_lover");
            user2.setPassword("user123");
            userRepository.save(user2);
            
            // 샘플 리뷰 데이터 초기화
            createReview(reviewRepository, sen, user1, "정말 감동적인 작품", 
                    "어린 시절 처음 봤을 때의 감동이 아직도 생생합니다. 치히로의 성장 과정이 너무 아름답게 그려져 있어요.");
            
            createReview(reviewRepository, howl, user2, "로맨틱한 판타지의 걸작", 
                    "하울과 소피의 사랑 이야기가 정말 아름답습니다. 미야자키 감독의 상상력이 돋보이는 작품이에요.");
            
            createReview(reviewRepository, totoro, user1, "따뜻한 가족 영화", 
                    "토토로와 함께하는 시간들이 정말 따뜻하고 포근합니다. 아이들과 함께 보기 좋은 영화예요.");
            
            createReview(reviewRepository, neko, user2, "고양이 왕국의 판타지", 
                    "고양이들의 세계가 정말 매력적으로 그려져 있습니다. 단편이지만 완성도가 높아요.");
            
            createReview(reviewRepository, umi, user1, "청춘의 아련함", 
                    "TV 스페셜이지만 극장판 못지않은 감동을 줍니다. 고등학생 시절의 풋풋함이 잘 표현되어 있어요.");
        };
    }

    // 애니메이션 생성 메서드
    private Animation createAnimation(AnimationRepository repository, String title, String director, 
                               String description, String releaseDateStr, Category category) {
        Animation animation = new Animation();
        animation.setTitle(title);
        animation.setDirector(director);
        animation.setDescription(description);
        animation.setReleaseDate(parseDate(releaseDateStr));
        animation.setCategory(category);
        return repository.save(animation);
    }

    private void createReview(ReviewRepository repository, Animation animation, User user, 
                            String title, String content) {
        Review review = new Review();
        review.setAnimation(animation);
        review.setUser(user);
        review.setTitle(title);
        review.setContent(content);
        review.setReviewDate(new Date());
        repository.save(review);
    }

    private Date parseDate(String dateStr) {
        try {
            SimpleDateFormat formatter;
            String processedDate = dateStr;
            
            // 년도만 있는 경우
            if (dateStr.matches("\\d{4}")) {
                processedDate = dateStr + "-01-01";
            }
            // 년-월만 있는 경우
            else if (dateStr.matches("\\d{4}-\\d{2}")) {
                processedDate = dateStr + "-01";
            }
            
            // 최종적으로 yyyy-MM-dd 형식으로 파싱
            formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(processedDate);
            
        } catch (ParseException e) {
            // 파싱 실패 시 기본값 (1900-01-01)
            try {
                return new SimpleDateFormat("yyyy-MM-dd").parse("1900-01-01");
            } catch (ParseException ex) {
                return new Date();
            }
        }
    }
}