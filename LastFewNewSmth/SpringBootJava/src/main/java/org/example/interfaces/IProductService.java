package org.example.interfaces;

import org.example.dto.productdto.CreateProductDTO;
import org.example.dto.productdto.ProductDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> Get();
    ProductDTO Get(int id);
    void Add(CreateProductDTO model) throws Exception;
    void Delete(int id) throws Exception;
    void Update(ProductDTO model) throws Exception;
}
