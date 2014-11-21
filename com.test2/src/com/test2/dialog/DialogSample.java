package com.test2.dialog;

import org.eclipse.jface.dialogs.Dialog; //ע�������jface��Dialog,����swt��Dialog
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class DialogSample extends Dialog {

	/**
	* ���캯��
	* ע��:�䷶Χ��protected��Ϊpublic,�����������޷�����
	*/
	public DialogSample(Shell parentShell) {
		super(parentShell);
	}

	/**
	* ����������ﹹ��Dialog�еĽ�������
	*/
	protected Control createDialogArea(Composite parent) {
		getShell().setText("����"); //����Dialog�ı�ͷ
		Text text = new Text(parent, SWT.BORDER); //����һ��Text�ؼ�
		text.setText("�Ұ�JAVA"); //����text�е�����
		return parent;
	}

	/**
	* ��������������Ըı䴰�ڵ�Ĭ��ʽ��
	* SWT.RESIZE�����ڿ����϶��߿�ı��С
	* SWT.MAX�������ڿ������
	*/
	protected int getShellStyle() {
		return super.getShellStyle() | SWT.RESIZE | SWT.MAX;
	}

}

