package ${packageName};

import java.util.List;

import ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;
import com.ctosb.core.mybatis.Page;
import com.ctosb.core.mybatis.PageList;

public interface ${tableInfo.upperCamelTabName}Mapper {

	/**
     * 分页查询方法
     * @param condition
     * @param page
     * @return
     * @author alan
	 * @date ${.now}
     */
    public PageList<${tableInfo.upperCamelTabName}Model> get(${tableInfo.upperCamelTabName}Model condition,Page page);

    /**
     * 查询方法
     * @param condition
     * @return
     * @author alan
	 * @date ${.now}
     */
    public List<${tableInfo.upperCamelTabName}Model> get(${tableInfo.upperCamelTabName}Model condition);
    
    /**
     * 新增方法
     * @param model
     * @return
     * @author alan
	 * @date ${.now}
     */
    public int insert(${tableInfo.upperCamelTabName}Model model);
    
    /**
     * 修改方法
     * @param model
     * @return
     * @author alan
	 * @date ${.now}
     */
    public int update(${tableInfo.upperCamelTabName}Model model);
    
    /**
     * 删除方法
     * @param ids
     * @return
   	 * @author alan
	 * @date ${.now}
     */
    public int delete(List<Long> ids);

}