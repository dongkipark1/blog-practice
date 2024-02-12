package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // final 생성자 만들어 준다
@Controller
public class UserController {
    // final 변수는 초기화 되어야 한다.
    private final UserRepository userRepository;
    private final HttpSession session;

    // ** 컨트롤러에서 클라이언트 정보 받기 **

    // 1. 왜 조회인데 post인가? 민감한 정보는 바디데이터로 보낸다
    // 2. 로그인만 예외로 select인데 post 사용한다
    // 3. select * from user_tb where username = ? and password=?


    // 방법 1: 변수로 바로 받기
    //    @PostMapping
//    public String login(String username, String password){
//        return null;
//    }

    // 방법 2 서블릿에서 요청받아 전송하기
//    @PostMapping("/login")
//    public String login(HttpServletRequest request) {
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        return null;
//    }

    // 방법 3. DTO에 객체를 실어서 전달하기
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO requestDTO, HttpServletRequest request) {
        System.out.println(requestDTO);

        HttpSession s = request.getSession();

        if (requestDTO.getUsername().length() < 3) {
            return "error/400"; // ViewResolver 설정 됨
        }

        User user = userRepository.findByUsernameAndPassword(requestDTO);

        if (user == null) {// 조회가 안됨 (401)
            return "error/401";
        } else { // 조회가 되었음
            session.setAttribute("sessionUser", user); // 라커에 담는다 (stateful)
        }

        return "redirect:/"; // 컨트롤러가 존재하면 무조건 redirect다
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO requestDTO) {


        // 모델에 위임하기
        userRepository.save(requestDTO);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // 서랍비우기
        return "redirect:/";
    }
}
