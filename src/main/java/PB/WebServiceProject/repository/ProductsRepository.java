package PB.WebServiceProject.repository;

import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.entities.ProductCategoryEntity;
import PB.WebServiceProject.entities.ProductsEntity;
import PB.WebServiceProject.rest.dto.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {


   List<ProductsEntity> findByName(String name);
}
