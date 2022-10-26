package site.metacoding.white.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.service.BoardService;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final BoardService boardService;
    private final HttpSession session;

}
