package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWithSeniorDto {
    private Integer userId;
    private String id;
    private String name;
    private String department;
    private String phone;
    private Integer to_id;
    private String to_name;
    private List<String> roles;
}
