package com.aloha.test;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * @author chenpopo
 * @since2008-2-15
 *
 */
public class MyFileFilter extends FileFilter {
	private final String java="java";
	private final String txt="txt";
	private final String sql="sql";
	private final String classes="class";
	private final String jsp="jsp";
	
	//文件类型
	String name;
	
	public MyFileFilter(String fileType){
		this.name=fileType;
	}
	public boolean accept(File f) {
		
		return f.getAbsolutePath().toLowerCase().endsWith(name) || f.isDirectory();
	}

	public String getDescription() {
		if(name.equals(java))
			return "java文件(.java)";
		else if(name.equals(txt))
			return "txt文件(.txt)";
		else if(name.equals(sql))
			return "sql文件(.sql)";
		else if(name.equals(classes))
			return "class文件(.class)";
		else if(name.equals(jsp))
			return "jsp文件(.jsp)";
		else
			return "所有文件(*.*)";
	}

}
