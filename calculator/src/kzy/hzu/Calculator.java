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
 * ��������
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
		//�½�һ������������󲢵���launchFrame����
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
		//����16����ť
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

		//���ÿ�ܱ߽粼�ֹ�����
		setLayout(new BorderLayout(4,4));
		
		//����������񲼾ֹ�����
		_Panel.setLayout(new GridLayout(4,4));
		
		//�ı����Ҷ���
		mTextField.setHorizontalAlignment(JTextField.RIGHT);
		for(int i = 0 ; i < 16 ; i ++)
		{
			_Panel.add(_Button[a[i]]);
		}
		add(mTextField,BorderLayout.NORTH);
		
		add(_Panel,BorderLayout.CENTER);
		
		//��Ӵ��ڼ�����
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
			
			//�ж��Ƿ����ַ�
			if(e.getActionCommand().equals("+") || e.getActionCommand().equals("-") ||
					e.getActionCommand().equals("x") ||e.getActionCommand().equals("/") ||
					e.getActionCommand().equals("="))
			{
				//�ж��Ƿ��ǵȺ� �ǵĻ��ͽ��м���
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
					//�������������mString3�� 
					mString3 = e.getActionCommand();
					//����ֵ�ַ���������mString1��
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
