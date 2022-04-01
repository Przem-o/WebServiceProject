package PB.WebServiceProject.repository;

import PB.WebServiceProject.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

//    List<ClientEntity> findByName(String name);


}
