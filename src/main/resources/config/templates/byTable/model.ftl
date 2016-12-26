package ${packageName};

import java.io.Serializable;
import java.util.Date;

/**
 * ${tableInfo.tabComment}
 * 
 * @author alan
 * @date ${.now}
 */
public class ${tableInfo.upperCamelTabName}Model implements Serializable {
	
	private static final long serialVersionUID = ${(.now?long)?replace(",","")}l;
	<#list columnInfos as columnInfo>
	/**
	 * ${columnInfo.colComment}
 	 */
	private ${columnInfo.javaColType} ${columnInfo.camelColName};
	</#list>

	public ${tableInfo.upperCamelTabName}Model() {

	}
	<#list columnInfos as columnInfo>
	
	public ${columnInfo.javaColType} get${columnInfo.upperCamelColName}() {
		return ${columnInfo.camelColName};
	}

	public ${tableInfo.upperCamelTabName}Model set${columnInfo.upperCamelColName}(${columnInfo.javaColType} ${columnInfo.camelColName}) {
		this.${columnInfo.camelColName} = ${columnInfo.camelColName};
		return this;
	}
	</#list>

}