package PB.WebServiceProject.services;

import PB.WebServiceProject.Type;
import PB.WebServiceProject.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class ProductsService {

    private final ProductsRepository productsRepository;

//    productsRepository.findAllByType(Type.RTV);


}
