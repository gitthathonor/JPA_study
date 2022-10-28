package site.metacoding.white.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.service.BoardService;
import site.metacoding.white.service.UserService;
import site.metacoding.white.util.SHA256;

@ActiveProfiles("test")
@Sql("classpath:truncate.sql")
@Transactional // JPA테스트를 위해서 필요함
@AutoConfigureMockMvc // MockMvc를 IoC 컨테이너에 등록
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // 모든 것을 IoC에 띄운다.
public class BoardApiControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper om;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @Autowired
    private static SHA256 sha256;

    private static HttpHeaders headers;

    @BeforeEach
    public static void init() throws Exception {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

    }

    @Test
    public void findById_test() throws Exception {
        // data init

        // given
        Long id = 1L;

        // when
        ResultActions resultActions = mvc.perform(get("/board/" + id).accept(MediaType.APPLICATION_JSON_VALUE));

        // then
        resultActions.andExpect(status().isOk());

    }

    @Test
    public void save_test() throws JsonProcessingException {
        // given
        BoardSaveReqDto boardSaveReqDto = new BoardSaveReqDto();
        boardSaveReqDto.setTitle("스프링1강");
        boardSaveReqDto.setContent("트랜젝션관리");

        String body = om.writeValueAsString(boardSaveReqDto);
        System.out.println("디버그 : " + body);

        // when

        // then

    }
}
