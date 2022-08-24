package services;

import entities.Role;
import interfaces.IRoleService;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class RoleService implements IRoleService {
    private Session _session;
    public RoleService(Session session)
    {
        _session = session;
    }
    public void Create(Role role)
    {
        try {
            _session.save(role);
            System.out.println("Role successfully created!");

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public List<Role> Get()
    {
        List<Role> roles = new ArrayList<Role>();
        try {
            CriteriaBuilder cb = _session.getCriteriaBuilder();
            CriteriaQuery<Role> cq = cb.createQuery(Role.class);
            Root<Role> rootEntry = cq.from(Role.class);
            CriteriaQuery<Role> all = cq.select(rootEntry);
            TypedQuery<Role> allQuery = _session.createQuery(all);

            roles = allQuery.getResultList();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }

        return roles;
    }

    public void Update(Role role)
    {
        try {
            _session.saveOrUpdate(role);

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
            Role role = findRecordById(id);
            _session.delete(role);

            // Committing The Transactions To The Database
            _session.beginTransaction().commit();
        } catch(Exception sqlException) {
            if(_session.getTransaction() != null) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
    }

    public Role findRecordById(Integer id)
    {
        Role findRoleObj = null;
        try {
            findRoleObj = (Role)_session.load(Role.class, id);
        } catch(Exception sqlException) {
            if(null != _session.getTransaction()) {
                _session.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        }
        return findRoleObj;
    }
}
