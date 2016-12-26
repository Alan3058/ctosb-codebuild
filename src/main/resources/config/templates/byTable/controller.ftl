package ${packageName};

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import ${basePackageName}.ResultMessage;
import ${basePackageName}.model.${tableInfo.upperCamelTabName}Model;
import ${basePackageName}.services.${tableInfo.upperCamelTabName}Manager;
import com.ctosb.core.mybatis.Page;

@Controller
@RequestMapping("${tableInfo.camelTabName}")
public class ${tableInfo.upperCamelTabName}Controller {

    @Autowired
    private ${tableInfo.upperCamelTabName}Manager ${tableInfo.camelTabName}Manager;

    /**
     * 获取列表
     * 
     * @param condition
     * @return
	 * @author alan
	 * @date ${.now}
     */
    @RequestMapping(value = "/page")
    @ResponseBody
    public Object page(@ModelAttribute ${tableInfo.upperCamelTabName}Model condition, @ModelAttribute Page page) {
        return ${tableInfo.camelTabName}Manager.getPageByCondition(condition, page);
    }

    /**
     * 保存
     * 
     * @param model
     * @return
	 * @author alan
	 * @date ${.now}
     */
    @RequestMapping("/save")
    @ResponseBody
    public Object save(@ModelAttribute ${tableInfo.upperCamelTabName}Model model) {
    	// 保存
		int resultNum = ${tableInfo.camelTabName}Manager.save(model);
		// 返回是否成功
		return new ResultMessage(resultNum > 0, model.getId());
    }

    /**
     * 删除
     * 
     * @param ids
     * @return
	 * @author alan
	 * @date ${.now}
     */
    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteByIds(@RequestParam("ids") List<Long> ids) {
    	if (null != ids) {
			int resultNum = ${tableInfo.camelTabName}Manager.deleteByIds(ids);
			return new ResultMessage(resultNum > 0);
		} else {
			return new ResultMessage(false);
		}
    }
}
