/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package priv.roc.common.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service基类
 * @author ThinkGem
 * @version 2014-05-16
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	/**
	 * 获取单条数据
	 * @param id
	 * @return
	 */
	public T get(String id) {
		return dao.get(id);
	}
	
	/**
	 * 获取单条数据
	 * @param entity
	 * @return
	 */
	public T get(T entity) {
		return dao.get(entity);
	}
	
	/**
	 * 查询列表数据
	 * @param entity
	 * @return
	 */
	public List<T> findList(T entity) {
		return dao.findList(entity);
	}

	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param entity
	 * @return
	 */
	public Page<T> findPage(Page<T> page, T entity) {
		entity.setPage(page);

		page.setList(dao.findList(entity));
		return page;
	}

	/**
	 * 保存数据（插入或更新）
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void save(T entity) {
		if (entity.getIsNewRecord()){
			entity.preInsert();
			dao.insert(entity);
		}else{
			entity.preUpdate();
			dao.update(entity);
		}
	}
	
	/**
	 * 删除数据
	 * @param entity
	 */
	@Transactional(readOnly = false)
	public void delete(T entity) {
		dao.delete(entity);
	}

	/**
	 * PC端---批量修改启用、禁用状态
	 * @param enabledStatus
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void updateEnabledStatusByIds(String enabledStatus, String[] ids) {
		dao.updateEnabledStatusByIds(enabledStatus,ids);
	}

	/**
	 * crm端---批量修改启用、禁用状态
	 * @param enabledStatus
	 * @param ids
	 */
	@Transactional(readOnly = false)
	public void updateCrmEnabledStatusByIds(String enabledStatus, String[] ids) {
		dao.updateCrmEnabledStatusByIds(enabledStatus,ids);
	}
}
