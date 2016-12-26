package com.ctosb.codebuild;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * GridBagConstraints子类，方便set值
 * 
 * @author Alan
 * @date 2015-11-21 上午01:06:58
 * 
 */
public class GBC extends GridBagConstraints {

    /**
	 * 
	 */
    private static final long serialVersionUID = -1546789091354586300L;

    /**
     * 默认构造函数
     */
    public GBC() {}

    /**
     * 初始化左上角位置
     * 
     * @param gridx
     * @param gridy
     */
    public GBC(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * 初始化左上角位置和所占行数和列数
     * 
     * @param gridx
     * @param gridy
     * @param gridwidth
     * @param gridheight
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight) {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * 所占行数和列数
     * 
     * @param gridwidth
     * @param gridheight
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:04:16
     */
    public GBC setSize(int gridwidth, int gridheight) {
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        return this;
    }

    /**
     * 左上角位置点
     * 
     * @param gridx
     * @param gridy
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:04:47
     */
    public GBC setXy(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
        return this;
    }

    /**
     * 对齐方式
     * 
     * @param anchor
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:05:04
     */
    public GBC setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    /**
     * 是否拉伸及拉伸方向
     * 
     * @param fill
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:05:13
     */
    public GBC setFill(int fill) {
        this.fill = fill;
        return this;
    }

    /**
     * x和y方向上的权重
     * 
     * @param weightx
     * @param weighty
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:05:22
     */
    public GBC setWeight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * 外部填充
     * 
     * @param distance
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:05:37
     */
    public GBC setInsets(int distance) {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * 外填充
     * 
     * @param top
     * @param left
     * @param bottom
     * @param right
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:05:45
     */
    public GBC setInsets(int top, int left, int bottom, int right) {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * 内填充
     * 
     * @param ipadx
     * @param ipady
     * @return
     * @author Alan
     * @time 2015-11-21 上午01:06:02
     */
    public GBC setIpad(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
