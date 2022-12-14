package auth.entity;

import auth.dto.MenusDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_menu")
public class RoleToMenu implements Serializable {
    private static final long serialVersionUID = -9002279279716870352L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(length = 36, unique = true, name = "role")
    private String role;

    @Column(name = "description")
    private String description;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "role", referencedColumnName = "role"))
    private Set<MenusDto> menus = new HashSet<>();
}
