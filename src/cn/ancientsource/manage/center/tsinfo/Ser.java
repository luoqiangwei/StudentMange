package cn.ancientsource.manage.center.tsinfo;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import cn.ancientsource.manage.center.BaseT;
import cn.ancientsource.manage.center.TSInfo;

public class Ser extends JFrame {

	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -4665571384067887048L;
	private JTextField a;

	public Ser() {
		a = new JTextField("������Ҫ���ҵ���Ϣ");
		a.setFont(new Font("г��", Font.BOLD, 25));
		a.setSize(250, 35);
		a.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) { // �۽���
				if ("������Ҫ���ҵ���Ϣ".equalsIgnoreCase(a.getText())) {
					a.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) { // �޾ۼ�
				if ("".equals(a.getText().trim())) {
					a.setText("������Ҫ���ҵ���Ϣ");
				}
			}

		});
		a.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!a.getText().equals("") && !a.getText().equals("������Ҫ���ҵ���Ϣ")){
						TSInfo.account = a.getText();
						Ser.this.setVisible(false);
						BaseT.tSInfo.search();
					}
				}
			}
		});

		this.setSize(250, 85);
		this.setLocationRelativeTo(null);
		// �õ�һ�� Toolkit ����
		Toolkit tool = this.getToolkit();
		// ͨ�� tool ��ȡͼ��
		Image Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// ����ͼ��
		this.setIconImage(Ico);
		// �����Ƿ�ɼ�
		this.setVisible(true);
		// ���õ���رհ�ť��Ĭ�϶���
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setResizable(false);
		this.add(a);
	}
}
