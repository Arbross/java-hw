package services;

import entities.Category;
import interfaces.ICategoryService;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {
    private Session _session;
    public CategoryService(Session session)
    {
        _session = session;
    }
    public void Create(Category category)
    {
        try {
            _session.save(category);
            System.out.println("Category successfully created!");

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public List<Category> Get()
    {
        List<Category> categories = new ArrayList<Category>();
        try {
            CriteriaBuilder cb = _session.getCriteriaBuilder();
            CriteriaQuery<Category> cq = cb.createQuery(Category.class);
            Root<Category> rootEntry = cq.from(Category.class);
            CriteriaQuery<Category> all = cq.select(rootEntry);
            TypedQuery<Category> allQuery = _session.createQuery(all);

            categories = allQuery.getResultList();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }

        return categories;
    }

    public void Update(Category category)
    {
        try {
            _session.saveOrUpdate(category);

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public void Delete(Integer id)
    {
        try {
            Category category = findRecordById(id);
            _session.delete(category);

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public Category findRecordById(Integer id)
    {
        Category findCarObj = null;
        try {
            findCarObj = (Category)_session.load(Category.class, id);
        } catch(Exception sqlException) {
            if(null != _session.getTransaction()) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findCarObj;
    }
}
