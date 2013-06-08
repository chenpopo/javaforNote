package com.aloha.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.aloha.UtilClass;
/**
 * 
 * @author chenpo
 * @since 2008-2-14
 *
 */
public class StartFrame extends JFrame {
	
	private static final long serialVersionUID = -7960812041503098012L;
	
	private BorderLayout borderLayout1 = new BorderLayout ();
	private JScrollPane scrollPanel=new JScrollPane();
	private JTextArea textArea=new JTextArea();

	private JMenuBar menubar=new JMenuBar();
	private JMenu menuFile=new JMenu("�ļ�(F)");
	private JMenu menuEdit=new JMenu("�༭(E)");
	private JMenu menuFormat=new JMenu("��ʽ(O)");
	private JMenu menuSearch=new JMenu("�鿴(V)");
	private JMenu menuHelp=new JMenu("����(H)");
	
	private JMenuItem menuItemNew=new JMenuItem("�½�(N)");
	private JMenuItem menuItemOpen=new JMenuItem("��(O)");
	private JMenuItem menuItemSave=new JMenuItem("����(S)");
	private JMenuItem menuItemSaveAs=new JMenuItem("���Ϊ(A)");
	private JMenuItem menuItemPageSet=new JMenuItem("ҳ������(U)");
	private JMenuItem menuItemPrint=new JMenuItem("��ӡ(P)");
	private JMenuItem menuItemExit=new JMenuItem("�ر�(X)");
	
	private JMenuItem menuItemUndo=new JMenuItem("����(U)");
	private JMenuItem menuItemCut=new JMenuItem("����(X)");
	private JMenuItem menuItemCopy=new JMenuItem("����(C)");
	private JMenuItem menuItemPaste=new JMenuItem("ճ��(P)");
	private JMenuItem menuItemDel=new JMenuItem("ɾ��(L)");
	private JMenuItem menuItemSearch=new JMenuItem("����(F)");
	private JMenuItem menuItemReplace=new JMenuItem("�滻(T)");
	private JMenuItem menuItemGo=new JMenuItem("ת��(G)");
	private JMenuItem menuItemSelectAll=new JMenuItem("ȫѡ(A)");
	private JMenuItem menuItemDateTime=new JMenuItem("ʱ��/����(D)");
	
	private JCheckBoxMenuItem menuItemWrap=new JCheckBoxMenuItem("�Զ�����(W)");
	private JMenuItem menuItemStatus=new JMenuItem("״̬��(S)");
	private JMenuItem menuItemFont=new JMenuItem("����(F)");
	private JMenuItem menuItemForeground=new JMenuItem("ǰ��ɫ(F)");
	private JMenuItem menuItemBackgroud=new JMenuItem("����ɫ(B)");
	private JMenu styleMenu=new JMenu("���(S)");
	private JRadioButtonMenuItem metalMenuItem=new JRadioButtonMenuItem("Metal");
	private JRadioButtonMenuItem metifMenuItem=new JRadioButtonMenuItem("Metif");
	private JRadioButtonMenuItem windowsMenuItem=new JRadioButtonMenuItem("Windows");
	
	private JMenuItem menuItemHelpSubject=new JMenuItem("��������(H)");
	private JMenuItem menuItemNotes=new JMenuItem("���ڼ��±�(A)");
	
	private JToolBar toolBar=new JToolBar();
	private ButtonGroup buttonGroup=new ButtonGroup();
	private JButton newButton=new JButton(new ImageIcon("src\\image\\newFile.gif"));
	private JButton saveButton=new JButton(new ImageIcon("src\\image\\save.gif"));
	private JButton deleteButton=new JButton(new ImageIcon("src\\image\\delete.gif"));
	private JButton cutButton=new JButton(new ImageIcon("src\\image\\cut.gif"));
	private JButton pasteButton=new JButton(new ImageIcon("src\\image\\paste.gif"));
	
	private JPopupMenu popupMenu=new JPopupMenu();
	private JMenuItem popupMenuItemUndo=new JMenuItem("����(U)");
	private JMenuItem popupMenuItemCut=new JMenuItem("����(X)");
	private JMenuItem popupMenuItemCopy=new JMenuItem("����(C)");
	private JMenuItem popupMenuItemPaste=new JMenuItem("ճ��(P)");
	private JMenuItem popupMenuItemDel=new JMenuItem("ɾ��(L)");
	private JMenuItem popupMenuItemSelectAll=new JMenuItem("ȫѡ(A)");
	
	
	private Document document;
	//��¼��ǰ�ļ������Ƿ��޸��ˣ�����û�б��棻
	private boolean changed=false;
	//��¼��ǰ�ļ��ľ���·���ַ���
	private String fileName;
	
	private PrintPanel canvas;  
    private PageFormat pageFormat;
	
	public StartFrame(){
		setTitle("���±�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,600);
		init();
	}
	public void init(){
		UtilClass.setWin("src\\image\\java.jpg", 2000);
		this.setJMenuBar(menubar);
		
		menuItemNew.setIcon(new ImageIcon("src\\image\\newFile.gif"));
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		menuItemNew.setMnemonic('N');
		menuItemNew.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createFile();
			}
		});
		menuFile.add(menuItemNew);
		
		menuItemOpen.setIcon(new ImageIcon("src\\image\\open.gif"));
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		menuItemOpen.setMnemonic('O');
		menuItemOpen.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				openFile();
			}
		});
		menuFile.add(menuItemOpen);	
		
		menuItemSave.setIcon(new ImageIcon("src\\image\\save.gif"));
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		menuItemSave.setMnemonic('S');
		menuItemSave.setEnabled(false);
		menuItemSave.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveFile();
			}
		});
		menuFile.add(menuItemSave);
		
		menuItemSaveAs.setIcon(new ImageIcon("src\\image\\saveAs.gif"));
		menuItemSaveAs.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		menuItemSaveAs.setMnemonic('A');
		menuItemSaveAs.setEnabled(false);
		menuItemSaveAs.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveAsFile();
			}
		});
		menuFile.add(menuItemSaveAs);
		
		menuFile.addSeparator();
		
		menuItemPageSet.setIcon(new ImageIcon("src\\image\\pageSet.gif"));
		menuItemPageSet.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		menuItemPageSet.setMnemonic('U');
		menuItemPageSet.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PrinterJob printJob=PrinterJob.getPrinterJob();  
                if(pageFormat==null)
                	pageFormat=printJob.defaultPage();  
                pageFormat=printJob.pageDialog(pageFormat);
			}
		});
		menuFile.add(menuItemPageSet);
		
		menuItemPrint.setIcon(new ImageIcon("src\\image\\print.gif"));
		menuItemPrint.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
		menuItemPrint.setMnemonic('P');
		menuItemPrint.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				PrinterJob printJob=PrinterJob.getPrinterJob();  
                if(pageFormat==null)
                	pageFormat=printJob.defaultPage();
                printJob.setPrintable(canvas,pageFormat);
                if(printJob.printDialog()){     
                    try{ 
                    	printJob.print();  
                    }catch(PrinterException exception){   
                       exception.getMessage();
                    }  
                }  
			}
		});
		menuFile.add(menuItemPrint);
		
		menuFile.addSeparator();
		
		menuItemExit.setIcon(new ImageIcon("src\\image\\exit.gif"));
		menuItemExit.setMnemonic('X');
		menuItemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				myExit();
			}
		});
		menuFile.add(menuItemExit);
		menuFile.setIcon(new ImageIcon("src\\image\\file.gif"));
		menuFile.setMnemonic('F');
		menubar.add(menuFile);
		
		menuItemUndo.setIcon(new ImageIcon("src\\image\\undo.gif"));
		menuItemUndo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		menuItemUndo.setMnemonic('U');
		menuEdit.add(menuItemUndo);
		
		menuEdit.addSeparator();
		
		menuItemCut.setIcon(new ImageIcon("src\\image\\cut.gif"));
		menuItemCut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		menuItemCut.setMnemonic('X');
		menuItemCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.cut();
			}
		});
		menuEdit.add(menuItemCut);
		
		menuItemCopy.setIcon(new ImageIcon("src\\image\\copy.gif"));
		menuItemCopy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		menuItemCopy.setMnemonic('C');
		menuItemCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.copy();
			}
		});
		menuEdit.add(menuItemCopy);
		
		menuItemPaste.setIcon(new ImageIcon("src\\image\\paste.gif"));
		menuItemPaste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		menuItemPaste.setMnemonic('P');
		menuItemPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.paste();
			}
		});
		menuEdit.add(menuItemPaste);
		
		menuItemDel.setIcon(new ImageIcon("src\\image\\delete.gif"));
		menuItemDel.setAccelerator(KeyStroke.getKeyStroke(""+KeyEvent.VK_DELETE));
		menuItemDel.setMnemonic('L');
		menuItemDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.replaceSelection("");
			}
		});
		menuEdit.add(menuItemDel);
		
		menuEdit.addSeparator();
		menuItemSearch.setIcon(new ImageIcon("src\\image\\search.gif"));
		menuItemSearch.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		menuItemSearch.setMnemonic('F');
		menuItemSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new JFindDialog(StartFrame.this,textArea,true);
			}
		});
		menuEdit.add(menuItemSearch);
		
		menuItemReplace.setIcon(new ImageIcon("src\\image\\replace.gif"));
		menuItemReplace.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
		menuItemReplace.setMnemonic('R');
		menuEdit.add(menuItemReplace);
		
		menuItemGo.setIcon(new ImageIcon("src\\image\\go.gif"));
		menuItemGo.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
		menuItemGo.setMnemonic('G');
		menuEdit.add(menuItemGo);
		
		menuEdit.addSeparator();
		
		menuItemSelectAll.setIcon(new ImageIcon("src\\image\\selectAll.gif"));
		menuItemSelectAll.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
		menuItemSelectAll.setMnemonic('A');
		menuItemSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.selectAll();
			}
		});
		menuEdit.add(menuItemSelectAll);
		
		menuItemDateTime.setIcon(new ImageIcon("src\\image\\timer.gif"));
		menuItemDateTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
		menuItemDateTime.setMnemonic('D');
		menuItemDateTime.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//��ù��������λ��textArea.getCaretPosition()
				textArea.insert(getCurrentTime(), textArea.getCaretPosition());
			}
		});
		menuEdit.add(menuItemDateTime);
		
		menuEdit.setIcon(new ImageIcon("src\\image\\edit.gif"));
		menuEdit.setMnemonic('E');
		menubar.add(menuEdit);
		
		menuItemWrap.setIcon(new ImageIcon("src\\image\\wrap.gif"));
		menuItemWrap.setMnemonic('W');
		menuItemWrap.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(textArea.getLineWrap()){
					textArea.setLineWrap(false);
				}else{
					textArea.setLineWrap(true);
				}
			}
		});
		menuFormat.add(menuItemWrap);
		
		menuItemFont.setIcon(new ImageIcon("src\\image\\font.gif"));
		menuItemFont.setMnemonic('F');
		menuItemFont.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JFontChooser fontChooser=new JFontChooser(textArea.getFont(),textArea.getForeground());
				textArea.setFont(fontChooser.getFont());
			}
		});
		menuFormat.add(menuItemFont);
		
		menuItemForeground.setIcon(new ImageIcon("src\\image\\fgcolor.gif"));
		menuItemForeground.setMnemonic('F');
		menuItemForeground.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.setForeground(JColorChooser.showDialog(StartFrame.this, "�ı���ǰ��ɫѡ����", textArea.getForeground()));
			}
		});
		menuFormat.add(menuItemForeground);
		
		menuItemBackgroud.setIcon(new ImageIcon("src\\image\\bgcolor.gif"));
		menuItemBackgroud.setMnemonic('B');
		menuItemBackgroud.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Color color=JColorChooser.showDialog(StartFrame.this,"�ı���������ɫѡ����",textArea.getBackground());
				if(color!=null)
					textArea.setBackground(color);
			}
		});
		menuFormat.add(menuItemBackgroud);
		
		menuFormat.addSeparator();
		
		metalMenuItem.setMnemonic('l');
		metalMenuItem.setSelected(true);
		metalMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					UIManager.setLookAndFeel(LookAndFeel.getMetalPlaf());
					SwingUtilities.updateComponentTreeUI(menubar.getParent());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		styleMenu.add(metalMenuItem);
		
		metifMenuItem.setMnemonic('f');
		metifMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					UIManager.setLookAndFeel(LookAndFeel.getMetifPlaf());
					SwingUtilities.updateComponentTreeUI(menubar.getParent());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		styleMenu.add(metifMenuItem);
		
		windowsMenuItem.setMnemonic('s');
		windowsMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					UIManager.setLookAndFeel(LookAndFeel.getWindowsPlaf());
					SwingUtilities.updateComponentTreeUI(menubar.getParent());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		styleMenu.add(windowsMenuItem);
		
		styleMenu.setIcon(new ImageIcon("src\\image\\style.gif"));
		styleMenu.setMnemonic('S');
		buttonGroup.add(metalMenuItem);
		buttonGroup.add(metifMenuItem);
		buttonGroup.add(windowsMenuItem);
		menuFormat.add(styleMenu);
		menuFormat.setIcon(new ImageIcon("src\\image\\format.gif"));
		menuFormat.setMnemonic('O');
		menubar.add(menuFormat);
		
		menuItemStatus.setIcon(new ImageIcon("src\\image\\status.gif"));
		menuItemStatus.setMnemonic('S');
		menuSearch.add(menuItemStatus);
		menuSearch.setIcon(new ImageIcon("src\\image\\view.gif"));
		menuSearch.setMnemonic('V');
		menubar.add(menuSearch);
	
		menuItemHelpSubject.setIcon(new ImageIcon("src\\image\\helpSubject.gif"));
		menuItemHelpSubject.setMnemonic('H');
		menuHelp.add(menuItemHelpSubject);
		
		menuHelp.addSeparator();
		
		menuItemNotes.setIcon(new ImageIcon("src\\image\\home.gif"));
		menuItemNotes.setMnemonic('A');
		menuHelp.add(menuItemNotes);
		menuHelp.setIcon(new ImageIcon("src\\image\\help.png"));
		menuHelp.setMnemonic('H');
		menubar.add(menuHelp);
		
		newButton.setToolTipText("�½�");
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createFile();
			}
		});
		toolBar.add(newButton);
		
		saveButton.setToolTipText("����");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveFile();
			}
		});		
		toolBar.add(saveButton);

		
		cutButton.setToolTipText("����");
		cutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.cut();
			}
		});
		toolBar.add(cutButton);
		
		pasteButton.setToolTipText("ճ��");
		pasteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.paste();
			}
		});
		toolBar.add(pasteButton);
		
		deleteButton.setToolTipText("ɾ��");
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.replaceSelection("");
			}
		});
		toolBar.add(deleteButton);
		
		 getContentPane ().setLayout (borderLayout1);
		this.getContentPane().add(toolBar,BorderLayout.NORTH);
		
		UtilClass.setCenter(StartFrame.this);
		//setLocation((d.width-this.getWidth())/2, (d.height-this.getHeight())/2);
		
		this.getContentPane().add(scrollPanel);

		popupMenuItemUndo.setIcon(new ImageIcon("src\\image\\undo.gif"));
		popupMenuItemUndo.setAccelerator(KeyStroke.getKeyStroke("ctrl U"));
		popupMenuItemUndo.setMnemonic('U');
		popupMenuItemUndo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				createFile();
			}
		});
		
		popupMenuItemCut.setIcon(new ImageIcon("src\\image\\cut.gif"));
		popupMenuItemCut.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
		popupMenuItemCut.setMnemonic('X');
		popupMenuItemCut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.cut();
			}
		});
		
		popupMenuItemCopy.setIcon(new ImageIcon("src\\image\\copy.gif"));
		popupMenuItemCopy.setAccelerator(KeyStroke.getKeyStroke("ctrl C"));
		popupMenuItemCopy.setMnemonic('C');
		popupMenuItemCopy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.copy();
			}
		});
		
		popupMenuItemPaste.setIcon(new ImageIcon("src\\image\\paste.gif"));
		popupMenuItemPaste.setAccelerator(KeyStroke.getKeyStroke("ctrl V"));
		popupMenuItemPaste.setMnemonic('V');
		popupMenuItemPaste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.paste();
			}
		});
		
		popupMenuItemDel.setIcon(new ImageIcon("src\\image\\delete.gif"));
		popupMenuItemDel.setMnemonic('L');
		popupMenuItemDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.replaceSelection("");
			}
		});
		
		popupMenuItemSelectAll.setIcon(new ImageIcon("src\\image\\selectAll.gif"));
		popupMenuItemSelectAll.setMnemonic('L');
		popupMenuItemSelectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				textArea.selectAll();
			}
		});

		popupMenu.add(popupMenuItemUndo);
		popupMenu.addSeparator();
		popupMenu.add(popupMenuItemCut);
		popupMenu.add(popupMenuItemCopy);
		popupMenu.add(popupMenuItemPaste);
		popupMenu.add(popupMenuItemDel);
		popupMenu.addSeparator();
		popupMenu.add(popupMenuItemSelectAll);
		
		textArea.addMouseListener(new MouseAdapter(){
			int x,y;
			public void mousePressed(MouseEvent e){
				if(e.getButton()==3){
					x=e.getX();
					y=e.getY();
				}
			}
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==3)
					popupMenu.show(textArea, x, y);
			}
		});
		document=textArea.getDocument();
		document.addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e) {
				documentChanged(e);
			}
			public void insertUpdate(DocumentEvent e) {
				documentChanged(e);
			}
			public void removeUpdate(DocumentEvent e) {
				documentChanged(e);
			}
		});
		scrollPanel.getViewport().add(textArea);
		
		this.setVisible(true);
	}
	/**
	 * ���ı������ݱ仯
	 */
	public void documentChanged(DocumentEvent e){
		this.changed=true;
		this.textArea.getCaret().setVisible(true);
		this.menuItemSave.setEnabled(true);
		this.saveButton.setEnabled(true);
	}
	/**
	 * �½��ļ�
	 */
	public void createFile(){
		if(changed){
			int selected=JOptionPane.showConfirmDialog(StartFrame.this, "�ļ������Ѿ��ı䣬�Ƿ񱣴�?","����Ի���",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if(selected==JOptionPane.YES_OPTION){
				if(saveFile()){
					this.textArea.setText("");
					this.changed=false;
				}
			}else if(selected==JOptionPane.NO_OPTION){
				this.textArea.setText("");
				this.changed=false;
			}
		}else{
			this.textArea.setText("");
			this.changed=false;
		}
	}

	/**
	 * �����ļ�
	 */
	public boolean saveFile() {
		if(fileName==null){
			saveAsFile();
		}else{
			try {
				File file = new File (fileName);
	            FileWriter fw = new FileWriter (file);
				 fw.write (textArea.getText ());
	            fw.close ();
	            this.changed=false;
	            this.menuItemSave.setEnabled(false);
	            this.saveButton.setEnabled(false);
	            this.menuItemSaveAs.setEnabled(true);
	            return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//�������ļ�����Ϊ��
		
		return false;
	}
	
	/**
	 * ����ļ�
	 */
	public boolean saveAsFile(){
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileFilter(new MyFileFilter("txt"));
		fileChooser.setFileFilter(new MyFileFilter("sql"));
		fileChooser.setFileFilter(new MyFileFilter("java"));
		fileChooser.setFileFilter(new MyFileFilter("class"));
		fileChooser.setFileFilter(new MyFileFilter("jsp"));
		if(JFileChooser.APPROVE_OPTION==fileChooser.showSaveDialog(this)){
			File file = fileChooser.getSelectedFile();
			fileName=file.getPath();
			try{
				file=new File(fileName);
				FileWriter fw=new FileWriter(file);
				fw.write(textArea.getText());
				fw.close();
				this.changed=false;
				this.menuItemSave.setEnabled(false);
				this.saveButton.setEnabled(false);
				return true;
			}catch(IOException ioe){
				ioe.getMessage();
			}
		}
		return false;
	}
	
	/**
	 * ���ļ�
	 */
	public void openFile(){
		if(changed){
			if(JOptionPane.YES_OPTION==JOptionPane.showConfirmDialog(this, "�ı��Ѿ��ı��Ƿ񱣴�"))
				saveFile();
		}
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileFilter(new MyFileFilter("txt"));
		fileChooser.setFileFilter(new MyFileFilter("sql"));
		fileChooser.setFileFilter(new MyFileFilter("java"));
		fileChooser.setFileFilter(new MyFileFilter("class"));
		fileChooser.setFileFilter(new MyFileFilter("jsp"));
		int selected=fileChooser.showOpenDialog(this);
		if(JFileChooser.APPROVE_OPTION==selected){
			File file=fileChooser.getSelectedFile();
			this.fileName=file.getAbsolutePath();
			this.menuItemSaveAs.setEnabled(true);
			try {
				FileReader fr=new FileReader(file);
				BufferedReader br=new BufferedReader(fr);
				StringBuffer sb=new StringBuffer();
				String str="";
				while((str=br.readLine())!=null){
					sb.append(str+"\n");
				}
				this.textArea.setText(sb.toString());
				this.changed=false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * �رմ���
	 */
	public void myExit(){
		if(changed){
			int selected=JOptionPane.showConfirmDialog(this, "�ļ������Ѿ��ı䣬�Ƿ񱣴�?","����Ի���",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if(JOptionPane.YES_OPTION==selected){
				saveFile();
				System.exit(0);
			}else if(JOptionPane.NO_OPTION==selected){
				System.exit(0);
			}
		}else{
			System.exit(0);
		}
	}

	/**
	 * �õ�ϵͳ��ǰ����
	 */
	public String getCurrentTime(){
		Date date = new Date();
        SimpleDateFormat d = new SimpleDateFormat("kk:mm yyyy-MM-dd");
        String time = d.format(date);
		return time;
	}
	
}
