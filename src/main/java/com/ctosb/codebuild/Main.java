package com.ctosb.codebuild;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.ctosb.codebuild.process.DefaultlProcess;
import com.ctosb.codebuild.process.Process;
import com.ctosb.codebuild.ui.AboutPanel;
import com.ctosb.codebuild.ui.BuildBySqlPanel;
import com.ctosb.codebuild.ui.BuildByTablePanel;
import com.ctosb.codebuild.ui.ConfigPanel;
import com.ctosb.codebuild.ui.TabCloseButton;

/**
 * 主界面
 * 
 * @author Alan
 * @date 2015-11-18 下午11:54:40
 * 
 */
public class Main extends MouseAdapter {

    private JFrame frame = new JFrame("代码生成器");
    private JTabbedPane tab = new JTabbedPane();
    private JMenuItem menuItemSetting = new JMenuItem("设置");
    private JMenuItem menuItemByTable = new JMenuItem("通过表");
    private JMenuItem menuItemBySql = new JMenuItem("通过SQL");
    private JMenuItem menuItemAbout = new JMenuItem("关于");
    private JButton buttonSetting = new JButton("设置");
    private JButton buttonByTable = new JButton("通过表");
    private JButton buttonBySql = new JButton("通过SQL");
    private JTextArea introductionLabel = new JTextArea();
    private BuildByTablePanel buildByTable;
    private ConfigPanel configPanel;
    private AboutPanel aboutPanel;
    private BuildBySqlPanel buildBySql;
    private Context context;
    private Process process;

    public Main() {
        this(Context.getInstance());
    }

    public Main(String configFileName) {
        this(Context.getInstance(configFileName));
    }

    public Main(Context context) {
        this.context = context;
        process = new DefaultlProcess(this.context);
        initMenuBar();
        initToolBar();
        initComponent();// 初始化组件
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icons/code.png")));
        frame.setSize(500, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * 初始化组件
     * 
     * @author Alan
     * @time 2015-11-21 上午11:33:50
     */
    private void initComponent() {
        frame.add(tab);// 添加tab切换卡
        // 构建首页
        JPanel panelMain = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(introductionLabel);
        panelMain.setLayout(new BorderLayout());
        panelMain.add(scrollPane);

        String introductionText =
                "ctosb-codebuild模块构建初衷是作者在开发J2EE项目过程中，由于长期处于代码Copy and Paste，导致开发效率极低，开发成本过高，导致开发人员在开发过程中心情极坏，So So So bad。出于该初衷，毅然决定自己开发一套代码自动生成的工具，以便提高开发效率，使程序员能够高效愉快的工作。";
        introductionLabel.setLineWrap(true);// 设置自动换行
        introductionLabel.setEditable(false);
        introductionLabel.setText(introductionText);
        tab.addTab("首页", panelMain);
    }

    /**
     * 初始化菜单栏
     * 
     * @author Alan
     * @createTime 2015年12月5日 上午10:04:56
     */
    private void initMenuBar() {
        // 构建选项菜单
        JMenu selectionMenu = new JMenu("选项");
        selectionMenu.add(menuItemSetting);
        // 构建操作菜单
        JMenu operationMenu = new JMenu("操作");
        operationMenu.add(menuItemByTable);
        operationMenu.add(menuItemBySql);
        // 构建帮助菜单
        JMenu helpMenu = new JMenu("帮助");
        helpMenu.add(menuItemAbout);
        // 构建菜单栏，并给菜单栏下添加菜单
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(selectionMenu);
        menuBar.add(operationMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
        // 为菜单项添加点击触发器
        menuItemSetting.addMouseListener(this);
        menuItemByTable.addMouseListener(this);
        menuItemBySql.addMouseListener(this);
        menuItemAbout.addMouseListener(this);
    }

    /**
     * 初始化工具栏
     * 
     * @author Alan
     * @createTime 2015年12月5日 上午10:04:47
     */
    private void initToolBar() {
        // 构建工具栏
        JToolBar toolBar = new JToolBar();
        toolBar.add(buttonSetting);
        toolBar.addSeparator();
        toolBar.add(buttonByTable);
        toolBar.addSeparator();
        toolBar.add(buttonBySql);
        buttonSetting.addMouseListener(this);
        buttonByTable.addMouseListener(this);
        buttonBySql.addMouseListener(this);
        frame.add(toolBar, BorderLayout.NORTH);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        Object target = e.getSource();
        if (target == menuItemBySql || target == buttonBySql) {
            // 通过sql生成
            if (buildBySql == null  || tab.indexOfComponent(buildBySql) == -1) {
                buildBySql = new BuildBySqlPanel(process, context);
            }
            
            tab.addTab("通过SQL", buildBySql);
            tab.setTabComponentAt(tab.indexOfComponent(buildBySql), new TabCloseButton(tab));
            tab.setSelectedComponent(buildBySql);
        } else if (target == menuItemByTable || target == buttonByTable) {
            // 通过表生成
            if (buildByTable == null || tab.indexOfComponent(buildByTable) == -1) {
                buildByTable = new BuildByTablePanel(process, context);
            }
            tab.addTab("通过表", buildByTable);
            tab.setTabComponentAt(tab.indexOfComponent(buildByTable), new TabCloseButton(tab));
            tab.setSelectedComponent(buildByTable);
        } else if (target == menuItemSetting || target == buttonSetting) {
            // 设置
            if (configPanel == null || tab.indexOfComponent(configPanel) == -1) {
                configPanel = new ConfigPanel(context);
            }
            tab.addTab("设置", configPanel);
            tab.setTabComponentAt(tab.indexOfComponent(configPanel), new TabCloseButton(tab));
            tab.setSelectedComponent(configPanel);
        } else if (target == menuItemAbout) {
            // 关于
            if (aboutPanel == null || tab.indexOfComponent(aboutPanel) == -1) {
                aboutPanel = new AboutPanel();
            }
            tab.addTab("关于", aboutPanel);
            tab.setTabComponentAt(tab.indexOfComponent(aboutPanel), new TabCloseButton(tab));
            tab.setSelectedComponent(aboutPanel);
        }
    }

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Main();
    }
}
