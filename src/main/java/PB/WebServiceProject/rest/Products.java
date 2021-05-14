package PB.WebServiceProject.rest;

import PB.WebServiceProject.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Products extends JpaRepository<ProductsEntity, Long> {

}
