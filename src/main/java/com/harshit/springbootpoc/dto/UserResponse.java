package com.harshit.springbootpoc.dto;

import com.harshit.springbootpoc.model.UserLibrary;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private Integer userId;
    private List<UserLibrary> userLibrary;
}
