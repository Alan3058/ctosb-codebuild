package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${basePackageName}.mapper.${tableInfo.upperCamelTabName}Mapper;
import ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;
import ${basePackageName}.services.${tableInfo.upperCamelTabName}Manager;
import com.ctosb.core.mybatis.Page;
import com.ctosb.core.mybatis.PageList;

@Service
public class ${tableInfo.upperCamelTabName}ManagerImpl implements ${tableInfo.upperCamelTabName}Manager {

    @Autowired
    private ${tableInfo.upperCamelTabName}Mapper ${tableInfo.camelTabName}Mapper;

    @Override
    public PageList<${tableInfo.upperCamelTabName}Model> getPageByCondition(${tableInfo.upperCamelTabName}Model condition, Page page) {
        return ${tableInfo.camelTabName}Mapper.get(condition, page);
    }

    /**
     * 保存方法
     * 
     * @param model
     * @return
     */
    public int save(${tableInfo.upperCamelTabName}Model model) {
        Long id = model.getId();
        if (id == null) {
            return ${tableInfo.camelTabName}Mapper.insert(model);
        } else {
            return ${tableInfo.camelTabName}Mapper.update(model);
        }
    }

    /**
     * 删除方法
     * 
     * @param id
     * @return
     */
    public int deleteByIds(List<Long> ids) {
        return ${tableInfo.camelTabName}Mapper.delete(ids);
    }

}
