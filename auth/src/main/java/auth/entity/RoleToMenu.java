package auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Data
@GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_menu")
public class RoleToMenu {
    @Id
    @Column(length = 36, name = "uid")
    private String uid;

    @Column(length = 36, unique = true, name = "role")
    private String role;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "role"))
    private Set<String> menus = new HashSet<>();
}
