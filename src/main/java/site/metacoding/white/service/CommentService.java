package site.metacoding.white.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.domain.Comment;
import site.metacoding.white.domain.CommentRepository;
import site.metacoding.white.dto.CommentReqDto.CommentSaveReqDto;
import site.metacoding.white.dto.CommentRespDto.CommentSaveRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public CommentSaveRespDto save(CommentSaveReqDto commentSaveReqDto) {
        // 1. Board가 있는지 확인
        // 클라이언트가 boardId 값을 자기 마음대로 보낼 수 있기 때문에 없는 id를 보낼 수도 있다.
        // 그래서 findById를 통해서 영속화시킨 다음에 해야한다.
        Optional<Board> boardOP = boardRepository.findById(commentSaveReqDto.getBoardId());
        if (boardOP.isPresent()) {
            Comment comment = commentSaveReqDto.toEntity(boardOP.get());
            Comment commentPS = commentRepository.save(comment);
            return new CommentSaveRespDto(commentPS);

        } else {
            throw new RuntimeException("게시글이 없어서 댓글을 쓸 수 없습니다.");
        }
    }
}
