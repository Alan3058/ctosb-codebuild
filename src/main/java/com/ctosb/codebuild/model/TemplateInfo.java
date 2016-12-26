package com.ctosb.codebuild.model;

/**
 * 模版信息
 * 
 * @author Alan
 * @date 2015-11-21 上午12:58:04
 * 
 */
public class TemplateInfo {

    /**
     * 模版类型
     */
    private String templateType;
    /**
     * 模版名称
     */
    private String templateName;
    /**
     * 模版文件路径
     */
    private String templatePath;
    /**
     * 生成文件类型
     */
    private String generateType;
    /**
     * 生成文件路径
     */
    private String generatePath;
    /**
     * 生成文件子包路径
     */
    private String generateSubPackagePath;
    /**
     * 生成文件后缀
     */
    private String generateSuffix;

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getGenerateSubPackagePath() {
        return generateSubPackagePath;
    }

    public void setGenerateSubPackagePath(String generateSubPackagePath) {
        this.generateSubPackagePath = generateSubPackagePath;
    }

    public String getGenerateSuffix() {
        return generateSuffix;
    }

    public void setGenerateSuffix(String generateSuffix) {
        this.generateSuffix = generateSuffix;
    }

    public String getGeneratePath() {
        return generatePath;
    }

    public void setGeneratePath(String generatePath) {
        this.generatePath = generatePath;
    }

    public String getGenerateType() {
        return generateType;
    }

    public void setGenerateType(String generateType) {
        this.generateType = generateType;
    }

}
