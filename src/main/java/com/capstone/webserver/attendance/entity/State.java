package com.capstone.webserver.attendance.entity;

import com.capstone.webserver.common.util.convert.AbstractEnumNameConverter;
import com.capstone.webserver.common.util.convert.EnumName;

public enum State implements EnumName<String> {
    ATTENDANCE, LATE, ABSENCE, PUBLIC_ABSENCE, HOLD;

    @Override
    public String getName() {
        return this.name();
    }

    @jakarta.persistence.Converter(autoApply = true)
    static class Converter extends AbstractEnumNameConverter<State, String> {
        public Converter() {
            super(State.class);
        }
    }
}
