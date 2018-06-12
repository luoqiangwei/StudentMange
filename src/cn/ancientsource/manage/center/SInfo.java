package cn.ancientsource.manage.center;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.ancientsource.manage.database.Jdbc;
import cn.ancientsource.manage.login.Login;

// ������Ϣ -- ѧ�����
public class SInfo extends JPanel {

	/**
	 * @author QiangweiLuo
	 */
	private static final long serialVersionUID = -8261008319869477656L;

	private String[][] Speciality;

	// ��������б�
	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��

	private JLabel JL_Title;
	// ѧ��
	private JLabel JL_SID;
	// ����
	private JLabel JL_PWD;
	// ����
	private JLabel JL_Name;
	// �Ա�
	private JLabel JL_Sex;
	// Ժϵ
	private JLabel JL_Faculty;
	// רҵ
	private JLabel JL_Speciality;

	private JTextField JT_SID;
	private JTextField JT_PWD;
	private JTextField JT_Name;

	private JComboBox<String> JCB_Sex;
	private JComboBox<String> JCB_Faculty;
	private JComboBox<String> JCB_Speciality;

	private JButton JB_Enter;
	private JButton JB_Cube;

	public SInfo() {
		GUI();
	}

	private void GUI() {
		// ��ť���ó�ʼ��
		JButtonSet();
		// ���ֿ����ó�ʼ��
		JTextFieldSet();
		// ��Ͽ�����
		JComboBoxSet();
		// �ı������ó�ʼ��
		JLabelSet();
		// �μ�������ʼ��
		PanelSet();
	}

	private void JComboBoxSet() {
		String[] Sex = new String[] { "Ů", "��" };
		JCB_Sex = new JComboBox<String>(Sex);
		JCB_Sex.setFont(new Font("г��", Font.BOLD, 25));
		JCB_Sex.setEditable(false);
		JCB_Sex.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Sex.setBounds(170 + 450, 410, 280, 35);
		JCB_Sex.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});

		String[] Faculty = new String[] { "ũѧԺ", "԰��������ѧԺ", "�����ѧ����ѧԺ", "��ѧԺ", "���ѧԺ", "���������Ϣ����ѧԺ", "���ù���ѧԺ", "��ѧԺ",
				"�����빫������ѧԺ", "������Դ�뻷��ѧԺ", "���岿", "�����ѧ�빤��ѧԺ", "�����ѧԺ", "ʳƷ��ѧ�빤��ѧԺ", "ְҵʦ��(����)ѧԺ", "ͼ���", "���˼����ѧԺ",
				"��ѧԺ", "��������ѧԺ", "��Դ��У" };
		JCB_Faculty = new JComboBox<String>(Faculty);
		JCB_Faculty.setFont(new Font("г��", Font.BOLD, 25));
		JCB_Faculty.setEditable(false);
		JCB_Faculty.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Faculty.setBounds(170 + 450, 480, 280, 35);
		JCB_Faculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ѡ���ѧԺ����רҵ
				JCB_Speciality.removeAllItems();
				if (JCB_Faculty.getSelectedIndex() > -1)
					for (int i = 0; i < Speciality[JCB_Faculty.getSelectedIndex()].length; i++) {
						JCB_Speciality.addItem(Speciality[JCB_Faculty.getSelectedIndex()][i]);
					}
			}
		});
		JCB_Faculty.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});

		Speciality = new String[20][];
		Speciality[0] = new String[] { "ũѧ", "԰��", "ֲ�ﱣ��", "ũ������չ", "��ѧ", "��ֲ�����", "���ӿ�ѧ�빤��", "��ѧ", "Ωһũѧ" };
		Speciality[1] = new String[] { "��ѧ", "԰��", "���й滮", "�������", "��ҩ��Դ����", "�ֲ�����", "����滮", "�羰԰��", "�������", "�Ӿ��������",
				"��ѧʵ���" };
		Speciality[2] = new String[] { "�����ѧ", "����ҽѧ", "ˮ����ֳѧ", "����ҩѧ" };
		Speciality[3] = new String[] { "������Ϣ����", "��е�������Զ���", "ģ�����������", "�������乤��", "���������ά�޼���", "ũҵ��е�������Զ���",
				"��е������켰�Զ���", "��ͨ����", "ũҵ������������Դ����", "��ľ����", "������Ϣ����", "���̹���", "��������" };
		Speciality[4] = new String[] { "�������", "����������" };
		Speciality[5] = new String[] { "�������ѧ�뼼��", "��Ϣ��������Ϣϵͳ", "��������", "���繤��", "�������ѧ�뼼��" };
		Speciality[6] = new String[] { "���̹���˫ѧλ", "���ʾ�����ó��˫ѧλ", "���ѧ˫ѧλ", "����ѧ˫ѧλ", "����ѧ˫ѧλ", "�������˫ѧλ", "�г�Ӫ��˫ѧλ",
				"ũ�־��ù���", "����ѧ", "���ʾ�����ó��", "���̹���", "����ѧ", "�Ͷ�����ᱣ��", "�������", "�г�Ӫ��" };
		Speciality[7] = new String[] { "Ӧ�û�ѧ", "��Ϣ������ѧ" };
		Speciality[8] = new String[] { "������ҵ����", "����ѧ", "����ѧ", "��������ѧ", "��ѧ", "��ѧ˫ѧλ", "�����ѧ", "����ѧ", "���ֱ���" };
		Speciality[9] = new String[] { "ũҵˮ������", "ũҵ��Դ�뻷��", "������Դ����", "������Ϣ��ѧ", "������Ϣϵͳ", "���ι���", "��������", "������ѧ" };
		Speciality[10] = new String[] { "��" };
		Speciality[11] = new String[] { "��ҩ����", "���﹤��", "���＼��", "�����ѧ" };
		Speciality[12] = new String[] { "����Ӣ��", "����", "Ӣ��", "Ӣ��˫ѧλ" };
		Speciality[13] = new String[] { "�ữ����", "ʳƷ��ѧ�빤��", "ʳƷ�����밲ȫ" };
		Speciality[14] = new String[] { "��Ӣ������", "��Ƶ��㻯", "ũ�ս���", "����Ӫ�������Ͽ�ѧ", "���ӻ��������", "����Ӣ��", "԰�ּ���", "Ӧ�û�������",
				"Ӧ�õ��Ӽ���", "��������", "��������ѧ", "����ý������", "���ؽ���", "������Ƶ����", "���ι���", "�ִ�����԰���̻�", "�����������", "������������",
				"����ϵͳ����", "Ӫ����߻�", "�����ͼ��ͼ������", "�����Ӧ�ü���", "�������ѧ�뼼��", "�����붯��Ӫ��" };
		Speciality[15] = new String[] { "��" };
		Speciality[16] = new String[] { "��" };
		Speciality[17] = new String[] { "��" };
		Speciality[18] = new String[] { "��" };
		Speciality[19] = new String[] { "��" };

		JCB_Speciality = new JComboBox<String>(Speciality[0]);
		JCB_Speciality.setFont(new Font("г��", Font.BOLD, 25));
		JCB_Speciality.setEditable(false);
		JCB_Speciality.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Speciality.setBounds(170 + 450, 550, 280, 35);
		JCB_Speciality.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});
	}

	private void JTextFieldSet() {
		JT_SID = new JTextField("ѧ��");
		JT_SID.setBounds(170 + 450, 195, 280, 35);
		JT_SID.setFont(new Font("г��", Font.BOLD, 25));
		JT_SID.setEditable(false);
		JT_SID.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SID.setHorizontalAlignment(JTextField.CENTER);
		JT_SID.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});

		JT_PWD = new JTextField("����");
		JT_PWD.setBounds(170 + 450, 265, 280, 35);
		JT_PWD.setFont(new Font("г��", Font.BOLD, 25));
		JT_PWD.setEditable(false);
		JT_PWD.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_PWD.setHorizontalAlignment(JTextField.CENTER);
		JT_PWD.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});

		JT_Name = new JTextField("����������");
		JT_Name.setBounds(170 + 450, 335, 280, 35);
		JT_Name.setFont(new Font("г��", Font.BOLD, 25));
		JT_Name.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_Name.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});
		JT_Name.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_Name.getText())) {
					JT_Name.setText("����������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("����������".equals(JT_Name.getText())) {
					JT_Name.setText("");
				}
			}
		});
	}

	private void JButtonSet() {
		JB_Cube = new JButton("��");
		JB_Cube.setBounds(170 + 450 + 280 + 10, 265, 35, 35);
		// ������ȫ��ʾ
		JB_Cube.setMargin(new java.awt.Insets(0, 0, 0, 0));
		JB_Cube.setFont(new Font("����", Font.CENTER_BASELINE, 20));
		JB_Cube.setBackground(new Color(115, 140, 138));
		JB_Cube.setBorderPainted(true);
		JB_Cube.setFocusPainted(false);
		JB_Cube.setForeground(Color.WHITE);
		JB_Cube.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				new PwdCh(Login.SQL_Account);
			}
		});

		JB_Enter = new JButton("�޸�");
		JB_Enter.setBounds(160 + 450, 620, 200, 100);
		JB_Enter.setFont(new Font("����", Font.CENTER_BASELINE, 50));
		JB_Enter.setBackground(new Color(115, 140, 138));
		JB_Enter.setBorderPainted(true);
		JB_Enter.setFocusPainted(false);
		JB_Enter.setForeground(Color.WHITE);
		JB_Enter.addKeyListener(new KeyListener() {

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					upDate();
				}
			}
		});
		JB_Enter.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				upDate();
			}
		});
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
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Main.png");
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
		this.add(JL_SID);
		this.add(JL_PWD);
		this.add(JL_Name);
		this.add(JL_Sex);
		this.add(JL_Faculty);
		this.add(JL_Speciality);

		this.add(JT_SID);
		this.add(JT_PWD);
		this.add(JT_Name);

		this.add(JCB_Sex);
		this.add(JCB_Faculty);
		this.add(JCB_Speciality);

		this.add(JB_Enter);
		this.add(JB_Cube);
	}

	private void JLabelSet() {
		// ��ʼ����������ʾ����
		JL_Title = new JLabel();
		// ���ñ���
		JL_Title.setText("������ϸ��Ϣ");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 60));
		// ����������ɫ
		JL_Title.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_Title.setBounds(75 + 450, 20, 400, 100);

		// ��ʼ����������ʾ����
		JL_SID = new JLabel();
		// ���ñ���
		JL_SID.setText("�˻�");
		// ��������
		JL_SID.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_SID.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_SID.setBounds(50 + 450, 200, 50, 25);

		// ��ʼ����������ʾ����
		JL_PWD = new JLabel();
		// ���ñ���
		JL_PWD.setText("����");
		// ��������
		JL_PWD.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_PWD.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_PWD.setBounds(50 + 450, 270, 50, 25);

		// ��ʼ����������ʾ����
		JL_Name = new JLabel();
		// ���ñ���
		JL_Name.setText("����");
		// ��������
		JL_Name.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Name.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_Name.setBounds(50 + 450, 340, 50, 25);

		JL_Sex = new JLabel();
		JL_Sex.setText("�Ա�");
		JL_Sex.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Sex.setForeground(Color.BLACK);
		JL_Sex.setBounds(50 + 450, 410, 100, 25);

		JL_Faculty = new JLabel();
		JL_Faculty.setText("Ժϵ");
		JL_Faculty.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Faculty.setForeground(Color.BLACK);
		JL_Faculty.setBounds(50 + 450, 480, 50, 25);

		JL_Speciality = new JLabel();
		JL_Speciality.setText("רҵ");
		JL_Speciality.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Speciality.setForeground(Color.BLACK);
		JL_Speciality.setBounds(50 + 450, 550, 100, 25);

	}

	public void upDate() {

		try {
			if (!JT_Name.getText().equals("����������") && !JT_Name.getText().equals("")) {
				Jdbc.SqlStatementUpdate(
						"UPDATE `ѧ����Ϣ` SET `����`='" + JT_Name.getText() + "' WHERE `ѧ��`=" + Login.SQL_Account);
			}
			Jdbc.SqlStatementUpdate(
					"UPDATE `ѧ����Ϣ` SET `�Ա�`='" + JCB_Sex.getSelectedItem() + "',`Ժϵ`='" + JCB_Faculty.getSelectedItem()
							+ "',`רҵ`='" + JCB_Speciality.getSelectedItem() + "' WHERE `ѧ��`=" + Login.SQL_Account);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void initValue() {
		JT_SID.setText("�˻�");
		JT_PWD.setText("����");
		JT_Name.setText("����");
	}

	public void getInfo() {
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ����Ϣ` WHERE `ѧ��`=" + Login.SQL_Account);
			rs.next();
			JT_SID.setText(rs.getString(1));
			JT_PWD.setText(rs.getString(6));
			if (!(rs.getString(2) + "").equals("null")) {
				JT_Name.setText(rs.getString(2));
			}
			JCB_Sex.setSelectedItem(rs.getString(3));
			JCB_Faculty.setSelectedItem(rs.getString(4));
			JCB_Speciality.setSelectedItem(rs.getString(5));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
