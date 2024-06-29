package com.capstone.webserver.subject.entity;

import com.capstone.webserver.common.util.convert.AbstractEnumNameConverter;
import com.capstone.webserver.common.util.convert.EnumName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum College implements EnumName<String> {
    HUMANITIES("인문대학"),
    NATURAL_SCIENCES("자연과학대학"),
    SOCIAL_SCIENCES("사회과학대학"),
    GLOBAL_ECONOMICS("글로벌정경대학"),
    ENGINEERING("공과대학"),
    INFORMATION_TECHNOLOGY("정보기술대학"),
    BUSINESS("경영대학"),
    ARTS_AND_SPORTS("예술체육대학"),
    EDUCATION("사범대학"),
    URBAN_SCIENCES("도시과학대학"),
    LIFE_SCIENCE_AND_TECHNOLOGY("생명과학기술대학"),
    NO_DIVISION("단과대구분없음"),
    NO_DIVISION_LAW("단과대구분없음(법학)"),
    GENERAL_EDUCATION("교양"),
    TEACHER_TRAINING("교직"),
    GENERAL_ELECTIVE("일선"),
    MILITARY_STUDIES("군사학"),
    OTHER("기타");

    private final String krName;

    @Override
    public String getName() {
        return this.krName;
    }

    public static College getByName(String name) {
        for (College college : College.values()) {
            if (college.krName.equals(name)) {
                return college;
            }
        }

        return null;
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<College, String> {
        public Converter() {
            super(College.class);
        }
    }
}
