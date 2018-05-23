package com.zheng.base;


import com.zheng.base.page.OrderBean;
import com.zheng.base.page.Page;
import com.zheng.base.page.PageList;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import com.zheng.common.datasource.DynamicDataSourceHolder;

/**
 * 基础DB访问类
 * @param <T>
 */
public abstract class BaseDAOImpl<T extends BaseBean,K> implements BaseDAO<T,K>{
	private static Logger logger = Logger.getLogger(BaseDAOImpl.class.getName());

	public abstract BaseMapper<T,K> getMapper();

	/**
	 * 保存对象
	 * @param entity 对象
	 * @return
	 */
	public int insert(T entity) {
		return getMapper().insert(entity);
	}

	/**
	 * 批量保存对象
	 * @param list 对象列表
	 * @return
	 */
	public int insertBatch(List<T> list) {
		return getMapper().insertBatch(list);
	}

	/**
	 * 更新单属性（根据uid更新）
	 * @param uid
	 * @param property TABLE中的属性名称，参照FieldConstants内定义
	 * @param value 属性值
	 * @return
	 */
	public int updatePropertyByUid(String uid, String property, Serializable value) {
		return getMapper().updatePropertyByUid(uid, property, value);
	}

	/**
	 * 更新多属性（根据uid更新）
	 * @param entity 查询条件
	 * @return
	 */
	public int updateByUid(T entity) {
		return getMapper().updateByUid(entity);
	}
	/**
	 * 批量更新
	 *
	 * @param list
	 * @return
	 */
	public int updateBatchByUid(List<T> list){
		return getMapper().updateBatchByUid(list);
	}

	/**
	 * 根据uid删除对象（物理删除）
	 * @param uid
	 * @return
	 */
	public int deleteByUid(String uid) {
		return getMapper().deleteByUid(uid);
	}

	/**
	 *  根据uid查找某个对象
	 * @param uid
	 * @return
	 */
	public T getByUid(String uid) {
		return getMapper().getByUid(uid);
	}

	/**
	 * 查找对象的件数
	 * @param entity 查询条件
	 * @return
	 */
	public int getCount(T entity) {
		return getMapper().getCount(entity);
	}

	/**
	 * 按照查询条件查询对象
	 * @param entity 查询条件
	 * @param page 分页bean
	 * @param ob 排序bean
	 * @return
	 */
	public List<T> findByExample(T entity,Page page,OrderBean ob) {
//		DynamicDataSourceHolder.putDataSource("slave");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("entity", entity);
		map.put("page", page == null? (new Page()):page);
		map.put("orderBean", ob == null ? "":ob.getOrderSql());
		List<T> r = getMapper().findByExample(map);
//		DynamicDataSourceHolder.putDataSource("master");
		return r;
	}

	/**
	 * 分页查询
	 * @param entity 查询条件
	 * @param page 分页bean
	 * @param ob 排序bean
	 * @return
	 */
	public PageList<T> findByExamplePage(T entity,Page page,OrderBean ob) {
		if(page.isNeedTotal()){
			page.setTotalCount(getCount(entity));
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("entity", entity);
		map.put("page", page == null? (new Page()):page);
		map.put("orderBean", ob == null ? "":ob.getOrderSql());
		return new PageList<T>(findByExample(entity,page,ob),page);
	}
}
