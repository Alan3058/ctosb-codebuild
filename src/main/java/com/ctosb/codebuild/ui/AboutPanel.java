package com.ctosb.codebuild.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * 配置界面
 * 
 * @author Alan
 * @date 2015-11-18 下午11:53:24
 * 
 */
public class AboutPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 3694701464725767547L;
    private JTextArea textArea;

    public AboutPanel() {
        this.setLayout(new BorderLayout());
        String aboutString = "作者：ctosb—雪碧心拔凉\r\n邮箱:361225485@qq.com\r\nQQ:361225485\r\nCopyright © 2015 CTOSB. All Rights Reserved";
        textArea = new JTextArea();
        textArea.setText(aboutString);
        this.add(textArea);
    }


    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame();
        AboutPanel connectionConfig = new AboutPanel();
        frame.add(connectionConfig);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);// 居中
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
