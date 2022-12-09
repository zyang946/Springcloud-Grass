package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto implements Serializable {
    private static final long serialVersionUID = -3548036164265399934L;
    Integer id;
    String name;
    String description;
    List<String> permissions;
}
