package PB.WebServiceProject.repository;

import PB.WebServiceProject.Type;
import PB.WebServiceProject.entities.ProductCategoryEntity;
import PB.WebServiceProject.entities.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    List<ProductsEntity> findAllByType(Type type);
}
