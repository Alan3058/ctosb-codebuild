package com.ctosb.codebuild.ui;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.ctosb.codebuild.CompileFile;
import com.ctosb.codebuild.Context;
import com.ctosb.codebuild.GBC;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.model.TemplateInfo;
import com.ctosb.codebuild.process.DefaultlProcess;
import com.ctosb.codebuild.process.Process;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 通过sql构建的界面
 * 
 * @author Alan
 * @date 2015-11-18 下午11:54:07
 * 
 */
public class BuildBySqlPanel extends JPanel {

    /**
	 * 
	 */
    private static final long serialVersionUID = 6394984228829668515L;
    private JTextArea sqlText;
    private JPanel bottomPanel;// 下部分panel，包含多选框panel，编辑框panel
    private JButton buildButton;// 生成按钮
    private JPanel checkboxPanel;// 多选框panel
    private JLabel classNameLabel;// 类名标签
    private JTextField classNameText;// 类名输入文本框
    private JPanel textPanel;// 编辑框panel

    private Process process;
    private Context context;

    private Map<JCheckBox, TemplateInfo> templateMap = new HashMap<JCheckBox, TemplateInfo>();


    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        frame.add(new BuildBySqlPanel(new DefaultlProcess(Context.getInstance()), Context.getInstance()));
        frame.setTitle("代码生成器");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the application.
     */
    public BuildBySqlPanel(Process process, Context context) {
        this.process = process;
        this.context = context;
        initComponent();
        initLayout();
        initCheckbox();
    }

    /**
     * 初始化控件
     * 
     * @author Alan
     * @time 2015-11-19 上午12:22:30
     */
    private void initComponent() {
        sqlText = new JTextArea();
        bottomPanel = new JPanel();// 下部分panel，包含多选框panel，编辑框panel
        textPanel = new JPanel();
        classNameLabel = new JLabel("类名");
        classNameText = new JTextField();
        buildButton = new JButton("生成");// 生成按钮
        checkboxPanel = new JPanel();
    }

    /**
     * 初始化页面布局
     * 
     * @author Alan
     * @time 2015-11-12 下午02:40:45
     */
    private void initLayout() {
        this.setLayout(new GridBagLayout());
        JScrollPane scrollPane = new JScrollPane();//为文本框添加滚动条
        scrollPane.setViewportView(sqlText);
        this.add(scrollPane, new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH));
        this.add(bottomPanel, new GBC(0, 1).setWeight(0, 0.2).setFill(GBC.VERTICAL));
        bottomPanel.add(checkboxPanel);
        bottomPanel.add(textPanel);
        textPanel.add(classNameLabel);
        textPanel.add(classNameText);
        classNameText.setColumns(30);
        textPanel.add(buildButton);

        // 监听生成按钮事件
        buildButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                String sql = sqlText.getText();
                if (EmptyUtil.isEmptyTrim(sql)) {
                    JOptionPane.showMessageDialog(BuildBySqlPanel.this, "未填写sql!");
                    return;
                }
                if (EmptyUtil.isEmptyTrim(classNameText.getText())) {
                    JOptionPane.showMessageDialog(BuildBySqlPanel.this, "未填写类名!");
                    return;
                }
                // 获取表信息
                TableInfo tableInfo = process.getTableInfoBySql(sql);
                tableInfo.setUpperCamelTabName(classNameText.getText());
                tableInfo.setCamelTabName(classNameText.getText().substring(0, 1).toLowerCase() + classNameText.getText().substring(1));
                // 生成文件
                buildFile(tableInfo);
            }
        });
    }

    /**
     * 初始化多选框
     * 
     * @author alan
     * @date 2014-8-12 下午3:53:32
     */
    private void initCheckbox() {
        List<TemplateInfo> templateInfoList = context.getTemplateInfo().get("bySql");
        // 自动生成多选框
        for (TemplateInfo templateInfo : templateInfoList) {
            JCheckBox checkBox = new JCheckBox(templateInfo.getTemplateName().replace(".ftl", ""));
            checkBox.setSelected(true);
            templateMap.put(checkBox, templateInfo);
            checkboxPanel.add(checkBox);
        }
    }

    /**
     * 生成文件
     * 
     * @param tableInfo
     * @author Alan
     * @time 2015-11-12 下午02:36:04
     */
    private void buildFile(TableInfo tableInfo) {
        List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
        //获取需要生成的文件模版
        for (JCheckBox jCheckBox : templateMap.keySet()) {
            if (jCheckBox.isSelected()) {
                templateInfoList.add(templateMap.get(jCheckBox));
            }
        }
        //生成文件
        String resultMsg = CompileFile.build(context, tableInfo, templateInfoList);
        JOptionPane.showMessageDialog(this, resultMsg);
    }

}
