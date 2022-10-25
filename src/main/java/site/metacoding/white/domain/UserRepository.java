package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Repository // IoC 컨테이너 등록
public class UserRepository {
    // DI
    private final EntityManager em;

    public User save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush(트랜젝션 종료 시)
        log.debug("디버그 : " + user.getId());
        em.persist(user); // insert됨
        log.debug("디버그 : " + user.getId()); // 영속화 된 후 DB와 동기화 된다.
        return user;
    }

    public User login(User user) {
        User userPS = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", user.getUsername())
                .getSingleResult();

        return userPS;
    }

    public List<User> findAll() {
        List<User> userList = em.createQuery("select u from User u", User.class)
                .getResultList();
        return userList;
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username = : username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    public User findById(Long id) {
        User userPS = em.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        return userPS;
    }
}
