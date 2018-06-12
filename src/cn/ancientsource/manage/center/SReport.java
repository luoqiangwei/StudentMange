package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.ancientsource.manage.database.Jdbc;
import cn.ancientsource.manage.login.Login;

// �ɼ��� -- ѧ�����
public class SReport extends JPanel {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -4932163837656902554L;
	// ��������б�
	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��

	private JLabel JL_Title;
	// �ɼ�1
	private JLabel JL_S1;
	// �ɼ�2
	private JLabel JL_S2;
	// �ɼ�3
	private JLabel JL_S3;
	// �ܳɼ�
	private JLabel JL_SC;

	private JTextField JT_S1;
	private JTextField JT_S2;
	private JTextField JT_S3;
	private JTextField JT_SC;

	public SReport() {
		GUI();
	}

	private void GUI() {
		// ���ֿ����ó�ʼ��
		JTextFieldSet();
		// �ı������ó�ʼ��
		JLabelSet();
		// �μ�������ʼ��
		PanelSet();
	}

	private void JLabelSet() {
		JL_Title = new JLabel();
		// ���ñ���
		JL_Title.setText("�� �� �� ��");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 60));
		// ����������ɫ
		JL_Title.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_Title.setBounds(75 + 350, 20, 400, 100);
		
		JL_S1 = new JLabel();
		// ���ñ���
		JL_S1.setText("�ɼ�1");
		// ��������
		JL_S1.setFont(new Font("����", Font.PLAIN, 25));
		// ����������ɫ
		JL_S1.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_S1.setBounds(75 + 350, 200, 400, 100);
		
		JL_S2 = new JLabel();
		// ���ñ���
		JL_S2.setText("�ɼ�2");
		// ��������
		JL_S2.setFont(new Font("����", Font.PLAIN, 25));
		// ����������ɫ
		JL_S2.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_S2.setBounds(75 + 350, 300, 400, 100);
		
		JL_S3 = new JLabel();
		// ���ñ���
		JL_S3.setText("�ɼ�3");
		// ��������
		JL_S3.setFont(new Font("����", Font.PLAIN, 25));
		// ����������ɫ
		JL_S3.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_S3.setBounds(75 + 350, 400, 400, 100);
		
		JL_SC = new JLabel();
		// ���ñ���
		JL_SC.setText("�ܳɼ�");
		// ��������
		JL_SC.setFont(new Font("����", Font.PLAIN, 25));
		// ����������ɫ
		JL_SC.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_SC.setBounds(75 + 350, 500, 400, 100);
	}

	private void JTextFieldSet() {
		JT_S1 = new JTextField("�ɼ�1");
		JT_S1.setBounds(170 + 450 - 10, 230, 100, 50);
		JT_S1.setFont(new Font("г��", Font.BOLD, 30));
		JT_S1.setForeground(Color.WHITE);
		JT_S1.setEditable(false);
		JT_S1.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S1.setHorizontalAlignment(JTextField.CENTER);
		JT_S1.setOpaque(false);
		
		JT_S2 = new JTextField("�ɼ�2");
		JT_S2.setBounds(170 + 450 - 10, 330, 100, 50);
		JT_S2.setFont(new Font("г��", Font.BOLD, 30));
		JT_S2.setForeground(Color.WHITE);
		JT_S2.setEditable(false);
		JT_S2.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S2.setHorizontalAlignment(JTextField.CENTER);
		JT_S2.setOpaque(false);
		
		JT_S3 = new JTextField("�ɼ�3");
		JT_S3.setBounds(170 + 450 - 10, 430, 100, 50);
		JT_S3.setFont(new Font("г��", Font.BOLD, 30));
		JT_S3.setForeground(Color.WHITE);
		JT_S3.setEditable(false);
		JT_S3.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_S3.setHorizontalAlignment(JTextField.CENTER);
		JT_S3.setOpaque(false);
		
		JT_SC = new JTextField("�ܳɼ�");
		JT_SC.setBounds(170 + 450 - 10, 530, 100, 50);
		JT_SC.setFont(new Font("г��", Font.BOLD, 30));
		JT_SC.setForeground(Color.WHITE);
		JT_SC.setEditable(false);
		JT_SC.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SC.setHorizontalAlignment(JTextField.CENTER);
		JT_SC.setOpaque(false);
		
	}

	private void PanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\SS.png");
		img = icon.getImage();

		// ���ô�С
		this.setBounds(0, 0, 1300, 1000);
		// ���ñ���͸��
		this.setBackground(null);
		// ���ÿؼ�͸��
		this.setOpaque(false);
		// ��ʹ���κβ���
		this.setLayout(null);

		// �������
		this.add(JL_Title);
		this.add(JL_S1);
		this.add(JL_S2);
		this.add(JL_S3);
		this.add(JL_SC);
		
		this.add(JT_S1);
		this.add(JT_S2);
		this.add(JT_S3);
		this.add(JT_SC);
	}

	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, 1300, 1000);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	public void initValue() {
		JT_S1.setText("�ɼ�1");
		JT_S2.setText("�ɼ�2");
		JT_S3.setText("�ɼ�3");
		JT_SC.setText("�ܳɼ�");
	}
	
	public void getInfo(){
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ���ɼ�` WHERE `ѧ��`=" + Login.SQL_Account);
			if(rs.next()){
				JT_S1.setText(rs.getString(3));
				JT_S2.setText(rs.getString(4));
				JT_S3.setText(rs.getString(5));
				JT_SC.setText(rs.getString(6));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
