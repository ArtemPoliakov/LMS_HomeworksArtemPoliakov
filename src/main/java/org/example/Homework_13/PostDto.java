package org.example.Homework_13;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {
    private int userId;
    private int id;
    private String title;
    private String body;
}
