package com.aloha;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class UtilClass {
  /**
	 * 设置系统初始屏举重
	 * @param win
	 */
	public static void setCenter(JWindow win){
		Dimension  d=Toolkit.getDefaultToolkit().getScreenSize();
		win.setLocation((d.width-win.getWidth())/2, (d.height-win.getHeight())/2);
	}
	/**
	 * 设置主窗体举重
	 * @param frame
	 */
	public static void setCenter(JFrame frame){
		Dimension  d=Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width-frame.getWidth())/2, (d.height-frame.getHeight())/2);
	}
	/**
	 * 设置系统初始屏
	 * @param imagePath,初始屏图片相对路径
	 * @param time,停留time微妙的时间后自动销毁
	 */
	public static void setWin(String imagePath,int time){
		JWindow win=new JWindow();
		win.add(new JLabel(new ImageIcon(imagePath)));
		win.pack();
		setCenter(win);
		win.setVisible(true);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e1) {
			System.out.println(e1.getMessage());
		}
		win.dispose();
	}
}
