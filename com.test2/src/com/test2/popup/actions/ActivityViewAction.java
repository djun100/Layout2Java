package com.test2.popup.actions;

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

import com.test2.xmlHandler.ManifestHandler;
import com.test2.xmlHandler.XMLHandler;

public class ActivityViewAction implements IObjectActionDelegate {

	private Shell shell;
	private IStructuredSelection selection;
	
	/**
	 * Constructor for Action1.
	 */
	public ActivityViewAction() {
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
		
		Object o=selection.getFirstElement();
		IFile f1=(IFile)o;
		IProject project=f1.getProject();
		String path = f1.getLocation().makeAbsolute().toFile().getAbsolutePath();
		
//		String p=path.substring(0, path.indexOf("\\res\\")+1)+"src";
		String str1=path.substring(path.lastIndexOf("\\")+1);
		String fileName=str1.substring(0,str1.indexOf("."));
		
		String projectPath=project.getLocation().makeAbsolute().toFile().getAbsolutePath();
		
		String srcPath=projectPath+"\\src";
		
//		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance() ;
			XMLReader xmlReader = factory.newSAXParser().getXMLReader();
			// 获取AndroidManifest.xml 解析R文件路径
			File manifestfile=new File(projectPath+"\\AndroidManifest.xml");
			String packageName=null;
			if(manifestfile.exists()){
				ManifestHandler manifestHandler=new ManifestHandler();
				xmlReader.setContentHandler(manifestHandler);
				InputSource is1 = new InputSource(new FileInputStream(projectPath+"\\AndroidManifest.xml"));
				is1.setEncoding("utf-8");
				xmlReader.parse(is1) ;
				packageName=manifestHandler.packageName;
				
			}
			
			//解析选中xml
			XMLHandler handler=new XMLHandler();
			xmlReader.setContentHandler(handler) ;
			InputSource is = new InputSource(new FileInputStream(path));
			is.setEncoding("utf-8");
			xmlReader.parse(is) ;
			
			
			//创建文件
			File f=new File(srcPath,"TestView.java");
			if(f.exists()){
				f.delete();
			}
			f.createNewFile();
			
			
			PrintWriter writer=new PrintWriter(f);
			writer.write(handler.getJavaForMB(packageName,fileName,true,"mb2.txt"));
			writer.flush();
			writer.close();
			project.refreshLocal(IResource.DEPTH_INFINITE, null); 			
			
//			MessageDialog.openInformation(
//					shell,
//					"Test11",
//					"生成成功 文件路径:"+projectPath);
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.openInformation(
					shell,
					"Test11",
					"生成出错 文件路径:"+e.getMessage());
		}  

		
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		this.selection=(IStructuredSelection)selection;
	}

}
