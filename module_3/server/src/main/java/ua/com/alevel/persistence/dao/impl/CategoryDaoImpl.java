package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.entity.Category;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public CategoryDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(Category category) {
        entityManager.persist(category);
    }

    @Override
    public void update(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void delete(Category category) {
        entityManager.remove(category);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Category c where c.id = :id")
                .setParameter("id", id);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(c.id) from Category c where c.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Category findById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category findByName(String name) {
        return (Category) entityManager.createQuery("select c from Category as c where c.name = :name")
                .setParameter("name", name).getSingleResult();
    }

    @Override
    public List<Category> findAll() {
        return entityManager.createQuery("select c from Category c", Category.class).getResultList();
    }
}
