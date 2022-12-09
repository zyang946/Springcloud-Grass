package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthDto {
    private String studentId;
    private String userName;
    private String department;
    private String phone;
    private String seniorId;
    private Set<String> roles;
    private String password;
}
