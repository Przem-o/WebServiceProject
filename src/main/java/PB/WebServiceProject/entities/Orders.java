package PB.WebServiceProject.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private OffsetDateTime date;
    @Column(name = "price")
    private String status;
    @Column(name = "client_id")
    private Long clientId;
}
