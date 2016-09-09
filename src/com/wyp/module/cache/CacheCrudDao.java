package com.wyp.module.cache;

import java.util.List;

/**
 * CacheCrudDao.
 * @author earthlyfish
 * @since v1.0
 * @see CacheCrudDao
 */
interface CacheCrudDao<T> {

    /**
     * Get T Entity By ID.
     * @param id id
     * @return T
     */
    T get(String id);

    /**
     * Get T Entity by entity.
     * @param entity entity
     * @return T
     */
    T get(T entity);

    /**
     * List All T Entities.
     * @see public List<T> findAllList(T entity)
     * @return List
     */
    List<Object> findAllList();

    /**
     * Insert T Entity.
     * @param entity entity
     * @return int
     */
    int insert(T entity);

    /**
     * Update T Entity.
     * @param entity entity
     * @return int
     */
    int update(T entity);

    /**
     * Delete T Entity.
     * @param entity entity
     * @return int
     */
    int delete(T entity);
}
