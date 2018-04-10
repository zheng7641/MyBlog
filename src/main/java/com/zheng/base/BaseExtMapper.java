package com.zheng.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseExtMapper<T,K> {

	/**
	 * 保存
	 * 
	 * @param entity
	 * @param idx
	 * @return
	 */
	public int insert(T entity, String idx);

	/**
	 * 批量保存
	 * @param list
	 * @param idx
	 * @return
	 */
	public int insertBatch(List<T> list, String idx);

	/**
	 * 更新多个属性(强烈建议：更新单个属性，请参照方法 updatePropertyById))
	 * 
	 * @param entity
	 * @param idx
	 * @return
	 */
	public int updateByUid(T entity, String idx);

	/**
	 * 更新单个属性值(强烈建议：一般不直接调用此方法，只用于子类的其它方法调用)
	 * 
	 * @param id
	 * @param property
	 * @param value
	 * @param idx
	 * @return
	 */
	public int updatePropertyByUid(String uid, String property, Serializable value, String idx);

	/**
	 * 批量更新
	 * 
	 * @param list
	 * @param idx
	 * @return
	 */
	public int updateBatchByUid(List<T> list, String idx);

	/**
	 * 根据uid删除某个对象（物理删除）
	 * 
	 * @param uid
	 * @param idx
	 * @return
	 */
	public int deleteByUid(String uid, String idx);

	/**
	 * 根据uid加载某个对象
	 * 
	 * @param uid
	 * @param idx
	 * @return
	 */
	public T getByUid(String uid, String idx);

	/**
	 * 查找对象的件数
	 * @param entity
	 * @param idx
	 * @return
	 */
	public int getCount(T entity, String idx);

	/**
	 * 按照查询条件查询对象
	 * @param map
	 * @return
	 */
	public List<T> findByExample(Map<String, Object> map);

}