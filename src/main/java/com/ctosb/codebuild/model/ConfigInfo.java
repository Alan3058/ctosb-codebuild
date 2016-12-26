package com.ctosb.codebuild.model;

import java.util.List;
import java.util.Map;

/**
 * 配置文件信息
 * 
 * @author Alan
 * @date 2015-11-21 上午12:58:46
 * 
 */
public class ConfigInfo {

    /**
     * jdbc驱动
     */
    private String jdbcDriver;
    /**
     * jdbcUrl
     */
    private String jdbcUrl;
    /**
     * 数据库用户名
     */
    private String jdbcUserName;
    /**
     * 数据库密码
     */
    private String jdbcPassword;
    /**
     * 生成文件的基本路径
     */
    private String basePath;
    /**
     * java源文件夹
     */
    private String javaSourceFolder;
    /**
     * web源文件夹
     */
    private String webSourceFolder;
    /**
     * 生成包路径
     */
    private String packagePath;
    /**
     * 过滤字段
     */
    private String filterField;
    /**
     * 过滤字段
     */
    private String templatePath;
    /**
     * 模版信息
     */
    private Map<String, List<TemplateInfo>> templateInfoMap;

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getJavaSourceFolder() {
        return javaSourceFolder;
    }

    public void setJavaSourceFolder(String javaSourceFolder) {
        this.javaSourceFolder = javaSourceFolder;
    }

    public String getWebSourceFolder() {
        return webSourceFolder;
    }

    public void setWebSourceFolder(String webSourceFolder) {
        this.webSourceFolder = webSourceFolder;
    }

    public String getFilterField() {
        return filterField;
    }

    public void setFilterField(String filterField) {
        this.filterField = filterField;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }


    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public Map<String, List<TemplateInfo>> getTemplateInfoMap() {
        return templateInfoMap;
    }

    public void setTemplateInfoMap(Map<String, List<TemplateInfo>> templateInfoMap) {
        this.templateInfoMap = templateInfoMap;
    }

    public String getJdbcUserName() {
        return jdbcUserName;
    }

    public void setJdbcUserName(String jdbcUserName) {
        this.jdbcUserName = jdbcUserName;
    }

}
