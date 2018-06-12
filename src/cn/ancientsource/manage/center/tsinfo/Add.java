package cn.ancientsource.manage.center.tsinfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.ancientsource.manage.center.BaseT;
import cn.ancientsource.manage.database.Jdbc;

public class Add extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -404256303904204347L;

	private String[][] Speciality;

	// ��������б�
	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��

	private JPanel base;

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

	public Add() {
		GUI();
	}

	public void GUI() {
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

		JFrameSet();
	}

	private void JFrameSet() {
		// ���������ô���
		this.setTitle("ѧ����Ϣ���");
		// ����������ʾ
		this.setContentPane(base);
		// ���ô����С
		this.setSize(500, 800);
		// ����λ��
		this.setLocation(800, 300);
		// ���õ���رհ�ť��Ĭ�϶���
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		// ��������
		this.setResizable(false);
		// ���ñ���ɫ����Ȼûɶ�ã�
		this.setBackground(Color.WHITE);
		// �õ�һ�� Toolkit ����
		Toolkit tool = this.getToolkit();
		// ͨ�� tool ��ȡͼ��
		Image Ico = tool.getImage(System.getProperty("user.dir") + "\\images\\9.png");
		// ����ͼ��
		this.setIconImage(Ico);
		// �����Ƿ�ɼ�
		this.setVisible(true);
		// ���þ���
		this.setLocationRelativeTo(null);
	}

	private void JComboBoxSet() {
		String[] Sex = new String[] { "Ů", "��" };
		JCB_Sex = new JComboBox<String>(Sex);
		JCB_Sex.setFont(new Font("г��", Font.BOLD, 25));
		JCB_Sex.setEditable(false);
		JCB_Sex.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JCB_Sex.setBounds(170, 410, 280, 35);
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
		JCB_Faculty.setBounds(170, 480, 280, 35);
		JCB_Faculty.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// ����ѡ���ѧԺ����רҵ
				JCB_Speciality.removeAllItems();
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
		JCB_Speciality.setBounds(170, 550, 280, 35);
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
		JT_SID = new JTextField("������ѧ��");
		JT_SID.setBounds(170, 195, 280, 35);
		JT_SID.setFont(new Font("г��", Font.BOLD, 25));
		JT_SID.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		JT_SID.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_SID.getText())) {
					JT_SID.setText("������ѧ��");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("������ѧ��".equals(JT_SID.getText())) {
					JT_SID.setText("");
				}
			}
		});
		JT_SID.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				// �� JTextField ���󴫸� JT_C
				JTextField JT_C = (JTextField) e.getSource();
				// �洢 JTextField ���ַ�
				String Text = JT_C.getText();
				// �жϣ����û�����ݾ��˳�����
				if (Text.length() == 0) {
					return;
				}
				// ��ȡĩβ��Ԫ��
				char ch = Text.charAt(Text.length() - 1);
				// ������ʾҪ��,��ֹ��һ��ɾ��
				if (Text.equals("������ѧ��")) {
					return;
				}
				;
				// �жϣ����������ĸ��ɾȥ
				if (!((ch >= '0' && ch <= '9') || (ch >= '0' && ch <= '9'))) {
					// ��д��������ֱ���޸����֣��������߳�����
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// ȥ�� JTextField �е�ĩβ�ַ�
							JT_C.setText(Text.substring(0, Text.length() - 1));
						}
					});
				}
				if (Text.length() > 8) {
					// ��д��������ֱ���޸����֣��������߳�����
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// ȥ�� JTextField �е�ĩβ�ַ�
							JT_C.setText(Text.substring(0, Text.length() - 1));
						}
					});
				}
			}
		});
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

		JT_PWD = new JTextField("����������");
		JT_PWD.setBounds(170, 265, 280, 35);
		JT_PWD.setFont(new Font("г��", Font.BOLD, 25));
		JT_PWD.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
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
		JT_PWD.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent arg0) {
				if ("".equals(JT_PWD.getText())) {
					JT_PWD.setText("����������");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if ("����������".equals(JT_PWD.getText())) {
					JT_PWD.setText("");
				}
			}
		});

		JT_Name = new JTextField("����������");
		JT_Name.setBounds(170, 335, 280, 35);
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
		JB_Enter = new JButton("ȷ��");
		JB_Enter.setBounds(160, 620, 200, 100);
		JB_Enter.setFont(new Font("����", Font.CENTER_BASELINE, 50));
		// JB_Enter.setOpaque(false);
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
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				upDate();
			}
		});
	}

	private void PanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\S.png");
		img = icon.getImage();

		base = new JPanel(){
			/**
			 * @author luo
			 */
			private static final long serialVersionUID = 1247415254217551987L;

			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
				super.paint(g);
			}
		};
		// ���ñ���͸��
		base.setBackground(null);
		// ���ÿؼ�͸��
		base.setOpaque(false);
		// ��ʹ���κβ���
		base.setLayout(null);

		// �������
		base.add(JL_Title);
		base.add(JL_SID);
		base.add(JL_PWD);
		base.add(JL_Name);
		base.add(JL_Sex);
		base.add(JL_Faculty);
		base.add(JL_Speciality);

		base.add(JT_SID);
		base.add(JT_PWD);
		base.add(JT_Name);

		base.add(JCB_Sex);
		base.add(JCB_Faculty);
		base.add(JCB_Speciality);

		base.add(JB_Enter);
	}

	private void JLabelSet() {
		// ��ʼ����������ʾ����
		JL_Title = new JLabel();
		// ���ñ���
		JL_Title.setText("�� �� �� Ϣ");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 60));
		// ����������ɫ
		JL_Title.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_Title.setBounds(75, 20, 400, 100);

		// ��ʼ����������ʾ����
		JL_SID = new JLabel();
		// ���ñ���
		JL_SID.setText("�˻�");
		// ��������
		JL_SID.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_SID.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_SID.setBounds(50, 200, 50, 25);

		// ��ʼ����������ʾ����
		JL_PWD = new JLabel();
		// ���ñ���
		JL_PWD.setText("����");
		// ��������
		JL_PWD.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_PWD.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_PWD.setBounds(50, 270, 50, 25);

		// ��ʼ����������ʾ����
		JL_Name = new JLabel();
		// ���ñ���
		JL_Name.setText("����");
		// ��������
		JL_Name.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Name.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_Name.setBounds(50, 340, 50, 25);

		JL_Sex = new JLabel();
		JL_Sex.setText("�Ա�");
		JL_Sex.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Sex.setForeground(Color.BLACK);
		JL_Sex.setBounds(50, 410, 100, 25);

		JL_Faculty = new JLabel();
		JL_Faculty.setText("Ժϵ");
		JL_Faculty.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Faculty.setForeground(Color.BLACK);
		JL_Faculty.setBounds(50, 480, 50, 25);

		JL_Speciality = new JLabel();
		JL_Speciality.setText("רҵ");
		JL_Speciality.setFont(new Font("��Բ", Font.PLAIN, 25));
		JL_Speciality.setForeground(Color.BLACK);
		JL_Speciality.setBounds(50, 550, 100, 25);

	}

	public void upDate() {
		if(JT_SID.getText().equals("") || JT_SID.getText().equals("������ѧ��") || JT_PWD.getText().equals("") || JT_PWD.getText().equals("����������")){
			JOptionPane.showMessageDialog(null, "ѧ�Ż�����δ���룡", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(JT_SID.getText().length() != 8){
			JOptionPane.showMessageDialog(null, "������8λѧ�ţ�", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(JT_PWD.getText().length() < 4){
			JOptionPane.showMessageDialog(null, "��������Ҫ��λ��", "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ����Ϣ` WHERE `ѧ��`=" + JT_SID.getText());
			if(rs.next()){
				JOptionPane.showMessageDialog(null, "���û��Ѵ��ڣ�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String sql = "insert into `ѧ����Ϣ` values ('" + JT_SID.getText() + "',";
			if(!JT_Name.getText().equals("") && !JT_Name.getText().equals("����������")){
				sql += "'" + JT_Name.getText() + "','" + JCB_Sex.getSelectedItem() +"','" + JCB_Faculty.getSelectedItem() + "','" + JCB_Speciality.getSelectedItem() + "','" + JT_PWD.getText() + "')";
			}else{
				sql += "null,'" + JCB_Sex.getSelectedItem() +"','" + JCB_Faculty.getSelectedItem() + "','" + JCB_Speciality.getSelectedItem() + "','" + JT_PWD.getText() + "')";
			}
			Jdbc.SqlStatementUpdate(sql);
			BaseT.tSInfo.reWrite();
			this.setVisible(false);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
