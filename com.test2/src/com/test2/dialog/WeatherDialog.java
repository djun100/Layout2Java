package com.test2.dialog;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JEditorPane;

public class WeatherDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JEditorPane editorpane;

	public WeatherDialog(String title, boolean model) {
		super(new Frame(), title, model);
		init();
	}

	public void init() {

		this.setSize(800, 400);
		this.setContentPane(getJEditorPane());
		try {
			// 构建URL获得天气预报dc166.htm为成都的天气预报，查询其他城市可自己研究QQ的天气预报页面
			URL url = new URL("http://weather.news.qq.com/inc/07_dc166.htm");
			String temp = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				if (inputLine.indexOf("<!--") == -1)
					temp = temp + inputLine + "/n";
			in.close();

			String weather = temp.substring(temp.indexOf("<div"), temp.lastIndexOf("</div>"));
			this.editorpane.setText(weather);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public JEditorPane getJEditorPane() {
		if (editorpane == null) {
			editorpane = new JEditorPane();
			editorpane.setContentType("text/html");
		}
		return editorpane;
	}

}
