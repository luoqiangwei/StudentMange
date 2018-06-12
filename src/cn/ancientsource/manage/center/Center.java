package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import cn.ancientsource.manage.database.Jdbc;

public class Center extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = 3089294126902933658L;
	public static BaseS baseS = new BaseS();
	public static BaseT baseT = new BaseT();

	// ����б�
	private Image Ico;

	private Toolkit tool;

	public Center() {
		GUI();
	}

	public void GUI() {
		// ����������ʼ��
		FrameSet();
	}

	private void FrameSet() {
		// ���������ô���
		this.setTitle("ѧ������ϵͳ");
		// ����������ʾ
		//this.setContentPane(baseT);
		// ���ô����С
		this.setSize(1500, 1000);
		// ����λ��
		this.setLocation(200, 40);
		// ���õ���رհ�ť��Ĭ�϶���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��������
		this.setResizable(false);
		// ���ñ���ɫ����Ȼûɶ�ã�
		this.setBackground(Color.WHITE);
		// �õ�һ�� Toolkit ����
		tool = this.getToolkit();
		// ͨ�� tool ��ȡͼ��
		Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// ����ͼ��
		this.setIconImage(Ico);
		// ���þ���
		this.setLocationRelativeTo(null);
		// �����Ƿ�ɼ�
		this.setVisible(false);
		
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				Jdbc.closeSql(); // �رճ���ǰ�ȹر����ݿ�
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}
	
	public void switchS(){
		baseT.initValue();
		this.setContentPane(baseS);
	}
	
	public void switchT(){
		baseS.initValue();
		this.setContentPane(baseT);
	}
}
