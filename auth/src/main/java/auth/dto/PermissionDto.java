package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionDto implements Serializable {
    private static final long serialVersionUID = -9046350549000430012L;
    private List<String> menus;
    private List<String> points;
}
