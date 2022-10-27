package site.metacoding.white.domain;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board) {
        em.persist(board); // insert됨
        return board;
    }

    public Optional<Board> findById(Long id) {
        // JPQL 문법
        // Optional<Board> boardOP = Optional.of(em.createQuery("select b from Board b
        // where b.id = :id", Board.class)
        // .setParameter("id", id)
        // .getSingleResult());

        // 제어권을 못 가졌을 때 사용하는 방법

        try {
            Optional<Board> boardOP = Optional.of(em
                    .createQuery("select b from Board b join fetch b.user u join fetch b.comments c where b.id = :id",
                            Board.class)
                    .setParameter("id", id)
                    .getSingleResult());
            return boardOP;
        } catch (Exception e) {
            return Optional.empty();
        }

        // Board board = em.find(Board.class, id);
        // return Optional.ofNullable(board);
    }

    public void deleteById(Long id) {
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public List<Board> findAll() {
        List<Board> boardListPS = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boardListPS;
    }

}
