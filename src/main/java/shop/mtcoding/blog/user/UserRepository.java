package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository //IoC에 new 하는 방법
public class UserRepository {
    // DB에 접근 가능한 매니저 객체
    // 스프링이 만들어서 IoC에 넣어둔다.
    // DI에 꺼내 쓰기만 하면 된다.
    private EntityManager em;

    // 생성자 주입
    public UserRepository(EntityManager em) {
        this.em = em;
    }
}
