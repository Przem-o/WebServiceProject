package PB.WebServiceProject.entities;

import lombok.*;

import javax.persistence.*;
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "country")
    private String country;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id") // ta adnotacje dajemy tylko po 1 stronie
    private ClientEntity clientEntity;

}
