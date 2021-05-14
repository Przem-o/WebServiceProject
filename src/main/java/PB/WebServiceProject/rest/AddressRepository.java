package PB.WebServiceProject.rest;

import PB.WebServiceProject.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<ClientEntity, Long> {

}
