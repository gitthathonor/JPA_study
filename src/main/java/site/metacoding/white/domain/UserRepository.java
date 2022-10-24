package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IoC 컨테이너 등록
public class UserRepository {
    // DI
    private final EntityManager em;

    public void save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush(트랜젝션 종료 시)
        em.persist(user); // insert됨
    }
}
