package ${packageName};

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.alan.core.model.BaseModel;

/**
 * ${tableInfo.tabComment}
 * 
 * @author alan
 * @date ${.now}
 */
@Entity
@Table(name = "${tableInfo.tabName}")
public class ${tableInfo.upperCamelTabName} extends BaseModel {
	
	private static final long serialVersionUID = -4643466141706527625L;
	<#list columnInfos as columnInfo>
	/**
	 * ${columnInfo.colComment}
 	 */
	private ${columnInfo.javaColType} ${columnInfo.camelColName};
	</#list>

	public ${tableInfo.upperCamelTabName}() {

	}
	<#list columnInfos as columnInfo>
	
	@Column(name="${columnInfo.colName}")
	public ${columnInfo.javaColType} get${columnInfo.upperCamelColName}() {
		return ${columnInfo.camelColName};
	}

	public void set${columnInfo.upperCamelColName}(${columnInfo.javaColType} ${columnInfo.camelColName}) {
		this.${columnInfo.camelColName} = ${columnInfo.camelColName};
	}
	</#list>


}