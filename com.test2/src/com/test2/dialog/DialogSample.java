package com.test2.dialog;

import org.eclipse.jface.dialogs.Dialog; //注意这个是jface的Dialog,不是swt的Dialog
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogSample extends Dialog {

	/**
	* 构造函数
	* 注意:其范围由protected改为public,否则包外的类无法调用
	*/
	public DialogSample(Shell parentShell) {
		super(parentShell);
	}

	/**
	* 在这个方法里构建Dialog中的界面内容
	*/
	protected Control createDialogArea(Composite parent) {
		getShell().setText("标题"); //设置Dialog的标头
		Text text = new Text(parent, SWT.BORDER); //设置一个Text控件
		text.setText("我爱JAVA"); //设置text中的内容
		return parent;
	}

	/**
	* 重载这个方法可以改变窗口的默认式样
	* SWT.RESIZE：窗口可以拖动边框改变大小
	* SWT.MAX：　窗口可以最大化
	*/
	protected int getShellStyle() {
		return super.getShellStyle() | SWT.RESIZE | SWT.MAX;
	}

}

