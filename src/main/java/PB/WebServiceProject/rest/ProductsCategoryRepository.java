package PB.WebServiceProject.rest;

import PB.WebServiceProject.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {


}
