package site.metacoding.white.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.domain.UserRepository;
import site.metacoding.white.dto.BoardReqDto.BoardSaveReqDto;
import site.metacoding.white.dto.BoardReqDto.BoardUpdateReqDto;
import site.metacoding.white.dto.BoardRespDto.BoardDetailRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardSaveRespDto;
import site.metacoding.white.dto.BoardRespDto.BoardUpdateRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public BoardSaveRespDto save(BoardSaveReqDto boardSaveReqDto) {
        // 핵심 로직
        Board boardPS = boardRepository.save(boardSaveReqDto.toEntity());

        // Entity를 Controller로 들고가지 않는다는 원칙을 지켜야 한다.

        // DTO 전환
        BoardSaveRespDto boardSaveRespDto = new BoardSaveRespDto(boardPS);

        return boardSaveRespDto;
    }

    @Transactional(readOnly = true) // 세션 종료 안 됨
    public BoardDetailRespDto findById(Long id) {

        /*
         * Board boardPS = boardRepository.findById(id)
         * .orElseThrow(() -> new RuntimeException("해당 " + id + "로 상세보기를 할 수 없습니다."));
         */

        Optional<Board> boardOP = boardRepository.findById(id); // 오픈 인뷰가 false니까 조회후 세션 종료
        // boardPS.getUser().getUsername(); // Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번
        if (boardOP.isPresent()) {
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardOP.get());
            return boardDetailRespDto;
        } else {
            throw new RuntimeException("해당 " + id + "로 상세보기를 할 수 없습니다.");
        }
    }

    @Transactional
    public BoardUpdateRespDto update(BoardUpdateReqDto boardUpdateReqDto) {
        Long id = boardUpdateReqDto.getId();
        // 영속화된 데이터를 수정!
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            Board boardPS = boardOP.get();
            boardPS.update(boardUpdateReqDto.getTitle(), boardUpdateReqDto.getContent());
            return new BoardUpdateRespDto(boardPS);
        } else {
            throw new RuntimeException("해당 " + id + "로 수정을 할 수 없습니다.");
        }
    } // 트랜젝션 종료시 -> 더티체킹을 함

    @Transactional(readOnly = true)
    public List<BoardDetailRespDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardDetailRespDto> boardDetailList = new ArrayList<>();
        // 1. List의 크기만큼 for문 돌리기
        for (int i = 0; i < boardList.size(); i++) {
            BoardDetailRespDto boardDetailRespDto = new BoardDetailRespDto(boardList.get(i));
            boardDetailList.add(boardDetailRespDto);
        }
        // 2. Board -> DTO로 옮겨야 함

        // 3. DTO를 List에 담기

        return boardDetailList;
    }

    @Transactional
    public void deleteById(Long id) {
        Optional<Board> boardOP = boardRepository.findById(id);
        if (boardOP.isPresent()) {
            boardRepository.deleteById(id);
        } else {
            throw new RuntimeException("해당 " + id + "로 삭제를 할 수 없습니다.");
        }
    }
}
