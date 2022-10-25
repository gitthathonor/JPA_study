package site.metacoding.white.util;

import org.junit.jupiter.api.Test;

import lombok.Getter;
import lombok.Setter;

class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private String mcp; // 제조사
}

@Getter
@Setter
class ProductDto {
    private String name;
    private Integer price;
    private Integer qty;
}

public class MapperTest {

    @Test
    public void 고급매핑하기() {
        // toEntity, toDto 만들어서 매핑하면 편하지 않을까? (재활용)
    }

    @Test
    public void 매핑하기1() {
        // 1. Product 객체생성(디폴트)
        // 2. 값 넣기
        // 3. ProductDto 객체생성(디폴트)
        // 4. Product -> ProductDto로 옮기기

        // 5. ProductDto -> Product 변경

    }

}
