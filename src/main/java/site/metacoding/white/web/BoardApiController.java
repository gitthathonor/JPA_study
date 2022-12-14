package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardReqDto.BoardUpdateReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.ResponseDto;
import site.metacoding.white.dto.SessionUser;
import site.metacoding.white.service.BoardService;

@RequiredArgsConstructor
@RestController
public class BoardApiController {

    private final BoardService boardService;
    private final HttpSession session;

    @PostMapping("/board")
    public ResponseDto<?> save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        boardSaveReqDto.setSessionUser(sessionUser);
        BoardSaveRespDto boardSaveRespDto = boardService.save(boardSaveReqDto);
        return new ResponseDto<>(1, "성공", boardSaveRespDto);
    }

    // 게시글 상세보기 (Board + User + List<Comment>)
    @CrossOrigin
    @GetMapping("/board/{id}")
    public ResponseDto<?> findById(@PathVariable Long id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("로그인을 진행해주세요");
        }
        BoardDetailRespDto boardDetailRespDto = boardService.findById(id);
        return new ResponseDto<>(1, "글 상세보기 성공", boardDetailRespDto);
    }

    @PutMapping("/board/{id}")
    public ResponseDto<?> update(@PathVariable Long id, @RequestBody BoardUpdateReqDto boardUpdateReqDto) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("로그인을 진행해주세요");
        }
        boardUpdateReqDto.setId(id);
        return new ResponseDto<>(1, "글 수정 성공", boardService.update(boardUpdateReqDto));
    }

    @GetMapping("/board")
    public ResponseDto<?> findAll() {
        return new ResponseDto<>(1, "글 리스트 보기 성공", boardService.findAll());
    }

    @DeleteMapping("/board/{id}")
    public ResponseDto<?> deleteById(@PathVariable Long id) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            throw new RuntimeException("로그인을 진행해주세요");
        }
        boardService.deleteById(id);
        return new ResponseDto<>(1, "글 삭제 성공", null);
    }

    // @PostMapping("/v2/board")
    // public String saveV2(@RequestBody BoardSaveReqDto boardSaveReqDto) {
    // User principal = (User) session.getAttribute("principal");
    // // insert into board(title, content, user_id) values(?,?,?)
    // // boardSaveReqDto.setUser(principal);
    // boardService.save(boardSaveReqDto); // 서비스에는 단 하나의 객체만 전달한다.(그 책임은 컨트롤러에게 있다)
    // return "ok";
    // }

    // @GetMapping("/v2/board/{id}")
    // public String findByIdV2(@PathVariable Long id) {
    // System.out.println("현재 OPEN-IN-VIEW의 상태가 true인지 false인지 확인한 다음에 진행하자");
    // Board boardPS = boardService.findById(id);
    // System.out.println(boardPS.getTitle());
    // System.out.println(boardPS.getContent());
    // System.out.println(boardPS.getId());
    // System.out.println("false이면 lazy로딩을 여기서 못함");
    // return "ok";
    // }
}
