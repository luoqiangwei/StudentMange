package cn.ancientsource.manage.login;

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

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

public class Login extends JFrame {
	/**
	 * @author Qiangwei_Luo
	 */
	private static final long serialVersionUID = -6967500256047999437L;

	// private String SQLlib; // �洢 SQL ��� ��Ȼ��������Ҫ
	boolean flag = false; // �ж��Ƿ�ɹ�����

	protected static int SQL_CLP; // �洢���
	public static String SQL_Account = ""; // �洢���ݿ���ȡ�����˻�
	protected static String SQL_PWD = ""; // �洢���ݿ���ȡ��������

	private String Get_Account; // �洢�û�������˻�
	private String Get_PWD; // �洢�û����������

	ResultSet rs; // �洢���ݿⷵ�ص�����

	// ��������������ڱ����л�
	private Random rand = new Random();
	private int Set;

	// ��������б�
	private JLabel JL_Title; // ��ʵ��
	private JLabel JL_Account; // ��ʵ��
	private JLabel JL_Passwd; // ��ʵ��

	private JTextField JT_Account; // ��ʵ��

	private JPasswordField JP_Passwd; // ��ʵ��

	private ImageIcon icon; // ��ʵ��

	private Image img; // ��ʵ��
	private Image Ico; // ��ʵ��

	private JButton JB_login; // ��ʵ��
	private JButton JB_Register; // ��ʵ��

	private Toolkit tool;

	private JPanel base; // ��ʵ��

	public Login() {
		GUI();

	}

	// ��ʼ���ؼ�����ʾ
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
		// ����Ĭ�Ͻ��� ���Ѽ��봰���¼����������
		// JB_login.requestFocus();
	}

	public void JLabelSet() {
		JL_Title = new JLabel();
		// ���ñ���
		JL_Title.setText("ѧ������ϵͳ");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 50));
		// ����������ɫ
		JL_Title.setForeground(Color.BLACK);
		// ����λ�ô�С setBounds(int x, int y, int width, int height)
		JL_Title.setBounds(110, 20, 400, 100);

		// ��ʼ����������ʾ����
		JL_Account = new JLabel("�˻�");
		// ��������
		JL_Account.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Account.setForeground(Color.BLACK);
		// ����λ�ô�С
		JL_Account.setBounds(100, 200, 50, 25);

		// ��ʼ����������ʾ����
		JL_Passwd = new JLabel("����");
		// ��������
		JL_Passwd.setFont(new Font("��Բ", Font.PLAIN, 25));
		// ����������ɫ
		JL_Passwd.setForeground(Color.BLACK);
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
		JT_Account.setOpaque(true);
		// ���ñ���͸��
		JT_Account.setBackground(new Color(235, 238, 245));

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

		// �����¼������óɰ��»س����ɵ���
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
					JB_login.setBackground(new Color(190, 190, 190)); // ���ð�ť����
					Login_try();
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
		// ���ÿؼ�͸��
		JP_Passwd.setOpaque(true);
		// ���ñ���͸��
		JP_Passwd.setBackground(new Color(225, 228, 235));

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

		// �����¼������óɰ��»س����ɵ���
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
					JB_login.setBackground(new Color(190, 190, 190)); // ���ð�ť����
					Login_try();
				}
			}
		});
	}

	public void JButtonSet() {
		// ��ʼ��
		JB_login = new JButton("��½");
		// ����λ�ô�С
		JB_login.setBounds(170, 350, 100, 50);
		// ��������
		JB_login.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_login.setOpaque(true);
		// ���ñ���͸��
		JB_login.setBackground(new Color(147, 136, 147));
		// �����Ƿ���Ʊ߿�
		JB_login.setBorderPainted(true);
		// �����ƽ���
		JB_login.setFocusPainted(false);

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
				JB_login.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Login_try();
			}
		});

		JB_login.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					Login_try();
				}
			}
		});

		// �۽��¼�
		JB_login.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_login.setBackground(new Color(147, 136, 147));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_login.setBackground(new Color(190, 190, 190));
			}
		});

		// ��ʼ��
		JB_Register = new JButton("ע��");
		// ����λ�ô�С
		JB_Register.setBounds(320, 350, 100, 50);
		// ��������
		JB_Register.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_Register.setOpaque(true);
		// ���ñ���͸��
		JB_Register.setBackground(new Color(145, 133, 157));
		// �����Ƿ���Ʊ߿�
		JB_Register.setBorderPainted(true);
		// �����ƽ���
		JB_Register.setFocusPainted(false);

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
				JB_Register.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				JB_login.requestFocus(); // ��ʼ������
				Init_values(); // ��յ�ǰ�����ֵ
				Login.this.setVisible(false); // ������������ڣ�ת����һ������
				GUISystem.register.setVisible(true); // ��ʾע�ᴰ��
				Login.this.setLocationRelativeTo(null); // ��λ����λ��
			}
		});

		JB_Register.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_Register.setBackground(new Color(145, 133, 157));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_Register.setBackground(new Color(190, 190, 190));
			}
		});
	}

	public void PanelSet() {
		// ���ñ���(��ȡͼƬ��ַ)
		SetBackGround();
		// ������������������
		base = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 2329755506587564311L;

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
	}

	public void FrameSet() {
		// ���������ô���
		this.setTitle("ѧ������ϵͳ�������");
		// ����������ʾ
		this.setContentPane(base);
		// ���ô����С
		this.setSize(500, 500);
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
		this.setVisible(true);
		// ���þ���
		this.setLocationRelativeTo(null);
		// �����¼�
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				JB_login.requestFocus();
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
				// ����Ĭ�Ͻ���
				JB_login.requestFocus();
			}
		});
	}

	// �����¼��Ĵ�����
	private void Login_try() {
		// ��ȡ�û������룬���ܴ�ʩ��ʱ����
		Get_Account = JT_Account.getText();
		Get_PWD = String.valueOf(JP_Passwd.getPassword()); // ����ȫ������
		try {
			// ����ִ�� SQL ���
			rs = Jdbc.SqlStatement("select * from `��ʦ��Ϣ` where `����` = '" + Get_Account + "'");
		} catch (SQLException e) {
			// �쳣����
			e.printStackTrace();
		}
		// ��ʦ
		try {
			if (rs.next()) {
				try {
					// ��ʦ�ı�ʶ
					SQL_CLP = 1;
					// ȡ�˻�
					SQL_Account = rs.getInt(1) + "";
					// ȡ����
					SQL_PWD = rs.getString(3) + "";
				} catch (SQLException e) {
					// �쳣����
					e.printStackTrace();
				}
			} else {
				try {
					rs = Jdbc.SqlStatement("select * from `ѧ����Ϣ` where `ѧ��` = '" + Get_Account +"'");

					if (rs.next()) {
						// ѧ���ı�ʶ
						SQL_CLP = 2;
						// ȡ�˻�
						SQL_Account = rs.getInt(1) + "";
						// ȡ����
						SQL_PWD = rs.getString(6) + "";
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// ����˻���������ȷ�Ҵ����������
		if ((Get_PWD.equals(SQL_PWD)) && (SQL_Account.equals(Get_Account))) {
			flag = true;
		}
		if (!flag) {
			if ((!(Get_PWD.equals(SQL_PWD))) && (SQL_Account.equals(Get_Account))) {
				JOptionPane.showMessageDialog(null, "�������", "����", JOptionPane.ERROR_MESSAGE);
			} else if(Get_Account.length() != 4 && Get_Account.length() != 8){
				JOptionPane.showMessageDialog(null, "������ 4 λ���� 8 λ�˺ţ�", "����", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null, "���˻������ڣ�", "����", JOptionPane.ERROR_MESSAGE);
			}
		}

		// ��ת
		if (flag) {
			if (SQL_CLP == 1) {
				this.setVisible(false);
				GUISystem.center.switchT();
				GUISystem.center.setVisible(true);
				Login.this.setLocationRelativeTo(null); // ��λ����λ��
			} else {
				this.setVisible(false);
				GUISystem.center.switchS();
				GUISystem.center.setVisible(true);
				Login.this.setLocationRelativeTo(null); // ��λ����λ��
			}
		}
	}

	public void Init_values() {
		// ��ʼ��ֵ
		flag = false; // �ж��Ƿ�ɹ�����

		SQL_CLP = 0; // �洢Ȩ�޵ȼ�
		SQL_Account = ""; // �洢���ݿ���ȡ�����˻�
		SQL_PWD = ""; // �洢���ݿ���ȡ��������

		Get_Account = ""; // �洢�û�������˻�
		Get_PWD = ""; // �洢�û����������

		rs = null; // �洢���ݿⷵ�ص�����

		JT_Account.setText("�������˻���");

		JP_Passwd.setText("����������");
		JP_Passwd.setEchoChar((char) 0);
	}

	public void SetBackGround() {
		/*
		 * JL_Title.setForeground(Color.WHITE);
		 * JL_Account.setForeground(Color.WHITE);
		 * JL_Passwd.setForeground(Color.WHITE);
		 */
		// ��ȡ��������ѡ�񱳾�
		// ����������ʱ����������������ɫ
		Set = rand.nextInt(50);
		switch (Set) {
		case 0:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\0.png");
			break;
		case 1:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\1.png");
			break;
		case 2:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\2.png");
			break;
		case 3:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\3.png");
			break;
		case 4:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\4.png");
			break;
		case 5:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\5.png");
			break;
		case 6:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\6.png");
			break;
		case 7:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\7.png");
			break;
		case 8:
			// ��ȡ��ǰ��Ŀ·��
			// String t = System.getProperty("user.dir");
			// ��ȡͼƬ·��... MMP ��֪��Ϊʲô�����·�����Ǵ�ӡ������...
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\8.png");
			break;
		case 9:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\9.png");
			break;
		case 10:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\10.png");
			break;
		case 11:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\11.png");
			break;
		case 12:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\12.png");
			break;
		case 13:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\13.png");
			break;
		case 14:
			JL_Passwd.setForeground(new Color(170, 170, 10));
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\14.png");
			break;
		case 15:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\15.png");
			break;
		case 16:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\16.png");
			break;
		case 17:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\17.png");
			break;
		case 18:
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\18.png");
			break;
		case 19:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\19.png");
			break;
		case 20:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\20.png");
			break;
		case 21:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\21.png");
			break;
		case 22:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\22.png");
			break;
		case 23:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\23.png");
			break;
		case 24:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\24.png");
			break;
		case 25:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\25.png");
			break;
		case 26:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\26.png");
			break;
		case 27:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\27.png");
			break;
		case 28:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\28.png");
			break;
		case 29:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\29.png");
			break;
		case 30:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\30.png");
			break;
		case 31:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\31.png");
			break;
		case 32:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\32.png");
			break;
		case 33:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\33.png");
			break;
		case 34:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\34.png");
			break;
		case 35:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\35.png");
			break;
		case 36:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\36.png");
			break;
		case 37:
			JL_Title.setForeground(Color.WHITE);
			JL_Account.setForeground(Color.WHITE);
			JL_Passwd.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\37.png");
			break;
		case 38:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\38.png");
			break;
		case 39:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\39.png");
			break;
		case 40:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\40.png");
			break;
		case 41:
			JL_Title.setForeground(Color.WHITE);
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\41.png");
			break;
		case 42:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\42.png");
			break;
		case 43:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\43.png");
			break;
		case 44:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\44.png");
			break;
		case 45:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\45.png");
			break;
		case 46:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\46.png");
			break;
		case 47:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\47.png");
			break;
		case 48:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\48.png");
			break;
		case 49:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\49.png");
			break;
		case 50:
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\LoginBackGround\\50.png");
			break;

		}
		img = icon.getImage();
	}
}
