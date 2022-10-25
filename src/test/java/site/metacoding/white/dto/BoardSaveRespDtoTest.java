package site.metacoding.white.dto;

import org.junit.jupiter.api.Test;

import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.User;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;

public class BoardSaveRespDtoTest {

    @Test
    public void innerclass_테스트() {
        User user = User.builder().id(1L).username("cos").password("1234").build();
        Board board = Board.builder().id(9L).title("제목 테스트1").content("내용 테스트1").user(user).build();
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(board);
        System.out.println(boardSaveRespDto.getTitle());
    }
}
