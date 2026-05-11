package yassir.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String username;
    private List<String> roles;
}
