package Model;

import Model.DICT.Permissions;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long Id;

    @Column(name = "FirstName")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "LastName")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "Login")
    @Getter
    @Setter
    private String login;

    @Column(name = "PasswordHash")
    @Getter
    @Setter
    private String passwordHash;

    @Column(name = "CodeHash")
    @Getter
    @Setter
    private String codeHash;

    @Column(name = "BaseSalary", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    @Setter
    private BigDecimal baseSalary;

    @Column(name = "HourlyRate", columnDefinition = "Decimal(9,2)", precision = 9, scale = 2)
    @Getter
    @Setter
    private BigDecimal hourlyRate;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "UsersPermission",
            inverseJoinColumns = @JoinColumn(name = "PermissionId", referencedColumnName = "Id"),
            joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "Id"))
    @Getter
    @Setter
    private List<Permissions> permissionsList;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", codeHash='" + codeHash + '\'' +
                ", baseSalary=" + baseSalary +
                ", hourlyRate=" + hourlyRate +
                ", permissionsList=" + permissionsList +
                '}';
    }
}
