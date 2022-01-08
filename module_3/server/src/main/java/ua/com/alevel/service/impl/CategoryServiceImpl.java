package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void create(Category category) {
        LOGGER_INFO.info("create the new category: " + category.getName());
        categoryDao.create(category);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Category category) {
        LOGGER_WARN.warn("delete category: " + category.getName());
        categoryDao.delete(category);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void delete(Long id) {
        LOGGER_WARN.warn("delete category by id: " + id);
        categoryDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Long id) {
        LOGGER_INFO.info("find category by id: " + id);
        return categoryDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
        LOGGER_INFO.info("find category by name: " + name);
        return categoryDao.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        LOGGER_INFO.info("find all categories");
        return categoryDao.findAll();
    }
}
