package com.ctosb.codebuild.ui;

import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.ctosb.codebuild.CompileFile;
import com.ctosb.codebuild.Context;
import com.ctosb.codebuild.GBC;
import com.ctosb.codebuild.model.TableInfo;
import com.ctosb.codebuild.model.TemplateInfo;
import com.ctosb.codebuild.process.DefaultlProcess;
import com.ctosb.codebuild.process.Process;
import com.ctosb.codebuild.util.EmptyUtil;

/**
 * 通过表构建页面
 * 
 * @author Alan
 * @date 2015-11-18 下午11:53:43
 * 
 */
public class BuildByTablePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 8483200961842659514L;
    private JScrollPane topScrollPane;// 顶部滚动条
    private JTable topTable;// 表格
    private JPanel bottomPanel;// 下部分panel，包含多选框panel，编辑框panel
    private JButton buildButton;// 生成按钮
    private JPanel checkboxPanel;// 多选框panel
    private JPanel textPanel;// 编辑框panel

    private Process process;
    private Context context;

    private Map<JCheckBox, TemplateInfo> templateMap = new HashMap<JCheckBox, TemplateInfo>();

    /**
     * Launch the application.
     * 
     * @throws UnsupportedLookAndFeelException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        frame.add(new BuildByTablePanel(new DefaultlProcess(Context.getInstance()), Context.getInstance()));
        // frame.pack();
        frame.setTitle("代码生成器");
        frame.setSize(600, 400);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Create the application.
     */
    public BuildByTablePanel(Process process, Context context) {
        this.process = process;
        this.context = context;
        initComponent();
        initLayout();
        initTable();
        initCheckbox();
    }

    /**
     * 初始化控件
     * 
     * @author Alan
     * @time 2015-11-19 上午12:22:48
     */
    private void initComponent() {
        topScrollPane = new JScrollPane();// 顶部滚动条
        // 表格
        topTable = new JTable() {
            private static final long serialVersionUID = -5393006704708503248L;

            // 设置table不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bottomPanel = new JPanel();// 下部分panel，包含多选框panel，编辑框panel
        buildButton = new JButton("生成");// 生成按钮
        textPanel = new JPanel();
        checkboxPanel = new JPanel();
    }

    /**
     * 初始化页面布局
     * 
     * @author Alan
     * @time 2015-11-12 下午02:40:45
     */
    private void initLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        // gridBagLayout.rowHeights = new int[]{146, 0};
        gridBagLayout.columnWeights = new double[] {1.0, 0};
        gridBagLayout.rowWeights = new double[] {1.0, 0.2};
        this.setLayout(gridBagLayout);


        topScrollPane = new JScrollPane();
        GBC gbc = new GBC(0, 0).setFill(GBC.BOTH);
        this.add(topScrollPane, gbc);

        // 设置多选
        topTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // 设置列表头
        topTable.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"表名称", "描述", "类型"}));
        topTable.getTableHeader().setReorderingAllowed(false);// 设置列不能拖动
        topScrollPane.setViewportView(topTable);

        bottomPanel = new JPanel();
        gbc = new GBC(0, 1).setFill(GBC.VERTICAL);
        this.add(bottomPanel, gbc);

        bottomPanel.add(checkboxPanel);

        bottomPanel.add(textPanel);

        textPanel.add(buildButton);

        // 监听生成按钮事件
        buildButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int[] rowIds = topTable.getSelectedRows();
                // 校验是否选中
                if (EmptyUtil.isEmpty(rowIds)) {
                    JOptionPane.showMessageDialog(BuildByTablePanel.this, "请选择记录!");
                    return;
                }
                for (int rowId : rowIds) {
                    String tableName = (String) topTable.getValueAt(rowId, 0);// 表名
                    // 获取表信息
                    Collection<TableInfo> tableInfos = process.getTableInfoByTableName(tableName, null, true);
                    if (EmptyUtil.isEmpty(tableInfos)) {
                        // 默认值
                        tableInfos = new ArrayList<TableInfo>();
                    }
                    // 生成文件
                    for (TableInfo tableInfo : tableInfos) {
                        buildFile(tableInfo);
                    }
                }
            }
        });
    }

    /**
     * 初始化Jtable信息
     * 
     * @author Alan
     * @time 2015-11-12 下午02:36:15
     */
    private void initTable() {
        DefaultTableModel tableModel = (DefaultTableModel) topTable.getModel();
        Collection<TableInfo> tableInfos = process.getTableInfoByTableName(null, null, false);
        if (EmptyUtil.isEmpty(tableInfos)) {
            // 默认
            tableInfos = new ArrayList<TableInfo>();
        }
        // 清空表格数据
        tableModel.setRowCount(0);
        for (TableInfo tableInfo : tableInfos) {
            tableModel.addRow(new Object[] {tableInfo.getTabName(), tableInfo.getTabComment(), tableInfo.getTabType()});
        }
    }

    /**
     * 初始化多选框
     * 
     * @author alan
     * @date 2014-8-12 下午3:53:32
     */
    private void initCheckbox() {
        List<TemplateInfo> templateInfoList = context.getTemplateInfo().get("byTable");
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
        // 获取需要生成的文件模版
        for (JCheckBox jCheckBox : templateMap.keySet()) {
            if (jCheckBox.isSelected()) {
                templateInfoList.add(templateMap.get(jCheckBox));
            }
        }
        // 生成文件
        String resultMsg = CompileFile.build(context, tableInfo, templateInfoList);
        JOptionPane.showMessageDialog(this, resultMsg);
    }

}
