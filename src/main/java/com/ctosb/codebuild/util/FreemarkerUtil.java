package com.ctosb.codebuild.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.ctosb.codebuild.model.BuildInfo;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerUtil {

    private static Configuration config;

    /**
     * 编译模版生成文件
     * 
     * @param templatePath
     * @param templateName
     * @param map
     * @param outPath
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:01:50
     */
    public static String compile(String templatePath, String templateName, BuildInfo buildInfo, String outPath) {
        PrintWriter out = null;
        try {
            init(templatePath);
            Template template = config.getTemplate(templateName);

            File file = new File(outPath);
            File path = file.getParentFile();
            if (path != null && !path.exists()) {
                path.mkdirs();
            }
            // 定义模板输出
            out = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            // StringWriter out = new StringWriter();
            // 模板解释
            template.process(buildInfo, out);
            return file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return null;
    }

    // public static String compile(String templatePath, String templateName, Map<String, Object> map, String outPath,boolean isChange) {
    // String fileName = compile(templatePath, templateName, map, outPath);
    // File file = new File(fileName);
    // //替换字符（防止freemarker和el重复）
    // if(isChange){
    // String data;
    // try {
    // data = FileUtils.readFileToString(file).replace("<<change>>", "");
    // FileUtils.writeStringToFile(file, data);
    // } catch (IOException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    // return fileName;
    // }

    /**
     * 初始化
     */
    private static void init(String templatePath) throws IOException {
        config = new Configuration(Configuration.VERSION_2_3_23);
        // 设置处理空值
        config.setClassicCompatible(true);
        // 加载模板
        config.setDirectoryForTemplateLoading(new File(templatePath));
        // 设置对象包装器
        config.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_23));
        // 设置异常处理器
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
    }
}
