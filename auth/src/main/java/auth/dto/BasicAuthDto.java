package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicAuthDto implements Serializable {
    private static final long serialVersionUID = -8489501276234472430L;
    private String username;
    private String password;
}
