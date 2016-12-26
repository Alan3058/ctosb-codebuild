package ${packageName};

import java.util.List;

import ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;
import com.ctosb.core.mybatis.Page;
import com.ctosb.core.mybatis.PageList;

public interface ${tableInfo.upperCamelTabName}Manager {

    /**
     * 分页查询方法
     * @param condition
     * @param page
     * @return
     * @author alan
	 * @date ${.now}
     */
    public PageList<${tableInfo.upperCamelTabName}Model> getPageByCondition(${tableInfo.upperCamelTabName}Model condition,Page page);
    
    /**
     * 新增方法
     * @param model
     * @return
     * @author alan
	 * @date ${.now}
     */
    public int save(${tableInfo.upperCamelTabName}Model model);
    
    /**
     * 删除方法
     * @param id
     * @return
     * @author alan
	 * @date ${.now}
     */
    public int deleteByIds(List<Long> ids);

}
