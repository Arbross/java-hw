package interfaces;

import entities.Product;
import entities.Role;

import java.util.List;

public interface IRoleService {
    void Create(Role role);
    List<Role> Get();
    void Update(Role role);
    void Delete(Integer id);
    Role findRecordById(Integer id);
}
