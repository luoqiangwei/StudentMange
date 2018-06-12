package cn.ancientsource.manage.register;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.ancientsource.manage.count.GUISystem;
import cn.ancientsource.manage.database.Jdbc;

public class Register extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -6968091735063327215L;
	private String SQL_Account; // ����ӵ����û�
	private String SQL_PWD; // ����ӵ����û�����

	private String SQLsm1; // SQL1 ���
	private String SQLsm2; // SQL2 ���
	private String SQLsm3; // SQL1 ���

	private boolean flag = false; // �ж��Ƿ�ɹ�ע��

	// ��������б�
	private JLabel JL_Title; // ��ʵ��
	private JLabel JL_Account; // ��ʵ��
	private JLabel JL_Passwd; // ��ʵ��

	private JTextField JT_Account; // ��ʵ��

	private JPasswordField JP_Passwd; // ��ʵ��
	private JPasswordField JP_RePasswd;

	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��
	private Image Ico; // ��ʵ��

	private JButton JB_login; // ��ʵ��
	private JButton JB_Register; // ��ʵ��

	private Toolkit tool;

	private JPanel base; // ��ʵ��

	public Register() {
		GUI();
	}

	public void GUI() {
		// ��ť���ó�ʼ��
		JButtonSet();
		// ���ֿ����ó�ʼ��
		JTextFieldSet();
		// ��������ó�ʼ��
		JPasswordFieldSet();
		// �ı������ó�ʼ��
		JLabelSet();
		// �μ�������ʼ��
		PanelSet();
		// ����������ʼ��
		FrameSet();
	}

	public void JLabelSet() {
		JL_Title = new JLabel();
		// ���ñ���
		JL_Title.setText("�� ע��ϵͳ ��");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 50));
		// ����������ɫ
		JL_Title.setForeground(Color.WHITE);
		// ����λ�ô�С setBounds(int x, int y, int width, int height)
		JL_Title.setBounds(100, 20, 400, 100);

		// ��ʼ����������ʾ����
		JL_Account = new JLabel("�˻�");
		// ��������
		JL_Account.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Account.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_Account.setBounds(100, 200, 50, 25);

		// ��ʼ����������ʾ����
		JL_Passwd = new JLabel("����");
		// ��������
		JL_Passwd.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Passwd.setForeground(Color.WHITE);
		// ����λ�ô�С
		JL_Passwd.setBounds(100, 270, 50, 25);

	}

	public void JTextFieldSet() {
		// ��ʼ��
		JT_Account = new JTextField("�������˻���");
		// ����λ�ô�С
		JT_Account.setBounds(170, 195, 250, 35);
		// ��������
		JT_Account.setFont(new Font("г��", Font.BOLD, 25));
		// ���ý�ֹֻ��
		JT_Account.setEditable(true);
		// ���ÿؼ�͸��
		// JT_Account.setOpaque(true);
		// ���ñ���͸��
		JT_Account.setOpaque(false);
		// ����������ɫ
		JT_Account.setForeground(Color.WHITE);
		// ���ñ߿�
		JT_Account.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		// ���ò��������꣩����ɫ
		JT_Account.setCaretColor(Color.WHITE);

		// ��Ӿ۽��¼� ��������¼���̫�������ȥ��
		JT_Account.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) { // �۽���
				if ("�������˻���".equalsIgnoreCase(JT_Account.getText())) {
					JT_Account.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) { // �޾ۼ�
				if ("".equals(JT_Account.getText().trim())) {
					JT_Account.setText("�������˻���");
				}
			}

		});

		// ���������¼�
		JT_Account.addCaretListener(new CaretListener() {

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
				if (Text.equals("�������˻���")) {
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

		// �����¼������óɰ��»س�����ע��
		JT_Account.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // ������������ڣ�ת����һ������
						GUISystem.registerWrite.setVisible(true); // ת�����봰��
						flag = false;
					}
				}
			}
		});
	}

	public void JPasswordFieldSet() {
		// ��ʼ��
		JP_Passwd = new JPasswordField("����������");
		// ����λ�ô�С
		JP_Passwd.setBounds(170, 265, 250, 35);
		// ��������
		JP_Passwd.setFont(new Font("г��", Font.BOLD, 25));
		// ���ý�ֹֻ��
		JP_Passwd.setEditable(true);
		// ����������ʾ
		JP_Passwd.setEchoChar((char) 0);
		// ���ñ���͸��
		JP_Passwd.setOpaque(false);
		// ����������ɫ
		JP_Passwd.setForeground(Color.WHITE);
		// ���ñ߿�
		JP_Passwd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// ���ò��������꣩����ɫ
		JP_Passwd.setCaretColor(Color.WHITE);

		// ���þ۽��¼�
		JP_Passwd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { // �Ǿ۽��¼�
				if ("".equals(String.valueOf(JP_Passwd.getPassword()).trim())) {
					JP_Passwd.setEchoChar((char) 0);
					JP_Passwd.setText("����������");
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // �۽��¼�
				// valueOf����������ͨ��new һ��String ���������ת����
				if ("����������".equalsIgnoreCase(String.valueOf(JP_Passwd.getPassword()))) {
					JP_Passwd.setText("");
					JP_Passwd.setEchoChar('I');
				}
			}
		});

		// ����Ͳ��������ֵ�ʹ����~

		// �����¼������óɰ��»س�����ע��
		JP_Passwd.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // ������������ڣ�ת����һ������
						GUISystem.registerWrite.setVisible(true); // ת�����봰��
						flag = false;
					}
				}
			}
		});

		// ��ʼ��
		JP_RePasswd = new JPasswordField("���ٴ���������");
		// ����λ�ô�С
		JP_RePasswd.setBounds(170, 335, 250, 35);
		// ��������
		JP_RePasswd.setFont(new Font("г��", Font.BOLD, 25));
		// ���ý�ֹֻ��
		JP_RePasswd.setEditable(true);
		// ����������ʾ
		JP_RePasswd.setEchoChar((char) 0);
		// ���ñ���͸��
		JP_RePasswd.setOpaque(false);
		// ����������ɫ
		JP_RePasswd.setForeground(Color.WHITE);
		// ���ñ߿�
		JP_RePasswd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// ���ò��������꣩����ɫ
		JP_RePasswd.setCaretColor(Color.WHITE);

		// ���þ۽��¼�
		JP_RePasswd.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) { // �Ǿ۽��¼�
				if ("".equals(String.valueOf(JP_RePasswd.getPassword()).trim())) {
					JP_RePasswd.setEchoChar((char) 0);
					JP_RePasswd.setText("���ٴ���������");
				}
			}

			@Override
			public void focusGained(FocusEvent e) { // �۽��¼�
				// valueOf����������ͨ��new һ��String ���������ת����
				if ("���ٴ���������".equalsIgnoreCase(String.valueOf(JP_RePasswd.getPassword()))) {
					JP_RePasswd.setText("");
					JP_RePasswd.setEchoChar('I');
				}
			}
		});

		// ����Ͳ��������ֵ�ʹ����~

		// �����¼������óɰ��»س�����ע��
		JP_RePasswd.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // ������������ڣ�ת����һ������
						GUISystem.registerWrite.setVisible(true); // ת�����봰��
						flag = false;
					}
				}
			}
		});
	}

	public void JButtonSet() {
		// ��ʼ��
		JB_login = new JButton("��½");
		// ����λ�ô�С
		JB_login.setBounds(320, 420, 100, 50);
		// ��������
		JB_login.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_login.setOpaque(true);
		// ���ñ���
		JB_login.setBackground(new Color(11, 82, 175));
		// �����Ƿ���Ʊ߿�
		JB_login.setBorderPainted(true);
		// �����ƽ���
		JB_login.setFocusPainted(false);
		// ����������ɫ
		JB_login.setForeground(Color.WHITE);

		// ��д�¼�
		JB_login.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���ñ߿�
				JB_login.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// ���ñ߿�
				JB_login.setBorder(BorderFactory.createLineBorder(new Color(164, 167, 225)));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				JB_Register.requestFocus(); // ��ʼ������
				Init_values(); // ��ʼ��ֵ
				Register.this.setVisible(false); // ������������ڣ�ת����һ������
				GUISystem.login.setVisible(true); // ��ʾ���봰��
				Register.this.setLocationRelativeTo(null); // ��λ����λ��
			}
		});

		// �۽��¼�
		JB_login.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_login.setBackground(new Color(11, 82, 175));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_login.setBackground(new Color(61, 132, 225));
			}
		});

		// ��ʼ��
		JB_Register = new JButton("ע��");
		// ����λ�ô�С
		JB_Register.setBounds(170, 420, 100, 50);
		// ��������
		JB_Register.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_Register.setOpaque(true);
		// ���ñ���
		JB_Register.setBackground(new Color(21, 92, 195));
		// �����Ƿ���Ʊ߿�
		JB_Register.setBorderPainted(true);
		// �����ƽ���
		JB_Register.setFocusPainted(false);
		// ����������ɫ
		JB_Register.setForeground(Color.WHITE);

		// ��д�¼�
		JB_Register.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���ñ߿�
				JB_Register.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// ���ñ߿�
				JB_Register.setBorder(BorderFactory.createLineBorder(new Color(164, 167, 225)));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Register_try();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (flag) {
					if (JT_Account.getText().length() == 8)
						GUISystem.registerWrite.switchPanelS();
					if (JT_Account.getText().length() == 4)
						GUISystem.registerWrite.switchPanelT();
					GUISystem.register.setVisible(false); // ������������ڣ�ת����һ������
					GUISystem.registerWrite.setVisible(true); // ת�����봰��
					Init_values();
					Register.this.setLocationRelativeTo(null); // ��λ����λ��
					flag = false;
				}
			}
		});

		JB_Register.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Register_try();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					if (flag) {
						GUISystem.register.setVisible(false); // ������������ڣ�ת����һ������
						if (JT_Account.getText().length() == 8)
							GUISystem.registerWrite.switchPanelS();
						if (JT_Account.getText().length() == 4)
							GUISystem.registerWrite.switchPanelT();
						GUISystem.registerWrite.setVisible(true); // ת�����봰��
						Init_values();
						Register.this.setLocationRelativeTo(null); // ��λ����λ��
						flag = false;
					}
				}
			}
		});

		JB_Register.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_Register.setBackground(new Color(21, 92, 195));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_Register.setBackground(new Color(77, 142, 245));
			}
		});
	}

	public void PanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\10.png");
		img = icon.getImage();
		// ������������������
		base = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 3576260302275511426L;

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
		base.add(JL_Account);
		base.add(JL_Passwd);
		base.add(JT_Account);
		base.add(JP_Passwd);
		base.add(JB_login);
		base.add(JB_Register);
		base.add(JP_RePasswd);

	}

	public void FrameSet() {
		// ���������ô���
		this.setTitle("ѧ������ϵͳע�����");
		// ����������ʾ
		this.setContentPane(base);
		// ���ô����С
		this.setSize(500, 570);
		// ����λ��
		this.setLocation(800, 300);
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
		// ���þ���
		this.setLocationRelativeTo(null);
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
				JB_Register.requestFocus();
			}
		});
	}

	// ע��ģ��
	private void Register_try() throws SQLException {
		// ��ȡע���˻�
		SQL_Account = JT_Account.getText();
		// ��ȡע������
		SQL_PWD = String.valueOf(JP_Passwd.getPassword());
		// �����벻�淶�Ĵ���
		if ("�������˻���".equals(SQL_Account)) {
			JOptionPane.showMessageDialog(null, "�������˻�����", "����", JOptionPane.ERROR_MESSAGE);
		} else if ("����������".equals(SQL_PWD)) {
			JOptionPane.showMessageDialog(null, "���������룡", "����", JOptionPane.ERROR_MESSAGE);
		} else if (!SQL_PWD.equals(String.valueOf(JP_RePasswd.getPassword()))) {
			JOptionPane.showMessageDialog(null, "�������벻һ�£�", "����", JOptionPane.ERROR_MESSAGE);
		}
		// �ж�������˻��Ƿ���Ϲ淶
		if (!"�������˻���".equals(SQL_Account) && !"����������".equals(SQL_PWD)
				&& SQL_PWD.equals(String.valueOf(JP_RePasswd.getPassword()))) {
			SQLsm2 = "select * from `��ʦ��Ϣ` where `����` = " + SQL_Account;
			SQLsm3 = "select * from `ѧ����Ϣ` where `ѧ��` = " + SQL_Account;
			// �ж��˺��Ƿ��Ѿ�����
			boolean flag1 = Jdbc.SqlStatement(SQLsm3).next();
			if (!flag1)
				flag1 = Jdbc.SqlStatement(SQLsm2).next();

			if (SQL_Account.length() == 8 && SQL_PWD.length() > 3 && !flag1) {
				// ���뵽ѧ����
				SQLsm1 = "insert into `ѧ����Ϣ`(`ѧ��`,`��������`) values(" + SQL_Account + "," + SQL_PWD + ")";
				Jdbc.SqlStatementUpdate(SQLsm1);
				flag = true;
				GUISystem.registerWrite.setDeualt(SQL_Account);
			} else if (SQL_Account.length() == 4 && SQL_PWD.length() > 3 && !flag1) {
				// ���뵽��ʦ��
				SQLsm1 = "insert into `��ʦ��Ϣ`(`����`,`��������`) values(" + SQL_Account + "," + SQL_PWD + ")";
				Jdbc.SqlStatementUpdate(SQLsm1);
				flag = true;
				GUISystem.registerWrite.setDeualt(SQL_Account);
			}
			// ����˻����ڻ������������벻�Ϲ淶
			if (flag1) {
				JOptionPane.showMessageDialog(null, "���˻��Ѵ��ڣ�", "����", JOptionPane.ERROR_MESSAGE);
				Init_values();
			} else if (SQL_Account.length() != 8 && SQL_Account.length() != 4) {
				JOptionPane.showMessageDialog(null, "���˻�ע�����\n������ 8 λѧ�Ż� 4 λ���ţ�", "����", JOptionPane.ERROR_MESSAGE);
			} else if (SQL_PWD.length() < 4) {
				JOptionPane.showMessageDialog(null, "���˻�ע�����\n������������λ���룡", "����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void Init_values() {
		SQL_Account = ""; // ����ӵ����û�
		SQL_PWD = ""; // ����ӵ����û�����

		flag = false;

		JT_Account.setText("�������˻���");
		JP_Passwd.setText("����������");
		JP_RePasswd.setText("���ٴ���������");
		JP_Passwd.setEchoChar((char) 0);
		JP_RePasswd.setEchoChar((char) 0);
	}
}
