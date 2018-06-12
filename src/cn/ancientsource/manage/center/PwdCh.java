package cn.ancientsource.manage.center;

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

public class PwdCh extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = 4814260960299623205L;
	private String SQL_Account; // �û���ʶ
	private String SQL_PWD; // ����ӵ����û�����

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

	private JButton JB_ch; // ��ʵ��

	private Toolkit tool;

	private JPanel base; // ��ʵ��

	public PwdCh(String Account) {
		SQL_Account = Account;
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
		JL_Title.setText("�� �����޸� ��");
		// ��������
		JL_Title.setFont(new Font("����", Font.PLAIN, 50));
		// ����������ɫ
		JL_Title.setForeground(Color.BLACK);
		// ����λ�ô�С setBounds(int x, int y, int width, int height)
		JL_Title.setBounds(100, 20, 400, 100);

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
		JT_Account = new JTextField(SQL_Account);
		// ����λ�ô�С
		JT_Account.setBounds(170, 195, 250, 35);
		// ��������
		JT_Account.setFont(new Font("г��", Font.BOLD, 25));
		// ���ý�ֹֻ��
		JT_Account.setEditable(false);
		// ���ÿؼ�͸��
		// JT_Account.setOpaque(true);
		// ���ñ���͸��
		JT_Account.setOpaque(false);
		// ����������ɫ
		JT_Account.setForeground(Color.BLACK);
		// ���ñ߿�
		JT_Account.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
		// ���ò��������꣩����ɫ
		JT_Account.setCaretColor(Color.BLACK);
		// ���־���
		JT_Account.setHorizontalAlignment(JTextField.CENTER);

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
		JP_Passwd.setForeground(Color.BLACK);
		// ���ñ߿�
		JP_Passwd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// ���ò��������꣩����ɫ
		JP_Passwd.setCaretColor(Color.BLACK);

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
		JP_RePasswd.setForeground(Color.BLACK);
		// ���ñ߿�
		JP_RePasswd.setBorder(BorderFactory.createLineBorder(new Color(73, 95, 219)));
		// ���ò��������꣩����ɫ
		JP_RePasswd.setCaretColor(Color.BLACK);

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
		JB_ch = new JButton("�޸�");
		// ����λ�ô�С
		JB_ch.setBounds(320 - 130, 420, 200, 50);
		// ��������
		JB_ch.setFont(new Font("����", Font.CENTER_BASELINE, 30));
		// ���ÿؼ�͸��
		JB_ch.setOpaque(true);
		// ���ñ���
		JB_ch.setBackground(new Color(160, 179, 190));
		// �����Ƿ���Ʊ߿�
		JB_ch.setBorderPainted(true);
		// �����ƽ���
		JB_ch.setFocusPainted(false);
		// ����������ɫ
		JB_ch.setForeground(Color.BLACK);

		// ��д�¼�
		JB_ch.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// ���ñ߿�
				JB_ch.setBorder(null);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// ���ñ߿�
				JB_ch.setBorder(BorderFactory.createLineBorder(new Color(58, 66, 69)));
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					Register_try();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		// �۽��¼�
		JB_ch.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				JB_ch.setBackground(new Color(160, 179, 190));
			}

			@Override
			public void focusGained(FocusEvent e) {
				JB_ch.setBackground(new Color(61, 132, 225));
			}
		});
	}

	public void PanelSet() {
		// ���ñ���
		// ��ȡͼƬ·��
		icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\12.png");
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
		base.add(JB_ch);
		base.add(JP_RePasswd);

	}

	public void FrameSet() {
		// ���������ô���
		this.setTitle("ѧ������ϵͳ�޸Ľ���");
		// ����������ʾ
		this.setContentPane(base);
		// ���ô����С
		this.setSize(500, 570);
		// ����λ��
		this.setLocation(800, 300);
		// ���õ���رհ�ť��Ĭ�϶���
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
				//Jdbc.closeSql(); // �رճ���ǰ�ȹر����ݿ�
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

	// �޸������ģ��
	private void Register_try() throws SQLException {
		if(String.valueOf(JP_Passwd.getPassword()).equals(String.copyValueOf(JP_RePasswd.getPassword())) && String.valueOf(JP_Passwd.getPassword()).length() > 3){
			SQL_PWD = String.valueOf(JP_Passwd.getPassword());
			if(JT_Account.getText().length() == 4){
				Jdbc.SqlStatementUpdate("UPDATE `��ʦ��Ϣ` SET `��������`='" + SQL_PWD + "' WHERE `����`='" + SQL_Account + "'");
				this.setVisible(false);
				BaseT.tInfo.getInfo();
			}else{
				Jdbc.SqlStatementUpdate("UPDATE `ѧ����Ϣ` SET `��������`='" + SQL_PWD + "' WHERE `ѧ��`='" + SQL_Account + "'");
				BaseS.sInfo.getInfo();
				this.setVisible(false);
			}
		}else if(String.valueOf(JP_Passwd.getPassword()).length() < 4){
			JOptionPane.showMessageDialog(null, "�����޸Ĵ���\n������������λ���룡", "����", JOptionPane.ERROR_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, "�����޸Ĵ���\n�������벻һ�£�", "����", JOptionPane.ERROR_MESSAGE);
			Init_values();
		}
	}

	public void Init_values() {
		SQL_Account = ""; // ����ӵ����û�
		SQL_PWD = ""; // ����ӵ����û�����

		flag = false;
		
		JP_Passwd.setText("����������");
		JP_RePasswd.setText("���ٴ���������");
		JP_Passwd.setEchoChar((char) 0);
		JP_RePasswd.setEchoChar((char) 0);
	}
}
