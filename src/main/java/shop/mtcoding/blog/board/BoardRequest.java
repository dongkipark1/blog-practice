package shop.mtcoding.blog.board;

import lombok.Data;

// 클라이언트로 부터 받는 데이터
public class BoardRequest {
    @Data
    public static class SaveDTO {
        private String title;
        private String content;
    }
}
