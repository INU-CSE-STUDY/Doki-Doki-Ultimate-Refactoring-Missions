package com.capstone.webserver.subject.repository;

import com.capstone.webserver.subject.entity.Major;
import com.capstone.webserver.subject.entity.QSubject;
import com.capstone.webserver.subject.entity.Subject;
import com.querydsl.core.annotations.QueryDelegate;
import com.querydsl.core.types.dsl.BooleanExpression;

public class SubjectExpression {
    @QueryDelegate(Subject.class)
    public static BooleanExpression eqMajor(QSubject qSubject, Major major) {
        return qSubject.majorSubject.eq(major);
    }
}
