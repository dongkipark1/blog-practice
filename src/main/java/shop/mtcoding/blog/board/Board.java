package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table(name = "board_tb")
@Data
@Entity
public class Board {
    @Id // pk설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
    private int id;
    private String title;
    private String content;

    private int userId; // 테이블 만들어 질때 user_id

    private LocalDateTime createdAt;
}
