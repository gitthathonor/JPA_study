package site.metacoding.white.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.Comment;
import site.metacoding.white.domain.CommentRepository;
import site.metacoding.white.dto.CommentReqDto.CommentSaveReqDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public void save(CommentSaveReqDto commentSaveReqDto) {
        Comment comment = new Comment();
        comment.setContent(commentSaveReqDto.getContent());
        comment.setUser(commentSaveReqDto.getSessionUser().toEntity());
        comment.setBoard(Board.builder().id(commentSaveReqDto.getBoardId()).build());
        commentRepository.save(comment);
    }
}
