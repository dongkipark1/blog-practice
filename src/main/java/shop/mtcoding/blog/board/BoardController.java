package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {
    // 세션 불러들이기
    private final HttpSession session;
    private final BoardRepository boardRepository;
    // 세션 접근 방법 1
    //    private HttpSession session;
//    public BoardController(HttpSession session) {
//        this.session = session;}
//   세션 접근 방법  2
//  public String 임시(HttpServletRequest request){
//  HttpSession session = request.getSession();
//  }


    @GetMapping({"/", "/board"})
    public String index(HttpServletRequest request) {

        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return "index";
    }

    // /board/saveForm 요청(Get)이 온다
    @GetMapping("/board/saveForm")
    public String saveForm() {
        // session 영역에 sessionUser 키값에 user 객체 있는지 체크
        User sessionUser = (User) session.getAttribute("sessionUser");

        // 값이 null이면 로그인폼 페이지로 리다이렉션
        // 값이 null이 아니면, /board/saveForm 으로 이동

        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "board/saveForm";
    }

    // 가방에 담아서 화면에 전달
    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        BoardResponse.DetailDTO responseDTO = boardRepository.findById(id);

        request.setAttribute("board", responseDTO);
        System.out.println("id: " + id);
        return "board/detail";
    }
}
