package cn.ancientsource.manage.register;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import cn.ancientsource.manage.database.Jdbc;

public class RegisterWrite extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -2547626758598568134L;
	// ѧ���ĺ�����������
	public static RegisterWriteS registerWriteS = new RegisterWriteS();
	// ��ʦ�ĺ�����������
	public static RegisterWriteT registerWriteT = new RegisterWriteT();

	private Toolkit tool;
	private Image Ico;

	public RegisterWrite() {
		// ���������ô���
		this.setTitle("ѧ������ϵͳ�������");
		/*
		 * // ����������ʾ this.setContentPane(base);
		 */
		// ���ô����С
		this.setSize(500, 1000);
		// ����λ��
		this.setLocation(800, 50);
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
		// �����Ƿ�ɼ�
		this.setVisible(false);
		// Ĭ�Ϲرճ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// �����¼�
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Jdbc.closeSql(); // �رճ���ǰ�ȹر����ݿ�
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
	}
	
	//����ת����ʦ���
	public void switchPanelT(){
		this.remove(registerWriteS);
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.add(registerWriteT);
		this.repaint();
		registerWriteT.repaint();
	}
	
	// ת��ѧ�����
	public void switchPanelS(){
		this.remove(registerWriteT);
		this.setSize(540, 800);
		this.setLocationRelativeTo(null);
		this.add(registerWriteS);
		this.repaint();
		registerWriteS.repaint();
	}

	// �����жϵ����ĸ�ʹ��������ʼ����Ӧ��ֵ
	public void setDeualt(String a) {
		if(a.length() == 4){
			registerWriteT.setDeualt(a);
		}
		if(a.length() == 8){
			registerWriteS.setDeualt(a);
		}
	}

}
