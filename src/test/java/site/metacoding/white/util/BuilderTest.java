package site.metacoding.white.util;

import org.junit.jupiter.api.Test;

class MyUser {
    private Long id;
    private String username;
    private String password;

    public MyUser id(Long id) {
        this.id = id;
        return this;
    }

    public MyUser username(String username) {
        this.username = username;
        return this;
    }

    public MyUser password(String password) {
        this.password = password;
        return this;
    }

    public static MyUser builder() {
        return new MyUser();
    }
}

public class BuilderTest {

    @Test
    public void 빌더_테스트() {
        MyUser.builder().id(1L).username("ssar").password("1234");
        // 장점
        // 1. 순서가 필요없다.
        // 2. 넣고 싶은 것만 넣을 수 있다.
        // 3. 컴파일 시에 오류가 잡힌다.
    }
}
