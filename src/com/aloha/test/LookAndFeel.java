package com.aloha.test;

/**
 * ¿Ø¼þ¸Ð¹Û
 * @author chenpo
 * @since 2008-2-15
 *
 */
public class LookAndFeel {

	private final static String metalName="Metal";
	private final static String metalPlaf="javax.swing.plaf.metal.MetalLookAndFeel";
	private final static String metifName="Metif";
	private final static String metifPlaf="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
	private final static String windowsName="Windows";
	private final static String windowsPlaf="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public static String getMetalName() {
		return metalName;
	}
	public static String getMetalPlaf() {
		return metalPlaf;
	}
	public static String getMetifName() {
		return metifName;
	}
	public static String getMetifPlaf() {
		return metifPlaf;
	}
	public static String getWindowsName() {
		return windowsName;
	}
	public static String getWindowsPlaf() {
		return windowsPlaf;
	}
}
