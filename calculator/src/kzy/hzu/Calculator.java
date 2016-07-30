package kzy.hzu;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;

/**
 * 计算器类
 * @author kzy
 * @version 1.0
 * @date 7/29/2016
 *
 */
public class Calculator extends Frame
{
	private JTextField mTextField ;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		//新建一个计算器类对象并调用launchFrame函数
		new Calculator().launchFrame();
	}
	
	
	/**
	 *@return void 
	 */
	public void launchFrame()
	{		
		Panel _Panel = new Panel();
		mTextField = new JTextField(20);
		Button[] _Button=new Button[16];
		int []a = {7,8,9,10,4,5,6,11,1,2,3,12,0,14,15,13};
		MyActionListener _MyActionListener = new MyActionListener();
		//定义16个按钮
		for(int i=0;i<16;i++)
		{
			//_Button[i] = new Button(""+i);
			_Button[i] = new Button(String.valueOf(i));
			_Button[i].addActionListener(_MyActionListener);
		}
		_Button[10].setLabel("+");
		_Button[11].setLabel("-");
		_Button[12].setLabel("x");
		_Button[13].setLabel("/");
		_Button[14].setLabel(".");
		_Button[15].setLabel("=");

		//设置框架边界布局管理器
		setLayout(new BorderLayout(4,4));
		
		//设置面板网格布局管理器
		_Panel.setLayout(new GridLayout(4,4));
		
		//文本框右对齐
		mTextField.setHorizontalAlignment(JTextField.RIGHT);
		for(int i = 0 ; i < 16 ; i ++)
		{
			_Panel.add(_Button[a[i]]);
		}
		add(mTextField,BorderLayout.NORTH);
		
		add(_Panel,BorderLayout.CENTER);
		
		//添加窗口监听器
		this.addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		
		pack();
		setSize(200,200);
		setVisible(true);
	}

	private class MyActionListener implements ActionListener
	{
		String mString1 = "";
		String mString2 = "";
		String mString3 = "";
		@Override
		public void actionPerformed(ActionEvent e) {
			
			//判断是否是字符
			if(e.getActionCommand().equals("+") || e.getActionCommand().equals("-") ||
					e.getActionCommand().equals("x") ||e.getActionCommand().equals("/") ||
					e.getActionCommand().equals("="))
			{
				//判断是否是等号 是的话就进行计算
				if (e.getActionCommand().equals("=")) 
				{
					if(mString3.equals("+"))
						mString1= "" + (Double.parseDouble(mString2) + Double.parseDouble(mString1));
					else if(mString3.equals("-"))
						mString1= "" + (Double.parseDouble(mString2) - Double.parseDouble(mString1));
					else if(mString3.equals("x"))
						mString1= "" + (Double.parseDouble(mString2) * Double.parseDouble(mString1));
					else if(mString3.equals("/"))
						mString1= "" + (Double.parseDouble(mString2) / Double.parseDouble(mString1));
					mTextField.setText(mString1);
				}
				
				else
				{
					//把运算符保留在mString3中 
					mString3 = e.getActionCommand();
					//把数值字符串保留在mString1中
					mString2 = mString1;
					mString1 = "";
				}
				
			}
			else
			{
				mString1 += e.getActionCommand();
				mTextField.setText(mString1);
			}
		}	
	}
}
