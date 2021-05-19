package PB.WebServiceProject.repository;

import PB.WebServiceProject.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {

  // public List<ProductsEntity> findAllByType(Status type);



}
