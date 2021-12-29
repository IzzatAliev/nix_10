package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CourseDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CourseDaoImpl implements CourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Course course) {
        entityManager.persist(course);
    }

    @Override
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Course c where c.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void delete(Course course) {
        entityManager.remove(course);
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(c.id) from Course c where c.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public DataTableResponse<Course> findAll(DataTableRequest request) {
        int firstResult = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
        Root<Course> from = criteriaQuery.from(Course.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Course> shops = entityManager.createQuery(criteriaQuery)
                .setFirstResult(firstResult)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Course> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(shops);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Student");
        return (Long) query.getSingleResult();
    }

    @Override
    public Map<Long, String> findByStudentId(Long studentId) {
//        Map<Long, String> map = new HashMap<>();
//        Set<Course> courses = findById(studentId).getCourses();
//        for (Course course : courses) {
//            map.put(course.getId(), course.getName());
//        }
//        return map;
        return null;
    }

    @Override
    public List<Course> findAllByStudentId(Long studentId) {
        return null;
    }
}
