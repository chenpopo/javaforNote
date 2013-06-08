package com.aloha.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JFontChooser extends JPanel implements ActionListener {

	private  Font font;
    private  Color color;
    private JTextField jtFont = new JTextField(10);//����
    private JTextField jtFontStyle = new JTextField(10); //��ʽ
    private JTextField jtFontSize = new JTextField(10);//��С
    private JList jlFontStyle;
    private JList jlFontSize;
    private JList jlFont;
    
    private JDialog jd;
    private JLabel showJLabel = new JLabel();//��ʾ��ǰ����
    private JLabel instructionJLabel = new JLabel();//�Ե�ǰ�������˵��
    private GraphicsEnvironment ge =  GraphicsEnvironment.getLocalGraphicsEnvironment();
    private  String[] jfontStr = ge.getAvailableFontFamilyNames();
    private String[] fontStyle = {"����","б��","����","��б��"};
    private String[] fontSize = {"8","9","10","11","12","14","16","18","20","22","24","26","28",
    "36","48","72","����","С��","һ��","Сһ","����","С��","����","С��","�ĺ�","С��","���","С��",
    "����","С��","�ߺ�","�˺�"};
    private int[] sizeValue={8,9,10,11,12,14,16,18,20,22,24,26,28,36,48,72,42,36,26,24,22,18,16,15,
    14,12,11,9,7,6,5,4};
    private JButton ok = new JButton("ȷ��");
    private JButton cancel = new JButton("ȡ��");
    //ɾ�������»���
    private JCheckBox jcDelLine = new JCheckBox("ɾ����(K)");
    private JCheckBox jcDownLine = new JCheckBox("�»���(U)");
    //��ɫ
    private Object[] o = {"��ɫ","��ɫ","��ɫ","���ɫ","��ɫ","��ɫ","ǳ��ɫ","���ɫ","�ۻ�ɫ","�ۺ�ɫ","��ɫ","��ɫ","��ɫ"};
    private Color[] colorValue = {Color.BLACK,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.WHITE,Color.YELLOW};
    private JComboBox jcb = new JComboBox(o);
    /** Creates a new instance of JFontChooser */
    public JFontChooser() {
        initJFont();
        initJFontStyle();
        initJFontSize();
        initOther();
        initComponentUpdate();
        initJDialog();
    }
    public JFontChooser(Font f,Color c){  //���ݴ���������������ɫ��������
        initJFont();
        initJFontStyle();
        initJFontSize();
        initOther();
        this.font = f;
        this.color = c;
        setFontAndColor(f,c);
        initComponentUpdate();
        initJDialog();
        
    }
    private void initOther(){
        //Ĭ����ɫ������
        updateFont();//����һ��Ĭ����������ɫ
        //Ч����ʾ��
        JLabel jl4 = new JLabel();
        jl4.setBorder(BorderFactory.createTitledBorder("Ч��"));
        jl4.setBounds(10,160,150,130);
        this.add(jl4);
        JLabel jl5 = new JLabel();
        jl5.setBorder(BorderFactory.createTitledBorder("ʾ��"));
        jl5.setBounds(170,160,220,130);
        this.add(jl5);
        //ok��ȡ����ť
        ok.setBounds(400,30,80,25);
        ok.addActionListener(this);
        cancel.addActionListener(this);
        cancel.setBounds(400,60,80,25);
        this.add(ok);
        this.add(cancel);
        //ʾ��
        showJLabel.setText((String)jlFont.getSelectedValue());
        showJLabel.setHorizontalAlignment(JLabel.CENTER);
        showJLabel.setBounds(180,150,200,150);
        this.add(showJLabel);
        //Ч����� ɾ�������»���
        jcDelLine.setBounds(15,180,100,25);
        jcDownLine.setBounds(15,200,100,25);
        this.add(jcDelLine);
        this.add(jcDownLine);
        JLabel jl6 = new JLabel("��ɫ(C):");
        jl6.setBounds(15,230,100,25);
        //��ɫ
        jcb.addActionListener(this);
        jcb.setBounds(17,255,130,22);
        jcDelLine.addActionListener(this);
        jcDownLine.addActionListener(this);
        this.add(jl6);
        this.add(jcb);
        //�����״̬˵��
        instructionJLabel.setText("������������ʾ,��ӡʱ��ʹ����ӽ���ƥ������");
        instructionJLabel.setBounds(17,290,400,25);
        this.add(instructionJLabel);
    }
    private void initComponentUpdate(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	/**
	 * ���ݴ�������font��color����������������ɫ
	 */
	private void setFontAndColor(Font f,Color c){
	    String fontStr = f.getFamily();
	    int style = f.getStyle();//���������������ʽ
	    int size = f.getSize();//������������Ĵ�С
	    for(int i =0;i<jfontStr.length;i++){
	        if(jfontStr[i].equals(fontStr)){
	            jlFont.setSelectedIndex(i);
	            break;
	        }
	    }
	    switch (style){
	        case Font.PLAIN:
	            jlFontStyle.setSelectedIndex(0);
	            break;
	        case Font.ITALIC:
	            jlFontStyle.setSelectedIndex(1);
	            break;
	        case Font.BOLD:
	            jlFontStyle.setSelectedIndex(2);
	            break;
	        case Font.BOLD|Font.ITALIC:
	            jlFontStyle.setSelectedIndex(3);
	            break;
	    }
	    for (int i =0;i<sizeValue.length;i++){
	        if(sizeValue[i] == size){
	            jlFontSize.setSelectedIndex(i);
	            break;
	        }
	    }
	    for(int i = 0;i<colorValue.length;i++){
	        if(colorValue[i].equals(c)){
	            jcb.setSelectedIndex(i);
	            break;
	        }
	    }
	    updateFont();
	}
	
	/**
	 * ʾ���иı�showJLabel��������ʽ
	 */
	private Font updateFont(){
	    String fontStr = (String)jlFont.getSelectedValue();
	    String styleStr = (String)jlFontStyle.getSelectedValue();
	    int style;
	    int size = sizeValue[jlFontSize.getSelectedIndex()];
	    if(styleStr.equals("����")){
	        style = Font.PLAIN;
	    }else if(styleStr.equals("б��")){
	        style = Font.ITALIC;
	    }else if(styleStr.equals("����")){
	        style = Font.BOLD;
	    }else{
	        style = Font.ITALIC | Font.BOLD;
	    }
	    Font f = new Font(fontStr,style,size);
	    //ɾ�������»���
	    String temp = (String)jlFont.getSelectedValue();
	    if(jcDelLine.isSelected()){
	        if(jcDownLine.isSelected()){
	            showJLabel.setText("<html><s><u>"+temp+"</u></s><html>");
	        }else if(!jcDownLine.isSelected()){
	            showJLabel.setText("<html><s>"+temp+"</s><html>");
	        }
	    }else if(!jcDelLine.isSelected()){
	        if(jcDownLine.isSelected()){
	            showJLabel.setText("<html><u>"+temp+"</u><html>");
	        }else if(!jcDownLine.isSelected()){
	            showJLabel.setText(temp);
	        }
	    }
	    //��ɫ
	    int select = jcb.getSelectedIndex();
	    showJLabel.setForeground(colorValue[select]);
	    return f;
	}
	/**
	 * ��ʼ��jfont ����
	 */
	private void initJFont(){
	    jlFont = new JList(jfontStr);
	    jlFont.setSelectedIndex(0);
	    jtFont.setText((String)jlFont.getSelectedValue());
	    JScrollPane jsp = new JScrollPane(jlFont);
	    jlFont.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	            jtFont.setText((String)jlFont.getSelectedValue());
	            showJLabel.setFont(updateFont());
	        }
	    });
	    JLabel jl1 = new JLabel("����(F:");
	    jl1.setBounds(10,0,80,30);
	    jtFont.setBounds(10,30,150,23);
	    jsp.setBounds(10,55,150,100);
	    this.add(jl1);
	    this.add(jtFont);
	    this.add(jsp);
	}
	
	/**
	 * ��ʼ��������ʽ
	 */
	private void initJFontStyle(){
	    jlFontStyle =new JList(fontStyle);
	    jlFontStyle.setSelectedIndex(0);
	    jtFontStyle.setText((String)jlFontStyle.getSelectedValue());
	    jlFontStyle.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	            jtFontStyle.setText((String)jlFontStyle.getSelectedValue());
	            showJLabel.setFont(updateFont());
	        }
	    });
	    JLabel j2 = new JLabel("����(Y): ");
	    j2.setBounds(170,0,80,30);
	    jtFontStyle.setBounds(170,30,130,23);
	    jlFontStyle.setBounds(170,55,130,100);
	    this.add(j2);
	    this.add(jtFontStyle);
	    this.add(jlFontStyle);
	}
	/**
	 * ��ʼ�������С
	 */
	private void initJFontSize(){
	    jlFontSize = new JList(fontSize);
	    jlFontSize.setSelectedIndex(0);
	    jtFontSize.setText((String)jlFontSize.getSelectedValue());
	    jlFontSize.addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent e) {
	            jtFontSize.setText((String)jlFontSize.getSelectedValue());
	            showJLabel.setFont(updateFont());
	        }
	    });
	    JScrollPane jspJL = new JScrollPane(jlFontSize);
	    JLabel j3 = new JLabel("��С(S): ");
	    j3.setBounds(310,0,80,30);
	    jtFontSize.setBounds(310,30,80,23);
	    jspJL.setBounds(310,55,80,100);
	    this.add(j3);
	    this.add(jtFontSize);
	    this.add(jspJL);
	}
	/**
	 * ��ʼ��JDialog
	 */
	private void initJDialog(){
	    this.setLayout(null);
	    jd = new JDialog();
	    jd.setResizable(false);
	    jd.setTitle("����ѡ����");
	    jd.setModal(true);
	    jd.setSize(510,360);
	    jd.add(this,BorderLayout.CENTER);
	    jd.setLocationRelativeTo(null);
	    jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	    jd.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource() == ok){
	        //��������
	        font = this.updateFont();
	        int select = jcb.getSelectedIndex();
	        showJLabel.setForeground(colorValue[select]);
	        color = colorValue[select];
	        jd.dispose();
	    }else if(e.getSource() == cancel){
	        jd.dispose();
	    }else if(e.getSource() == jcDelLine){
	        this.updateFont();
	    }else if(e.getSource() == jcDownLine){
	        this.updateFont();
	    }else if(e.getSource() == jcb){ //��ɫѡ��
	        this.updateFont();
	    }
	}
	public Font getFont(){
	    return font;
	}
	public Color getColor(){
	    return color;
	}
	/*
	public static void main(String[] args) {
	    //����,һ���Ǵ���ʼֵ��ȥ
	    JFontChooser jfc = new JFontChooser(f,c);
	    System.out.println(jfc.getFont());
	    System.out.println(jfc.getColor());
	   */
	/*
	    //�ڶ�����û�д���ʼֵ��ȥ
	    JFontChooser jfc = new JFontChooser();
	    System.out.println(jfc.getFont());
	    System.out.println(jfc.getColor());
	    
	}
	*/
}

