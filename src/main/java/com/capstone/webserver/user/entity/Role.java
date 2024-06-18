package com.capstone.webserver.user.entity;

import com.capstone.webserver.common.util.convert.AbstractEnumNameConverter;
import com.capstone.webserver.common.util.convert.EnumName;

public enum Role implements EnumName<String> {
    ADMIN, PROFESSOR, STUDENT;

    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<Role, String> {
        public Converter() {
            super(Role.class);
        }
    }
}