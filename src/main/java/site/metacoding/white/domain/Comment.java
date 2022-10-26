package site.metacoding.white.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter // 최종적으로 Entity에는 없어져야 함. 잠시 사용중. 사용 완료 후 삭제해야함
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    // User 누가 썼는지
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    // Board 어디에 썼는지
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

}
