<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/pui-tag" prefix="slt"%>
<script type="text/javascript" src="cdci/js/${tableInfo.camelTabName}Details.js"></script>
<div class="form-search" id="user-detail-template">
    <form class="form-horizontal" id="${tableInfo.camelTabName}EditForm" style="margin-top: 50px;">
    	<input  type="hidden" name="condition.distributionOrderId" value="{{${tableInfo.camelTabName}Id}}" />
  <#list columnInfos as columnInfo>
    <#if columnInfo_index%3 == 0 >
        </div>
        <#if columnInfo_has_next>
        <div class="row-fluid">
        </#if>
    </#if>
            <div class="span4 control-group full">
                <label class="control-label" for="">${columnInfo.colComment}</label>
                <div class="controls txt">
                    <input type="text" name="condition.${columnInfo.camelColName}" value="{{${columnInfo.camelColName}}}" >
                </div>
            </div>
  </#list>
        
    </form>
    <div class="row-fluid" style="margin-bottom: 30px;">
    <div class="form-search-btn">
        <a class="btn btn-info" href="javascript:void(0)" onclick="javascript:${tableInfo.upperCamelTabName}Details.saveHandler();">保存</a>
        <a class="btn btn-info" href="javascript:void(0)" onclick="javascript:${tableInfo.upperCamelTabName}Details.cancelHandler();">取消</a>
    </div>
    </div>
</div>