package auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class MenusDto implements Serializable {
    private static final long serialVersionUID = -2124507713552259764L;
    private String name;
    private String menuKey;
}
