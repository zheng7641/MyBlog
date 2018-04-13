package com.zheng.base;

import java.io.Serializable;
import java.util.List;

import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;

/**
 * 基础DAO
 *
 * @param <T>
 */
public interface BaseDAO<T extends BaseBean, K> {
    public BaseMapper<T, K> getMapper();

    /**
     * 保存
     *
     * @param entity
     * @return
     */
    public int insert(T entity);

    /**
     * 批量保存
     */
    public int insertBatch(List<T> list);

    /**
     * 更新多个属性(强烈建议：更新单个属性，请参照方法　updatePropertyById))
     *
     * @param entity
     * @return
     */
    public int updateByUid(T entity);

    /**
     * 更新单个属性值(强烈建议：一般不直接调用此方法，只用于子类的其它方法调用)
     *
     * @param uid
     * @param property
     * @param value
     * @return
     */
    public int updatePropertyByUid(String uid, String property, Serializable value);

    /**
     * 批量更新
     *
     * @param list
     * @return
     */
    public int updateBatchByUid(List<T> list);

    /**
     * 根据uid删除某个对象（物理删除）
     *
     * @param uid
     * @return
     */
    public int deleteByUid(String uid);

    /**
     * 根据uid加载某个对象
     *
     * @param uid
     * @return
     */
    public T getByUid(String uid);

    /**
     * 查找对象的件数
     *
     * @return
     */
    public int getCount(T entity);

    /**
     * 按照查询条件查询对象
     *
     * @return
     */
    public List<T> findByExample(T entity, Page page, OrderBean ob);

    /**
     * 分页查询对象
     *
     * @return
     */
    public PageList<T> findByExamplePage(T entity, Page page, OrderBean ob);

}