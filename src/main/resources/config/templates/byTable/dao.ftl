package ${packageName};

import java.util.List;

import com.alan.system.model.${tableInfo.upperCamelTabName};

public interface ${tableInfo.upperCamelTabName}Dao {

	/**
	 * 保存
	 * @param model
	 * @author alan
	 * @date ${.now}
	 */
	public void save(${tableInfo.upperCamelTabName}Model model);
	/**
	 * 批量保存
	 * @param model
	 * @author alan
	 * @date ${.now}
	 */
	public void saveAll(List<${tableInfo.upperCamelTabName}Model> models);

	/**
	 * 通过id查询model
	 * @param id
	 * @return
	 * @author alan
	 * @date ${.now}
	 */
	public ${tableInfo.upperCamelTabName}Model get(String id);
	
	/**
	 * 获取所有的modal
	 * @return
	 * @author alan
	 * @date ${.now}
	 */
	public List<${tableInfo.upperCamelTabName}Model> getAll();
	/**
	 * 通过id逻辑删除对象
	 * @param id
	 * @author alan
	 * @date ${.now}
	 */
	public void logicDel(String id);
	/**
	 * 通过id批量逻辑删除对象
	 * @param id
	 * @author alan
	 * @date ${.now}
	 */
	public void logicDelAll(String[] ids);
	/**
	 * 通过id删除对象
	 * @param id
	 * @author alan
	 * @date ${.now}
	 */
	public void del(String id);
	/**
	 * 批量删除
	 * @param ids
	 * @author alan
	 * @date ${.now}
	 */
	public void delAll(String[] ids);

}