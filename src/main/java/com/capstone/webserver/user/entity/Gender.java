package com.capstone.webserver.user.entity;

import com.capstone.webserver.common.util.convert.AbstractEnumNameConverter;
import com.capstone.webserver.common.util.convert.EnumName;

public enum Gender implements EnumName<String> {
    MALE, FEMALE;

    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Gender, String> {
        public Converter() {
            super(Gender.class);
        }
    }
}