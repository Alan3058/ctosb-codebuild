package com.ctosb.codebuild.ui;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ctosb.codebuild.Context;
import com.ctosb.codebuild.GBC;
import com.ctosb.codebuild.model.TemplateInfo;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 配置界面
 * 
 * @author Alan
 * @date 2015-11-18 下午11:53:24
 * 
 */
public class ConfigPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 3694701464725767546L;
    private JLabel jdbcDriverLabel;
    private JTextField jdbcDriverText;
    private JLabel jdbcUrlLabel;
    private JTextField jdbcUrlText;
    private JLabel userNameLabel;
    private JTextField userNameText;
    private JLabel passwordLabel;
    private JTextField passwordText;
    private JLabel basePathLabel;
    private JTextField basePathText;
    private JLabel javaSourceFolderLabel;
    private JTextField javaSourceFolderText;
    private JLabel webSourceFolderLabel;
    private JTextField webSourceFolderText;
    private JLabel packageNameLabel;
    private JTextField packageNameText;
    private JLabel filterFieldLabel;
    private JTextField filterFieldText;
    private JLabel templatePathLabel;
    private JTextField templatePathText;
    private JLabel templateInfosLabel;
    private JTable templateInfosTable;

    private JButton confirmButton;
    private JButton cancelButton;
    private Context context;

    public ConfigPanel() {
        this(null);
    }

    public ConfigPanel(Context context) {
        this.context = context;
        initComponent();// 初始化组件
        initLayout();// 初始化布局
        initData();// 初始化数据
    }

    /**
     * 初始化控件
     * 
     * @author Alan
     * @time 2015-11-19 上午12:11:01
     */
    private void initComponent() {
        jdbcDriverLabel = new JLabel("驱动");
        jdbcDriverText = new JTextField();
        jdbcUrlLabel = new JLabel("jdbcUrl");
        jdbcUrlText = new JTextField();
        userNameLabel = new JLabel("用户名");
        userNameText = new JTextField();
        passwordLabel = new JLabel("密码");
        passwordText = new JTextField();
        basePathLabel = new JLabel("基本路径");
        basePathText = new JTextField();
        javaSourceFolderLabel = new JLabel("java源路径");
        javaSourceFolderText = new JTextField();
        webSourceFolderLabel = new JLabel("web源路径");
        webSourceFolderText = new JTextField();
        packageNameLabel = new JLabel("包名");
        packageNameText = new JTextField();
        filterFieldLabel = new JLabel("过滤字段");
        filterFieldText = new JTextField();
        templatePathLabel = new JLabel("模版路径");
        templatePathText = new JTextField();
        templateInfosLabel = new JLabel("模版信息");
        templateInfosTable = new JTable();

        confirmButton = new JButton("确认");
        cancelButton = new JButton("取消");
        // 为按钮设置监听器
        MouseListener mouseListener = new MouseListener();
        confirmButton.addMouseListener(mouseListener);
        cancelButton.addMouseListener(mouseListener);
    }

    /**
     * 初始化数据
     * 
     * @author Alan
     * @time 2015-11-19 上午12:11:18
     */
    private void initData() {
        if (EmptyUtil.isEmpty(this.context)) {
            // 为空的话，则清空
            context = Context.getInstance();
        }
        jdbcDriverText.setText(context.getJdbcDriver());
        jdbcUrlText.setText(context.getJdbcUrl());
        userNameText.setText(context.getJdbcUserName());
        passwordText.setText(context.getJdbcPassword());
        basePathText.setText(context.getBasePath());
        javaSourceFolderText.setText(context.getJavaSourceFolder());
        webSourceFolderText.setText(context.getWebSourceFolder());
        packageNameText.setText(context.getPackagePath());
        filterFieldText.setText(context.getFilterField());
        templatePathText.setText(context.getTemplatePath());

        Map<String, List<TemplateInfo>> templateInfoMap = context.getTemplateInfo();
        if (EmptyUtil.isEmpty(templateInfoMap)) {
            // 清空
            templateInfoMap = new HashMap<String, List<TemplateInfo>>();
        }
        DefaultTableModel tableModel = (DefaultTableModel) templateInfosTable.getModel();
        tableModel.setRowCount(0);// 清空表格数据
        // 填充表格数据
        for (String key : templateInfoMap.keySet()) {
            List<TemplateInfo> templateInfoList = templateInfoMap.get(key);
            for (TemplateInfo templateInfo : templateInfoList) {
                tableModel.addRow(new Object[] {key, templateInfo.getTemplateName(), templateInfo.getTemplatePath(),
                        templateInfo.getGenerateType(), templateInfo.getGeneratePath(), templateInfo.getGenerateSubPackagePath(),
                        templateInfo.getGenerateSuffix()});
            }
        }
    }

    /**
     * 初始化布局
     * 
     * @author Alan
     * @time 2015-11-19 上午12:11:35
     */
    private void initLayout() {
        JScrollPane scrollPane = new JScrollPane();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // 连接信息布局
        JPanel connectionInfoPanel = new JPanel();
        connectionInfoPanel.setLayout(new GridBagLayout());
        connectionInfoPanel.setBorder(BorderFactory.createTitledBorder("连接信息"));
        connectionInfoPanel.add(jdbcDriverLabel, new GBC(0, 0));
        connectionInfoPanel.add(jdbcDriverText, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));
        connectionInfoPanel.add(jdbcUrlLabel, new GBC(2, 0));
        connectionInfoPanel.add(jdbcUrlText, new GBC(3, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));

        connectionInfoPanel.add(userNameLabel, new GBC(0, 1));
        connectionInfoPanel.add(userNameText, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));
        connectionInfoPanel.add(passwordLabel, new GBC(2, 1));
        connectionInfoPanel.add(passwordText, new GBC(3, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));

        // 构建信息布局
        JPanel buildInfoPanel = new JPanel();
        buildInfoPanel.setLayout(new GridBagLayout());
        buildInfoPanel.setBorder(BorderFactory.createTitledBorder("构建信息"));
        buildInfoPanel.add(basePathLabel, new GBC(0, 0));
        buildInfoPanel.add(basePathText, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));
        buildInfoPanel.add(javaSourceFolderLabel, new GBC(2, 0));
        buildInfoPanel.add(javaSourceFolderText, new GBC(3, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));

        buildInfoPanel.add(webSourceFolderLabel, new GBC(0, 1));
        buildInfoPanel.add(webSourceFolderText, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));
        buildInfoPanel.add(packageNameLabel, new GBC(2, 1));
        buildInfoPanel.add(packageNameText, new GBC(3, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));
        buildInfoPanel.add(filterFieldLabel, new GBC(0, 2));
        buildInfoPanel.add(filterFieldText, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(1, 0));


        // 构建模块信息布局
        JPanel templateInfoPanel = new JPanel();
        templateInfoPanel.setLayout(new GridBagLayout());
        templateInfoPanel.setBorder(BorderFactory.createTitledBorder("模版信息"));

        templateInfoPanel.add(templatePathLabel, new GBC(0, 0));
        templateInfoPanel.add(templatePathText, new GBC(1, 0).setSize(1, 1).setFill(GBC.HORIZONTAL).setWeight(0, 0));
        templateInfoPanel.add(templateInfosLabel, new GBC(0, 1, 0, 1).setFill(GBC.HORIZONTAL));
        templateInfoPanel.add(scrollPane, new GBC(0, 2, 0, 1).setWeight(1, 1).setFill(GBC.BOTH));

        scrollPane.setViewportView(templateInfosTable);
        templateInfosTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"模版类型", "模版名称", "模版路径", "生成文件类型", "生成文件路径",
                "生成文件子包", "生成文件后缀"}));
        templateInfosTable.getTableHeader().setReorderingAllowed(false);// 设置列不能拖动
        // 构建按钮块布局
        Box buttonBox = Box.createHorizontalBox();
        buttonBox.add(confirmButton);
        buttonBox.add(Box.createHorizontalStrut(20));
        buttonBox.add(cancelButton);

        this.add(Box.createVerticalStrut(10));
        this.add(connectionInfoPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(buildInfoPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(templateInfoPanel);
        this.add(Box.createVerticalStrut(10));
        this.add(buttonBox);
        this.add(Box.createVerticalStrut(10));
    }

    // /**
    // * 初始化布局
    // *
    // * @author Alan
    // * @time 2015-11-19 上午12:11:35
    // */
    // private void initLayout() {
    // JScrollPane scrollPane = new JScrollPane();
    //
    // GridBagLayout gridBagLayout = new GridBagLayout();
    // this.setLayout(gridBagLayout);
    //
    //
    // this.add(jdbcDriverLabel, new GBC(0, 0));
    // this.add(jdbcDriverText, new GBC(1, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    // this.add(jdbcUrlLabel, new GBC(2, 0));
    // this.add(jdbcUrlText, new GBC(3, 0).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    //
    // this.add(userNameLabel, new GBC(0, 1));
    // this.add(userNameText, new GBC(1, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    // this.add(passwordLabel, new GBC(2, 1));
    // this.add(passwordText, new GBC(3, 1).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    //
    // this.add(basePathLabel, new GBC(0, 2));
    // this.add(basePathText, new GBC(1, 2).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    // this.add(javaSourceFolderLabel, new GBC(2, 2));
    // this.add(javaSourceFolderText, new GBC(3, 2).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    //
    // this.add(webSourceFolderLabel, new GBC(0, 3));
    // this.add(webSourceFolderText, new GBC(1, 3).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    // this.add(packageNameLabel, new GBC(2, 3));
    // this.add(packageNameText, new GBC(3, 3).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    //
    // this.add(filterFieldLabel, new GBC(0, 4));
    // this.add(filterFieldText, new GBC(1, 4).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    // this.add(templatePathLabel, new GBC(2, 4));
    // this.add(templatePathText, new GBC(3, 4).setFill(GBC.HORIZONTAL).setWeight(1, 0));
    //
    // this.add(templateInfosLabel, new GBC(0, 5, 0, 1).setFill(GBC.HORIZONTAL));
    // this.add(scrollPane, new GBC(0, 6, 0, 1).setWeight(1, 1).setFill(GBC.BOTH));
    // scrollPane.setViewportView(templateInfosTable);
    // templateInfosTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"模版类型", "模版名称", "模版路径", "生成文件类型", "生成文件路径",
    // "生成文件子包", "生成文件后缀"}));
    // templateInfosTable.getTableHeader().setReorderingAllowed(false);// 设置列不能拖动
    // this.add(confirmButton, new GBC(1, 7));
    // this.add(cancelButton, new GBC(2, 7));
    // }

    /**
     * 鼠标监听器
     * 
     * @author Alan
     * @date 2015-11-21 上午12:27:04
     * 
     */
    private class MouseListener extends MouseAdapter {

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            Object target = e.getSource();
            if (target == confirmButton) {
                saveConfigInfoToContext();
                context.saveConfigInfoToFile();//保存配置信息到文件中
                JOptionPane.showMessageDialog(null, "保存成功");
            } else if (target == cancelButton) {
                // 取消，初始化表单数据
                initData();
            }
        }

    }
    /**
     *  保存配置信息到context中
     * 
     * @author Alan
     * @createTime 2016年2月23日 下午6:53:22
     */
    private  void saveConfigInfoToContext() {
        // 确认按钮，保存表单数据到上下文Context
        context.setJdbcDriver(jdbcDriverText.getText());
        context.setJdbcUrl(jdbcUrlText.getText());
        context.setJdbcUserName(userNameText.getText());
        context.setJdbcPassword(passwordText.getText());
        context.setBasePath(basePathText.getText());
        context.setJavaSourceFolder(javaSourceFolderText.getText());
        context.setWebSourceFolder(webSourceFolderText.getText());
        context.setPackagePath(packageNameText.getText());
        context.setFilterField(filterFieldText.getText());
        context.setTemplatePath(templatePathText.getText());
        int rowCount = templateInfosTable.getRowCount();
        Map<String, List<TemplateInfo>> templateInfoMap = new HashMap<String, List<TemplateInfo>>();
        for (int rowNum = 0; rowNum < rowCount; rowNum++) {
            TemplateInfo templateInfo = new TemplateInfo();
            int columnNum = 0;
            String templateType = (String) templateInfosTable.getValueAt(rowNum, columnNum++);
            templateInfo.setTemplateType(templateType);
            templateInfo.setTemplateName((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            templateInfo.setTemplatePath((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            templateInfo.setGenerateType((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            templateInfo.setGeneratePath((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            templateInfo.setGenerateSubPackagePath((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            templateInfo.setGenerateSuffix((String) templateInfosTable.getValueAt(rowNum, columnNum++));
            // 保存模版信息到Map中
            if (templateInfoMap.containsKey(templateType)) {
                templateInfoMap.get(templateType).add(templateInfo);
            } else {
                List<TemplateInfo> templateInfoList = new ArrayList<TemplateInfo>();
                templateInfoList.add(templateInfo);
                templateInfoMap.put(templateType, templateInfoList);

            }
        }
        context.setTemplateInfo(templateInfoMap);
    }

    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        ConfigPanel connectionConfig = new ConfigPanel();
        frame.add(connectionConfig);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);// 居中
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
