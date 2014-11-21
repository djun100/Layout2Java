package com.test2.popup.actions;

import java.awt.Dialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.jar.Manifest;

import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.test2.dialog.DialogSample;
import com.test2.dialog.WeatherDialog;
import com.test2.xmlHandler.ManifestHandler;
import com.test2.xmlHandler.XMLHandler;

public class TestAction implements IObjectActionDelegate {

	private Shell shell;
	private IStructuredSelection selection;
	
	/**
	 * Constructor for Action1.
	 */
	public TestAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		
		DialogSample wd = new DialogSample(null);
		wd.open();
//		wd.setLocation(200,100);
//		wd.setVisible(true);
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=(IStructuredSelection)selection;
	}

}
