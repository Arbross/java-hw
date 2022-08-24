package interfaces;

import entities.Category;

import java.util.List;

public interface ICategoryService {
    void Create(Category category);
    List<Category> Get();
    void Update(Category category);
    void Delete(Integer id);
    Category findRecordById(Integer id);
}
