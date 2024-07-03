package com.capstone.webserver.subject.repository;

import com.capstone.webserver.subject.entity.Major;
import com.capstone.webserver.subject.entity.QSubject;
import com.capstone.webserver.subject.entity.Subject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectQueryDSLRepository extends QuerydslRepositorySupport  {
    private final JPAQueryFactory jpaQueryFactory;
    private final QSubject qSubject = QSubject.subject;

    public SubjectQueryDSLRepository(JPAQueryFactory jpaQueryFactory) {
        super(Subject.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<Subject> findSubjectsByMajor(Pageable pageable, Major major) {
        List<Subject> subjects = jpaQueryFactory
                .selectFrom(qSubject)
                .where(SubjectExpression.eqMajor(qSubject, major))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long count = jpaQueryFactory
                .selectFrom(qSubject)
                .where(qSubject.eqMajor(major))
                .stream().count();

        return new PageImpl<>(subjects, pageable, count);
    }

    public List<Subject> findAll() {
        return jpaQueryFactory
                .selectFrom(qSubject)
                .fetch();
    }
}
