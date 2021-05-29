package PB.WebServiceProject.repository;
import PB.WebServiceProject.entities.ClientEntity;
import PB.WebServiceProject.entities.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    List<ProductCategoryEntity> findByproductcategory(String productcategory);
}
