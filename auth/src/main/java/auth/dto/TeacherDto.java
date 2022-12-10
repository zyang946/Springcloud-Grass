package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto implements Serializable {
    private static final long serialVersionUID = 1740373009467182904L;
    private Integer id;
    private String name;
}
