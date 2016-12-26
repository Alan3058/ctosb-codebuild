package com.ctosb.codebuild;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.ctosb.codebuild.model.ConfigInfo;
import com.ctosb.codebuild.model.TemplateInfo;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 上下文信息
 * 
 * @author Alan
 * @date 2015-11-20 下午09:40:37
 * 
 */
public class Context {

    private static final String DEFAULT_CONFIG_FILE_NAME = "config/codeBuildConfig.json";
    private static Context instance;
    
    private String configPath;
    private ConfigInfo configInfo;

    /**
     * Context单例实现
     * 
     * @param configPath
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:56:37
     */
    public static Context getInstance() {
        return getInstance(DEFAULT_CONFIG_FILE_NAME);
    }

    /**
     * Context单例实现
     * 
     * @param configFileName
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:56:37
     */
    public static Context getInstance(String configFileName) {
        if (instance == null) {
            if (configFileName == null) {
                instance = new Context(DEFAULT_CONFIG_FILE_NAME);
            } else {
                instance = new Context(configFileName);
            }
        }
        return instance;
    }

    /**
     * 私有构造方法
     * 
     * @param configFileName
     */
    private Context(String configFileName) {
        // 获取配置文件内容
        String fileContent = getConfigInfoContent(configFileName);
        // 剔除json文件的注释/**/
        fileContent = fileContent.replaceAll("/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/", "");
        // 解析配置文件内容
        parseConfigFileContent(fileContent);
    }
    /**
     * 将配置信息保存到文件中
     * 
     * @author Alan
     * @createTime 2015年12月4日 下午10:31:46
     */
    public void saveConfigInfoToFile() {
        saveConfigInfoToFile(this.configPath);
    }
    /**
     * 将配置信息保存到文件中
     * 
     * @author Alan
     * @createTime 2015年12月4日 下午10:31:46
     */
    public void saveConfigInfoToFile(String configPath) {
        // 将配置信息回写到配置文件中
        File configFile = new File(configPath);
        if (!configFile.exists()) {
            //配置文件不存在，重新新建
            try {
                configFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        String contextString = JSON.toJSONString(this.getConfigInfo(), true);
        try {
            //将Json格式的配置信息写入到配置文件中
            FileUtils.write(configFile, contextString, "utf-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 获取配置文件内容
     * 
     * @author Alan
     * @createTime 2015-11-29 上午11:51:29
     * @param configFileName
     * @return
     */
    private String getConfigInfoContent(String configFileName) {
        String fileContent = null;
        File file = new File(configFileName);
        try {
            if (file.exists()) {
                // 如果文件存在，直接读取该文件
                fileContent = IOUtils.toString(new FileInputStream(file), "UTF-8");
                this.setConfigPath(file.getAbsolutePath());//set配置路径到context
            } else {
                // 如果文件不存在，则在classpath文件目录下查找该文件
                URL url = getClass().getClassLoader().getResource(configFileName);
                this.setConfigPath(url.getPath());//set配置路径到context
                fileContent = IOUtils.toString(url.openStream(), "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * 解析配置文件内容
     * 
     * @author Alan
     * @createTime 2015-11-29 上午11:53:23
     * @param content
     */
    private void parseConfigFileContent(String content) {
        try {
            configInfo = JSON.parseObject(content, ConfigInfo.class);
            if (EmptyUtil.isEmpty(configInfo) || EmptyUtil.isEmpty(configInfo.getTemplateInfoMap())) {
                return;
            }
            Map<String, List<TemplateInfo>> templateInfoMap = configInfo.getTemplateInfoMap();
            for (String key : templateInfoMap.keySet()) {
                // 为模版赋值模版类型
                for (TemplateInfo templateInfo : templateInfoMap.get(key)) {
                    templateInfo.setTemplateType(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 数据库驱动
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:48:15
     */
    public String getJdbcDriver() {
        return configInfo.getJdbcDriver();
    }

    /**
     * set 数据库驱动
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param jdbcDriver
     */
    public Context setJdbcDriver(String jdbcDriver) {
        configInfo.setJdbcDriver(jdbcDriver);
        return this;
    }

    /**
     * 获取数据库url
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:48:05
     */
    public String getJdbcUrl() {
        return configInfo.getJdbcUrl();
    }

    /**
     * set 数据库url
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param jdbcUrl
     */
    public Context setJdbcUrl(String jdbcUrl) {
        configInfo.setJdbcUrl(jdbcUrl);
        return this;
    }

    /**
     * 获取数据库密码
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:47:55
     */
    public String getJdbcPassword() {
        return configInfo.getJdbcPassword();
    }

    /**
     * set 数据库密码
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param jdbcPassword
     */
    public Context setJdbcPassword(String jdbcPassword) {
        configInfo.setJdbcPassword(jdbcPassword);
        return this;
    }

    /**
     * 获取数据库用户名
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:47:38
     */
    public String getJdbcUserName() {
        return configInfo.getJdbcUserName();
    }

    /**
     * set 数据库用户名
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param jdbcUserName
     */
    public Context setJdbcUserName(String jdbcUserName) {
        configInfo.setJdbcUserName(jdbcUserName);
        return this;
    }

    /**
     * 获取生成代码的基本路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:47:23
     */
    public String getBasePath() {
        if (EmptyUtil.isEmpty(configInfo.getBasePath())) {
            return System.getProperty("user.dir");// 默认为当前项目目录
        } else {
            return configInfo.getBasePath();
        }
    }

    /**
     * set 生成代码的基本路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param basePath
     */
    public Context setBasePath(String basePath) {
        configInfo.setBasePath(basePath);
        return this;
    }

    /**
     * 获取java源文件夹
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:50:05
     */
    public String getJavaSourceFolder() {
        return configInfo.getJavaSourceFolder();
    }

    /**
     * set java源文件夹
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param javaSourceFolder
     */
    public Context setJavaSourceFolder(String javaSourceFolder) {
        configInfo.setJavaSourceFolder(javaSourceFolder);
        return this;
    }

    /**
     * web资源的源文件夹
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:50:15
     */
    public String getWebSourceFolder() {
        return configInfo.getWebSourceFolder();
    }

    /**
     * set web资源的源文件夹
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param webSourceFolder
     */
    public Context setWebSourceFolder(String webSourceFolder) {
        configInfo.setWebSourceFolder(webSourceFolder);
        return this;
    }

    /**
     * java文件的包路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:50:48
     */
    public String getPackagePath() {
        return configInfo.getPackagePath();
    }

    /**
     * set java文件的包路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param packagePath
     */
    public Context setPackagePath(String packagePath) {
        configInfo.setPackagePath(packagePath);
        return this;
    }

    /**
     * 模版路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:01
     */
    public String getTemplatePath() {
        return configInfo.getTemplatePath();
    }

    /**
     * set模版路径
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param templatePath
     */
    public Context setTemplatePath(String templatePath) {
        configInfo.setTemplatePath(templatePath);
        return this;
    }

    /**
     * 获取模版信息
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:16
     */
    public Map<String, List<TemplateInfo>> getTemplateInfo() {
        return configInfo.getTemplateInfoMap();
    }

    /**
     * set模版信息
     * 
     * @author Alan
     * @createTime 2015-11-12 下午02:51:16
     * @param templateInfoMap
     * @return
     */
    public Context setTemplateInfo(Map<String, List<TemplateInfo>> templateInfoMap) {
        configInfo.setTemplateInfoMap(templateInfoMap);
        return this;
    }

    /**
     * 获取过滤的字段（公用的数据库字段）
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     */
    public String getFilterField() {
        return configInfo.getFilterField();
    }

    /**
     * set过滤的字段（公用的数据库字段）
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     * @param filterField
     */
    public Context setFilterField(String filterField) {
        configInfo.setFilterField(filterField);
        return this;
    }

    /**
     * 获取过滤的字段，以拼接成数组（公用的数据库字段）
     * 
     * @return
     * @author Alan
     * @time 2015-11-12 下午02:51:25
     */
    public String[] getFilterFields() {
        String filterFields = configInfo.getFilterField();
        return filterFields == null ? new String[] {} : filterFields.split(",");
    }

    /**
     * 配置路径
     * @author Alan
     * @createTime 2015-12-2 上午10:22:58
     * @return
     */
    public String getConfigPath() {
        return configPath;
    }

    /**
     * set 配置路径
     * @author Alan
     * @createTime 2015-12-2 上午10:23:09
     * @param configPath
     */
    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    /**
     * 获取配置信息
     * @author Alan
     * @createTime 2015-12-2 上午11:15:38
     * @return
     */
    public ConfigInfo getConfigInfo() {
        return configInfo;
    }

}
