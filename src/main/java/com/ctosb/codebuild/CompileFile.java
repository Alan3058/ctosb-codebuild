package com.ctosb.codebuild;

import java.io.File;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctosb.codebuild.model.BuildInfo;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.model.TemplateInfo;
import com.ctosb.codebuild.ui.BuildByTablePanel;
import com.ctosb.codebuild.util.EmptyUtil;
import com.ctosb.codebuild.util.FreemarkerUtil;

public class CompileFile {
	private static Log log = LogFactory.getLog(BuildByTablePanel.class);

	/**
	 * 生成代码
	 * 
	 * @param tableName
	 * @author Alan
	 * @time 2015-11-12 下午02:36:04
	 */
	public static String build(Context context, TableInfo tableInfo, List<TemplateInfo> templateInfoList) {
		String className = tableInfo.getUpperCamelTabName();// 类名
		String commonTemplatePath;
		if (new File(context.getTemplatePath()).isAbsolute()) {
			// 公共模版路径为绝对路径
			commonTemplatePath = context.getTemplatePath();
		} else {
			// 公共模版路径 在classes文件目录下
			commonTemplatePath = Thread.currentThread().getContextClassLoader().getResource("").getPath()
					+ File.separator + context.getTemplatePath();
		}
		// 公共Java文件输出路径
		String commonJavaOutPath = context.getBasePath() + File.separator + context.getJavaSourceFolder()
				+ File.separator + context.getPackagePath();
		// 公共Web文件输出路径
		String commonWebOutPath = context.getBasePath() + File.separator + context.getWebSourceFolder() + File.separator
				+ context.getPackagePath();
		StringBuffer resultMsg = new StringBuffer();

		// 模版编译执行
		for (TemplateInfo templateInfo : templateInfoList) {
			String fileFullName;
			String fileName = className + templateInfo.getGenerateSuffix();
			if (!EmptyUtil.isEmpty(templateInfo.getGeneratePath())) {
				// 如果模版配置的生成文件路径不为空，则使用生成文件路径
				fileFullName = context.getBasePath() + File.separator + templateInfo.getGeneratePath() + File.separator
						+ fileName;
			} else {
				// 否则
				String generateSubPackagePath = templateInfo.getGenerateSubPackagePath().replace(".", File.separator);
				if ("java".equals(templateInfo.getGenerateType())) {
					// 如果类型为java,使用java路径
					fileFullName = commonJavaOutPath + File.separator + generateSubPackagePath + File.separator
							+ fileName;
				} else {
					// 否则则为web
					fileFullName = commonWebOutPath + File.separator + generateSubPackagePath + File.separator
							+ fileName;
				}
			}
			// 模版路径
			String templatePath = EmptyUtil.isEmpty(templateInfo.getTemplatePath())
					? commonTemplatePath + File.separator + templateInfo.getTemplateType()
					: templateInfo.getTemplatePath();
			// 解析模版所需要的参数Map
			BuildInfo buildInfo = new BuildInfo();
			String packageName = (context.getPackagePath() + File.separator + templateInfo.getGenerateSubPackagePath())
					.replaceAll("[/\\\\]", ".");// 包名
			buildInfo.setBasePackageName(context.getPackagePath().replaceAll("[/\\\\]", "."));
			buildInfo.setPackageName(packageName);
			buildInfo.setTableInfo(tableInfo);
			buildInfo.setColumnInfos(tableInfo.getColumns());
			buildFile(resultMsg, templatePath, templateInfo.getTemplateName(), fileFullName, buildInfo);
		}
		return resultMsg.toString();
	}

	/**
	 * 构建文件
	 * 
	 * @param rsMsg
	 * @param templatePath
	 * @param templateFileName
	 * @param file
	 * @param map
	 * @author Alan
	 * @time 2015-11-11 下午05:51:28
	 */
	private static void buildFile(StringBuffer rsMsg, String templatePath, String templateFileName, String file,
			BuildInfo buildInfo) {
		log.info(templateFileName + "模版文件开始生成。。。");
		String fileName = FreemarkerUtil.compile(templatePath, templateFileName, buildInfo, file);
		if (EmptyUtil.isEmpty(fileName)) {
			String msg = templateFileName + "模版文件生成失败。。。";
			rsMsg.append(msg + "\n");
			log.info(msg);
		} else {
			String msg = fileName + "文件生成完毕。。。";
			rsMsg.append(msg + "\n");
			log.info(msg);
		}
	}
}
