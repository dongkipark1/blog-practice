package shop.mtcoding.blog.board;

// 응답 DTO 만들기

import lombok.Data;


public class BoardResponse {
    // 응답 DTO 만들기
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;

    }
}
