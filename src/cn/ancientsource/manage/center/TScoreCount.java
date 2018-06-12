package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import cn.ancientsource.manage.database.Jdbc;

// ������Ϣ -- ��ʦ���
public class TScoreCount extends JPanel{
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -8191693428130074545L;
	private String[] title = { "ѧ��", "����", "�ɼ�1", "�ɼ�2", "�ɼ�3", "�ܷ�" };
	private String[][] info;

	public static String account;

	private int count;
	private int s1;
	private int s2;
	private int s3;
	private int sc;
	private DefaultTableModel model;
	private JTableHeader JTH;
	private JTable JT;

	// ��������б�
	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��

	private JScrollPane JSP;

	private JButton JB_add;
	private JButton JB_ser;
	private JButton JB_Dser;
	private JButton JB_RE;

	public TScoreCount() {
		GUI();
	}

	private void GUI() {
		JButtonSet();

		JScrollPaneSet();
		// ����ʼ��
		JTableSet();
		// �μ�������ʼ��
		PanelSet();
	}

	private void JButtonSet() {
		JB_add = new JButton("�߷�����");
		// ����λ�ô�С
		JB_add.setBounds(5 + 200, 910, 200, 50);
		// ��������
		JB_add.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_add.setOpaque(true);
		// ���ñ���
		JB_add.setBackground(new Color(0, 103, 146));
		// �����Ƿ���Ʊ߿�
		JB_add.setBorderPainted(true);
		// �����ƽ���
		JB_add.setFocusPainted(false);
		// ����������ɫ
		JB_add.setForeground(Color.WHITE);
		
		JB_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				up();
			}
		});

		JB_ser = new JButton("�ͷ�����");
		// ����λ�ô�С
		JB_ser.setBounds(255 + 200, 910, 200, 50);
		// ��������
		JB_ser.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_ser.setOpaque(true);
		// ���ñ���
		JB_ser.setBackground(new Color(0, 88, 126));
		// �����Ƿ���Ʊ߿�
		JB_ser.setBorderPainted(true);
		// �����ƽ���
		JB_ser.setFocusPainted(false);
		// ����������ɫ
		JB_ser.setForeground(Color.WHITE);

		JB_ser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				down();
			}
		});

		//JB_Dser = new JButton("Ĭ������");
		JB_Dser = new JButton("ƽ����");
		// ����λ�ô�С
		JB_Dser.setBounds(755 + 200, 910, 200, 50);
		// ��������
		JB_Dser.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_Dser.setOpaque(true);
		// ���ñ���
		JB_Dser.setBackground(new Color(3, 101, 140));
		// �����Ƿ���Ʊ߿�
		JB_Dser.setBorderPainted(true);
		// �����ƽ���
		JB_Dser.setFocusPainted(false);
		// ����������ɫ
		JB_Dser.setForeground(Color.WHITE);

		JB_Dser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//reWrite();
				JOptionPane.showMessageDialog(null, "�ɼ�1 ��" + s1 + "\n�ɼ�2 ��" + s2 + "\n�ɼ�3 ��" + s3 + "\n�ܷ� ��" + sc, "ƽ����", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		JB_RE = new JButton("Ĭ������");
		// ����λ�ô�С 
		JB_RE.setBounds(505 + 200, 910, 200, 50);
		// ��������
		JB_RE.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_RE.setOpaque(true);
		// ���ñ���
		JB_RE.setBackground(new Color(20, 101, 140));
		// �����Ƿ���Ʊ߿�
		JB_RE.setBorderPainted(true);
		// �����ƽ���
		JB_RE.setFocusPainted(false);
		// ����������ɫ
		JB_RE.setForeground(Color.WHITE);

		JB_RE.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reWrite();
			}
		});
	}

	private void JScrollPaneSet() {
		JSP = new JScrollPane();
		JSP.setBounds(5, 5, 1280, 900);
		JSP.setOpaque(false);
		JSP.setBackground(null);
		JSP.getViewport().setOpaque(false);
		JSP.getViewport().setBorder(null);
	}

	private void JTableSet() {
		getInfo();
		model = new DefaultTableModel();
		model.setDataVector(info, title);
		JT = new JTable(model);
		JT.setOpaque(false);
		JT.setFont(new Font("��Բ", Font.PLAIN, 20));
		// JT.setBackground(null);
		JT.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JT.setColumnSelectionAllowed(false);
		JT.setRowSelectionAllowed(true);

		JTH = JT.getTableHeader();
		JTH.setFont(new Font("��Բ", Font.PLAIN, 20));
		JSP.getViewport().add(JT);
		JT.setRowHeight(25);
	}

	@Override
	public void paint(Graphics g) {
		this.setBounds(0, 0, 1300, 1000);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		super.paint(g);
	}

	private void PanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Main_4.png");
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
		this.add(JSP);
		this.add(JB_add);
		this.add(JB_Dser);
		this.add(JB_ser);
		this.add(JB_RE);
	}

	public void getInfo() {
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ���ɼ�`");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			if(count != 0){
				s1 /= count;
				s2 /= count;
				s3 /= count;
				sc /= count;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void reWrite(){
		getInfo();
		model.setDataVector(info, title);
	}
	
	public void up(){
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ���ɼ�` order by `�ܷ�` desc");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			s1 /= count;
			s2 /= count;
			s3 /= count;
			sc /= count;
			model.setDataVector(info, title);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void down(){
		try {
			s1 = 0;
			s2 = 0;
			s3 = 0;
			sc = 0;
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ���ɼ�` order by `�ܷ�` asc");
			rs.last();
			count = rs.getRow();
			info = new String[rs.getRow()][6];
			rs.beforeFirst();
			for (int i = 0; i < count; i++) {
				if (rs.next()) {
					s1 += Integer.valueOf(rs.getString(3));
					s2 += Integer.valueOf(rs.getString(4));
					s3 += Integer.valueOf(rs.getString(5));
					sc += Integer.valueOf(rs.getString(6));
					for (int j = 0; j < 6; j++) {
						info[i][j] = rs.getString(j + 1);
					}
				}
			}
			s1 /= count;
			s2 /= count;
			s3 /= count;
			sc /= count;
			model.setDataVector(info, title);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
