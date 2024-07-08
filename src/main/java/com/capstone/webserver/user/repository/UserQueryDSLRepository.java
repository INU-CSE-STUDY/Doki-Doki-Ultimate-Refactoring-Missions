package com.capstone.webserver.user.repository;

import com.capstone.webserver.user.entity.QUser;
import com.capstone.webserver.user.entity.Role;
import com.capstone.webserver.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserQueryDSLRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    private final QUser qUser = QUser.user;
    public UserQueryDSLRepository(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<User> findAll() {
        return queryFactory
                .selectFrom(qUser)
                .fetch();
    }

    public List<User> findAllByType(Role type) {
        return queryFactory
                .selectFrom(qUser)
                .where(qUser.type.eq(type))
                .fetch();
    }

    public User findByLoginId(String loginId) {
        return queryFactory
                .selectFrom(qUser)
                .where(qUser.loginId.eq(loginId))
                .fetchOne();
    }

    @Transactional
    public void saveRefreshToken(String loginId, String newRefreshToken) {
        QUser user = QUser.user;

        queryFactory.update(user)
                .set(user.refreshToken, newRefreshToken)
                .where(user.loginId.eq(loginId))
                .execute();
    }
}
