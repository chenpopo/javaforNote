package com.aloha.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JFindDialog extends JDialog {

	private JPanel panel=new JPanel();
	private JPanel pnlTop=new JPanel();
	private JPanel pnlRight=new JPanel();
	private JPanel pnlCenter=new JPanel();
	
	private JTextArea txtArea=new JTextArea();
	
	private JLabel lblFind=new JLabel();
	private JLabel lblTotal=new JLabel();
	
	private JTextField txtField=new JTextField();
	private JButton btnOK=new JButton();
	private JButton btnPrevious=new JButton();
	private JButton btnNext=new JButton();
	private JButton btnTotal=new JButton();
	
	private int index=0;
	private int x=400;
	private int y=300;
	public JFindDialog(JFrame frame,JTextArea textArea,boolean flag){
		super(frame,"查找",flag);
		txtArea=textArea;
		
		jInit();
		Dimension  d=Toolkit.getDefaultToolkit().getScreenSize();
		pack();
		setLocation((d.width-x)/2, (d.height-y)/2);
		this.setVisible(true);
	}
	
	public void jInit(){
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout());
		pnlTop.setLayout(new FlowLayout());
		
		lblFind.setText("查找:");
		pnlTop.add(lblFind);
		
		if(txtArea.getSelectedText()!=null){
			txtField.setText(txtArea.getSelectedText());
		}
		txtField.setColumns(20);
		pnlTop.add(txtField);
		
		pnlRight.setLayout(new GridLayout(4,1));
		
		btnOK.setText("查找");
		btnOK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				index=txtArea.getText().indexOf(txtField.getText());
				getSelectionText();
			}
		});
		pnlRight.add(btnOK);
		
		btnNext.setText("下一个");
		btnNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(index<txtArea.getText().length()){
					if(txtArea.getText().indexOf(txtField.getText(),index+txtField.getText().length())>-1){
						index=txtArea.getText().indexOf(txtField.getText(),index+txtField.getText().length());
						getSelectionText();
					}
				}
			}
		});
		pnlRight.add(btnNext);
		
		btnPrevious.setText("上一个");
		btnPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(index>0){
					String str=txtArea.getText().substring(0, index);
					if(str.lastIndexOf(txtField.getText())>0){
						index=str.lastIndexOf(txtField.getText());
					}
					txtArea.setSelectionStart(index);
					txtArea.setSelectionEnd(index+txtField.getText().length());
				}
			}
		});
		pnlRight.add(btnPrevious);
		
		btnTotal.setText("总计");
		btnTotal.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				lblTotal.setText("共有"+getSelectionTotal()+"处");
			}
		});
		pnlRight.add(btnTotal);
		
		panel.add(pnlRight,BorderLayout.EAST);
		
		panel.add(pnlTop,BorderLayout.NORTH);
		
		getSelectionTotal();
		lblTotal.setText("共有"+getSelectionTotal()+"处");
		
		pnlCenter.setLayout(new GridLayout(1,1));
		pnlCenter.add(lblTotal);
		
		
		panel.add(pnlCenter,BorderLayout.CENTER);
	}
	
	public void getSelectionText(){
		txtArea.setSelectionStart(index);
		txtArea.setSelectionEnd(index+txtField.getText().length());
	}
	
	public int getSelectionTotal(){
		int total=0;
		if(txtField.getText()!=null){
			while(txtArea.getText().indexOf(txtField.getText(),index)>0){
				total++;
				index=txtArea.getText().indexOf(txtField.getText(),index)+txtField.getText().length();
			}
			index=0;
		}
		return total;
	}
}
