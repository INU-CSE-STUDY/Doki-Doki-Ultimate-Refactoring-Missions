package com.capstone.webserver.subject.entity;

import com.capstone.webserver.common.response.exception.CustomException;
import com.capstone.webserver.common.util.convert.AbstractEnumNameConverter;
import com.capstone.webserver.common.util.convert.EnumName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.capstone.webserver.common.response.exception.ExceptionCode.NOT_FOUND_ENUM;

@RequiredArgsConstructor
@Getter
public enum Major implements EnumName<String> {
    KOREAN_LANGUAGE_AND_LITERATURE("국어국문학과", College.HUMANITIES),
    ENGLISH_LANGUAGE_AND_LITERATURE("영어영문학과", College.HUMANITIES),
    GERMAN_LANGUAGE_AND_LITERATURE("독어독문학과", College.HUMANITIES),
    FRENCH_LANGUAGE_AND_LITERATURE("불어불문학과", College.HUMANITIES),
    JAPANESE_LANGUAGE_AND_LITERATURE("일어일문학과", College.HUMANITIES),
    CHINESE_LANGUAGE_AND_STUDIES("중어중국학과", College.HUMANITIES),
    MATHEMATICS("수학과", College.NATURAL_SCIENCES),
    PHYSICS("물리학과", College.NATURAL_SCIENCES),
    CHEMISTRY("화학과", College.NATURAL_SCIENCES),
    FASHION_INDUSTRY("패션산업학과", College.NATURAL_SCIENCES),
    MARINE_SCIENCE("해양학과", College.NATURAL_SCIENCES),
    SOCIAL_WELFARE("사회복지학과", College.SOCIAL_SCIENCES),
    JOURNALISM_AND_COMMUNICATIONS("신문방송학과", College.SOCIAL_SCIENCES),
    LIBRARY_AND_INFORMATION_SCIENCE("문헌정보학과", College.SOCIAL_SCIENCES),
    CREATIVE_TALENT_DEVELOPMENT("창의인재개발학과", College.SOCIAL_SCIENCES),
    PUBLIC_ADMINISTRATION("행정학과", College.GLOBAL_ECONOMICS),
    POLITICAL_SCIENCE_AND_DIPLOMACY("정치외교학과", College.GLOBAL_ECONOMICS),
    ECONOMICS("경제학과", College.GLOBAL_ECONOMICS),
    ECONOMICS_NIGHT("경제학과(야)", College.GLOBAL_ECONOMICS),
    INTERNATIONAL_TRADE("무역학부", College.GLOBAL_ECONOMICS),
    INTERNATIONAL_TRADE_NIGHT("무역학부(야)", College.GLOBAL_ECONOMICS),
    CONSUMER_STUDIES("소비자학과", College.GLOBAL_ECONOMICS),
    MECHANICAL_ENGINEERING("기계공학과", College.ENGINEERING),
    MECHANICAL_ENGINEERING_NIGHT("기계공학과(야)", College.ENGINEERING),
    MECHATRONICS_ENGINEERING("메카트로닉스공학과", College.ENGINEERING),
    ELECTRICAL_ENGINEERING("전기공학과", College.ENGINEERING),
    ELECTRONICS_ENGINEERING("전자공학과", College.ENGINEERING),
    ELECTRONICS_ENGINEERING_NIGHT("전자공학과(야)", College.ENGINEERING),
    INDUSTRIAL_AND_MANAGEMENT_ENGINEERING("산업경영공학과", College.ENGINEERING),
    SAFETY_ENGINEERING("안전공학과", College.ENGINEERING),
    MATERIALS_SCIENCE_AND_ENGINEERING("신소재공학과", College.ENGINEERING),
    ENERGY_AND_CHEMICAL_ENGINEERING("에너지화학공학과", College.ENGINEERING),
    COMPUTER_ENGINEERING("컴퓨터공학부", College.INFORMATION_TECHNOLOGY),
    COMPUTER_ENGINEERING_NIGHT("컴퓨터공학부(야)", College.INFORMATION_TECHNOLOGY),
    INFORMATION_AND_COMMUNICATION_ENGINEERING("정보통신공학과", College.INFORMATION_TECHNOLOGY),
    EMBEDDED_SYSTEMS_ENGINEERING("임베디드시스템공학과", College.INFORMATION_TECHNOLOGY),
    BUSINESS_ADMINISTRATION("경영학부", College.BUSINESS),
    TAX_AND_ACCOUNTING("세무회계학과", College.BUSINESS),
    FINE_ARTS("조형예술학부", College.ARTS_AND_SPORTS),
    KOREAN_PAINTING("한국화전공", College.ARTS_AND_SPORTS),
    WESTERN_PAINTING("서양화전공", College.ARTS_AND_SPORTS),
    DESIGN("디자인학부", College.ARTS_AND_SPORTS),
    PERFORMING_ARTS("공연예술학과", College.ARTS_AND_SPORTS),
    PHYSICAL_EDUCATION("체육학부", College.ARTS_AND_SPORTS),
    SPORTS_HEALTH_SCIENCE("운동건강학부", College.ARTS_AND_SPORTS),
    KOREAN_LANGUAGE_EDUCATION("국어교육과", College.EDUCATION),
    ENGLISH_LANGUAGE_EDUCATION("영어교육과", College.EDUCATION),
    JAPANESE_LANGUAGE_EDUCATION("일어교육과", College.EDUCATION),
    MATHEMATICS_EDUCATION("수학교육과", College.EDUCATION),
    PHYSICAL_EDUCATION_TEACHING("체육교육과", College.EDUCATION),
    EARLY_CHILDHOOD_EDUCATION("유아교육과", College.EDUCATION),
    HISTORY_EDUCATION("역사교육과", College.EDUCATION),
    ETHICS_EDUCATION("윤리교육과", College.EDUCATION),
    URBAN_ADMINISTRATION("도시행정학과", College.URBAN_SCIENCES),
    URBAN_ARCHITECTURE("도시건축학부", College.URBAN_SCIENCES),
    ARCHITECTURAL_ENGINEERING("건축공학전공", College.URBAN_SCIENCES),
    URBAN_ARCHITECTURE_MAJOR("도시건축학전공", College.URBAN_SCIENCES),
    URBAN_ENGINEERING("도시공학과", College.URBAN_SCIENCES),
    URBAN_ENVIRONMENTAL_ENGINEERING("도시환경공학부", College.URBAN_SCIENCES),
    CONSTRUCTION_ENVIRONMENTAL_ENGINEERING("건설환경공학전공", College.URBAN_SCIENCES),
    ENVIRONMENTAL_ENGINEERING("환경공학전공", College.URBAN_SCIENCES),
    LIFE_SCIENCE("생명과학부", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    LIFE_SCIENCE_MAJOR("생명과학전공", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    MOLECULAR_LIFE_SCIENCE("분자의생명전공", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    BIOTECHNOLOGY("생명공학부", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    BIOTECHNOLOGY_MAJOR("생명공학전공", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    NANO_BIO_TECHNOLOGY("나노바이오전공", College.LIFE_SCIENCE_AND_TECHNOLOGY),
    NORTHEAST_ASIA_INTERNATIONAL_COMMERCE("동북아국제통상학부", College.NO_DIVISION),
    NORTHEAST_ASIA_COMMERCE("동북아통상전공", College.NO_DIVISION),
    KOREAN_COMMERCE("한국통상전공", College.NO_DIVISION),
    LAW("법학부", College.NO_DIVISION_LAW),
    GENERAL_EDUCATION("교양", College.GENERAL_EDUCATION),
    TEACHER_TRAINING("교직", College.TEACHER_TRAINING),
    GENERAL_ELECTIVE("일선", College.GENERAL_ELECTIVE),
    MILITARY_STUDIES("군사학", College.MILITARY_STUDIES),
    OPTOELECTRONICS_INTERDISCIPLINARY("광전자공학전공(연계)", College.OTHER),
    LOGISTICS_INTERDISCIPLINARY("물류학전공(연계)", College.OTHER),
    MICE_SPORTS_AND_TOURISM_INTERDISCIPLINARY("MICE,스포츠및관광연계전공", College.OTHER),
    INTERNATIONAL_DEVELOPMENT_INTERDISCIPLINARY("국제개발협력연계전공", College.OTHER),
    CREATIVE_DESIGN_INTERDISCIPLINARY("창의적디자인연계전공", College.OTHER),
    BEAUTY_INDUSTRY_INTERDISCIPLINARY("뷰티산업연계전공", College.OTHER),
    HUMANITIES_CULTURE_ARTS_PLANNING_INTERDISCIPLINARY("인문문화예술기획연계전공", College.OTHER),
    SOCIAL_DATA_SCIENCE_INTERDISCIPLINARY("소셜데이터사이언스연계전공", College.OTHER),
    FUTURE_AUTOMOTIVE_INTERDISCIPLINARY("미래자동차연계전공", College.OTHER);

    private final String krName;
    private final College college;

    public static List<String> getByCollege(String college) {
        return Arrays.stream(values())
                .filter(major ->
                        major.college.getKrName().equals(college)
                )
                .map(Major::getKrName)
                .collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return this.krName;
    }

    public static Major getByName(String name) {
        for (Major major : Major.values()) {
            if (major.krName.equals(name)) {
                return major;
            }
        }

        throw new CustomException(NOT_FOUND_ENUM);
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Major, String> {
        public Converter() {
            super(Major.class);
        }
    }
}
