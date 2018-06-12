package cn.ancientsource.manage.center;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JPanel;

public class BaseT extends JPanel {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -5371819626722494457L;
	private ImageIcon icon;
	private Image img;

	private JList<String> list;
	private JPanel basebase;
	
	protected static TInfo tInfo = new TInfo();
	public static TScore tScore = new TScore();
	public static TScoreCount tScoreCount = new TScoreCount();
	public static TSInfo tSInfo = new TSInfo();

	public BaseT() {
		GUI();
	}

	private void GUI() {
		// �˵���ʼ��
		JListSet();
		// ������ʼ��
		JPanelSet();
	}

	private void JListSet() {
		String[] o = new String[] { "ѧ����Ϣ", "ѧ���ɼ�", "�ɼ�ͳ��", "������Ϣ" };
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
					Center.baseT.basebase.removeAll();
					Center.baseT.basebase.add(tSInfo);
					Center.baseT.repaint();
					tSInfo.repaint();
					break;
				case 1:
					Center.baseT.basebase.removeAll();
					Center.baseT.basebase.add(tScore);
					Center.baseT.repaint();
					tScore.repaint();
					break;
				case 2:
					Center.baseT.basebase.removeAll();
					Center.baseT.basebase.add(tScoreCount);
					Center.baseT.repaint();
					tScoreCount.repaint();
					break;
				case 3:
					Center.baseT.basebase.removeAll();
					tInfo.getInfo();
					Center.baseT.basebase.add(tInfo);
					Center.baseT.repaint();
					tInfo.repaint();
					break;
				}
			}
		});
	}

	private void JPanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\13.jpg");
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
		
	}
}
