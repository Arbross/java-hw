package interfaces;

import entities.Product;

import java.util.List;

public interface IProductService {
    void Create(Product product);
    List<Product> Get();
    void Update(Product product);
    void Delete(Integer id);
    Product findRecordById(Integer id);
}
