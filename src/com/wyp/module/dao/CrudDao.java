package com.wyp.module.dao;

import java.util.List;

/**
 * CrudDao.
 * @author earthlyfish
 * @since v1.0
 * @see CrudDao
 */
interface CrudDao<T> {

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
     * List T Entities by entity.
     * @param entity entity
     * @return List
     */
    List<T> findList(T entity);

    /**
     * List All T Entities.
     * @param entity entity
     * @return List
     */
    List<T> findAllList(T entity);

    /**
     * List All T Entities.
     * @see public List<T> findAllList(T entity)
     * @return List
     */
    List<T> findAllList();

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
     * @param id id
     * @see public int delete(T entity)
     * @return int
     */
    @Deprecated
    int delete(String id);

    /**
     * Delete T Entity.
     * @param entity entity
     * @return int
     */
    int delete(T entity);

}
