package cn.ancientsource.manage.center;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;

public class BaseS extends JPanel {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = 772586092247399675L;
	private ImageIcon icon;
	private Image img;

	private JList<String> list;
	private JPanel basebase;
	
	protected static SInfo sInfo = new SInfo();
	protected static SReport sReport = new SReport();

	public BaseS() {
		GUI();
	}

	private void GUI() {
		// �˵���ʼ��
		JListSet();
		// ������ʼ��
		JPanelSet();
	}

	private void JListSet() {
		String[] o = new String[] { "������Ϣ", "�ɼ���"};
		list = new JList<String>(o);
		list.setBounds(10, 10, 180, 9980);
		list.setBackground(null);
		list.setOpaque(false);
		list.setFont(new Font("г��", Font.BOLD, 20));
		list.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(list.getAnchorSelectionIndex()){
				case 0:
					Center.baseS.basebase.removeAll();
					sInfo.getInfo();
					Center.baseS.basebase.add(sInfo);
					Center.baseS.repaint();
					sInfo.repaint();
					break;
				case 1:
					Center.baseS.basebase.removeAll();
					sReport.getInfo();
					Center.baseS.basebase.add(sReport);
					Center.baseS.repaint();
					sReport.repaint();
					break;
				}
			}
		});
	}

	
	private void JPanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\13.png");
		img = icon.getImage();
		// ���ñ���͸��
		this.setBackground(null);
		// ���ÿؼ�͸��
		this.setOpaque(false);
		// ��ʹ���κβ���
		this.setLayout(null);
		// �������
		this.add(list);
		
		// �������
		basebase = new JPanel();
		basebase.setBackground(null);
		basebase.setOpaque(false);
		basebase.setBounds(200, 0, 1300, 1000);
		this.add(basebase);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	public void initValue() {
		sReport.initValue();
		sInfo.initValue();
	}
}
