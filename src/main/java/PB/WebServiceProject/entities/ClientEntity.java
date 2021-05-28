package PB.WebServiceProject.entities;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "client")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "name")
    private String name;

    @OneToOne(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn // adnotacja tylko przy OneToOne
    private AddressEntity addressEntity;

    @OneToMany(mappedBy = "clientEntity")
    private Set<OrdersEntity> ordersEntitySet;


}
