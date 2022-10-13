package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dto.productdto.CreateProductDTO;
import org.example.dto.productdto.ProductDTO;
import org.example.entities.Product;
import org.example.interfaces.IProductService;
import org.example.mapper.ApplicationMapper;
import org.example.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;
    private final ApplicationMapper mapper;

    @Override
    public List<ProductDTO> Get() {
        return mapper.productsToProductDTO_List(productRepository.findAll());
    }

    @Override
    public ProductDTO Get(int id) {
        return mapper.productToProductDTO(productRepository.findById(id).get());
    }

    @Override
    public void Add(CreateProductDTO model) throws Exception {
        if (model == null)
        {
            throw new Exception("Model is equal null.");
        }

        Product data = productRepository.findByName(model.getName());
        if (data != null)
        {
            throw new Exception("This product already exists.");
        }

        productRepository.save(mapper.createProductDTOToProduct(model));
    }

    @Override
    public void Delete(int id) throws Exception {
        Product data = productRepository.findById(id).get();
        if (data == null)
        {
            throw new Exception("This product is not exist.");
        }

        productRepository.delete(data);
    }

    @Override
    public void Update(ProductDTO model) throws Exception {
        if (model == null)
        {
            throw new Exception("Model is equal null.");
        }

        Product data = productRepository.findByName(model.getName());
        if (data == null)
        {
            throw new Exception("This product is not exist.");
        }

        productRepository.saveAndFlush(mapper.productDTOToProduct(model));
    }
}
