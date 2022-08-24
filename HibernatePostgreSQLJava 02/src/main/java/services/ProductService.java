package services;

import entities.Product;
import interfaces.IProductService;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private Session _session;
    public ProductService(Session session)
    {
        _session = session;
    }
    public void Create(Product product)
    {
        try {
            _session.save(product);
            System.out.println("Product successfully created!");

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public List<Product> Get()
    {
        List<Product> products = new ArrayList<Product>();
        try {
            CriteriaBuilder cb = _session.getCriteriaBuilder();
            CriteriaQuery<Product> cq = cb.createQuery(Product.class);
            Root<Product> rootEntry = cq.from(Product.class);
            CriteriaQuery<Product> all = cq.select(rootEntry);
            TypedQuery<Product> allQuery = _session.createQuery(all);

            products = allQuery.getResultList();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }

        return products;
    }

    public void Update(Product product)
    {
        try {
            _session.saveOrUpdate(product);

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
            Product product = findRecordById(id);
            _session.delete(product);

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public Product findRecordById(Integer id)
    {
        Product findProductObj = null;
        try {
            findProductObj = (Product)_session.load(Product.class, id);
        } catch(Exception sqlException) {
            if(null != _session.getTransaction()) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findProductObj;
    }
}
