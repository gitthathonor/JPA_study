package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IoC 컨테이너 등록
public class UserRepository {
    // DI
    private final EntityManager em;

    public User save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush(트랜젝션 종료 시)
        System.out.println("ccc : " + user.getId());
        em.persist(user); // insert됨
        System.out.println("ccc : " + user.getId()); // 영속화 된 후 DB와 동기화 된다.
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
}
