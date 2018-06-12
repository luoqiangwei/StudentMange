package cn.ancientsource.manage.center.tscore;

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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.ancientsource.manage.center.BaseT;
import cn.ancientsource.manage.database.Jdbc;

public class Update extends JFrame {
	/**
	 * @author luo
	 */
	private static final long serialVersionUID = -6585881719980360121L;

		// ��������б�
		private ImageIcon icon; // ��ʵ��

		private Image img; // ��ʵ��

		private JPanel base;

		private JLabel JL_Title;
		// ѧ��
		private JLabel JL_SID;
		private JLabel JL_S1;
		private JLabel JL_S2;
		private JLabel JL_S3;

		private JTextField JT_SID;
		private JTextField JT_S1;
		private JTextField JT_S2;
		private JTextField JT_S3;
		
		private int sum;

		private JButton JB_Enter;

		public Update(String account) {
			GUI();
			JT_SID.setText(account);
			try {
				ResultSet rs = Jdbc.SqlStatement("SELECT * FROM `ѧ���ɼ�` WHERE `ѧ��`=" + account);
				rs.next();
				JT_S1.setText(rs.getString(3));
				JT_S2.setText(rs.getString(4));
				JT_S3.setText(rs.getString(5));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}

		public void GUI() {
			// ��ť���ó�ʼ��
			JButtonSet();
			// ���ֿ����ó�ʼ��
			JTextFieldSet();
			// �ı������ó�ʼ��
			JLabelSet();
			// �μ�������ʼ��
			PanelSet();

			JFrameSet();
		}

		private void JFrameSet() {
			// ���������ô���
			this.setTitle("ѧ����Ϣ�޸�");
			// ����������ʾ
			this.setContentPane(base);
			// ���ô����С
			this.setSize(500, 800 - 100);
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
			JT_SID.setEditable(false);
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

			JT_S1 = new JTextField("������ɼ�1");
			JT_S1.setBounds(170, 265, 280, 35);
			JT_S1.setFont(new Font("г��", Font.BOLD, 25));
			JT_S1.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
			JT_S1.addKeyListener(new KeyListener() {

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
			JT_S1.addCaretListener(new CaretListener() {

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
					if (Text.equals("������ɼ�1")) {
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
					if (Text.length() > 3) {
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
			JT_S1.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					if ("".equals(JT_S1.getText())) {
						JT_S1.setText("������ɼ�1");
					}
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					if ("������ɼ�1".equals(JT_S1.getText())) {
						JT_S1.setText("");
					}
				}
			});
			
			JT_S2 = new JTextField("������ɼ�2");
			JT_S2.setBounds(170, 335, 280, 35);
			JT_S2.setFont(new Font("г��", Font.BOLD, 25));
			JT_S2.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
			JT_S2.addKeyListener(new KeyListener() {

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
			JT_S2.addCaretListener(new CaretListener() {

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
					if (Text.equals("������ɼ�2")) {
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
					if (Text.length() > 3) {
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
			JT_S2.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					if ("".equals(JT_S2.getText())) {
						JT_S2.setText("������ɼ�2");
					}
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					if ("������ɼ�2".equals(JT_S2.getText())) {
						JT_S2.setText("");
					}
				}
			});
			
			JT_S3 = new JTextField("������ɼ�3");
			JT_S3.setBounds(170, 410, 280, 35);
			JT_S3.setFont(new Font("г��", Font.BOLD, 25));
			JT_S3.setBorder(BorderFactory.createLineBorder(new Color(123, 165, 254)));
			JT_S3.addCaretListener(new CaretListener() {

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
					if (Text.equals("������ɼ�3")) {
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
					if (Text.length() > 3) {
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
			JT_S3.addKeyListener(new KeyListener() {

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
			JT_S3.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent arg0) {
					if ("".equals(JT_S3.getText())) {
						JT_S3.setText("������ɼ�3");
					}
				}

				@Override
				public void focusGained(FocusEvent arg0) {
					if ("������ɼ�3".equals(JT_S3.getText())) {
						JT_S3.setText("");
					}
				}
			});
		}

		private void JButtonSet() {
			JB_Enter = new JButton("ȷ��");
			JB_Enter.setBounds(160, 620 - 100, 200, 100);
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
			icon = new ImageIcon(System.getProperty("user.dir") + "\\images\\Sore.png");
			img = icon.getImage();

			base = new JPanel(){
				/**
				 * @author luo
				 */
				private static final long serialVersionUID = -6447702046193914178L;

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
			base.add(JL_S1);
			base.add(JL_S2);
			base.add(JL_S3);

			base.add(JT_SID);
			base.add(JT_S1);
			base.add(JT_S2);
			base.add(JT_S3);

			base.add(JB_Enter);
		}

		private void JLabelSet() {
			// ��ʼ����������ʾ����
			JL_Title = new JLabel();
			// ���ñ���
			JL_Title.setText("�� �� �� ��");
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
			
			JL_S1 = new JLabel("�ɼ�1");
			JL_S1.setFont(new Font("��Բ", Font.PLAIN, 25));
			JL_S1.setForeground(Color.BLACK);
			JL_S1.setBounds(50, 270, 100, 25);
			
			JL_S2 = new JLabel("�ɼ�2");
			JL_S2.setFont(new Font("��Բ", Font.PLAIN, 25));
			JL_S2.setForeground(Color.BLACK);
			JL_S2.setBounds(50, 340, 100, 25);
			
			JL_S3 = new JLabel("�ɼ�3");
			JL_S3.setFont(new Font("��Բ", Font.PLAIN, 25));
			JL_S3.setForeground(Color.BLACK);
			JL_S3.setBounds(50, 410, 100, 25);
		}

		public void upDate() {
			try {
				sum = 0;
				if(!JT_S1.getText().equals("") && !JT_S1.getText().equals("������ɼ�1")){
					sum += Integer.valueOf(JT_S1.getText());
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�1`=" + JT_S1.getText() + " WHERE `ѧ��`=" + JT_SID.getText());
				}else{
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�1`=0 WHERE `ѧ��`=" + JT_SID.getText());
				}
				if(!JT_S2.getText().equals("") && !JT_S2.getText().equals("������ɼ�2")){
					sum += Integer.valueOf(JT_S2.getText());
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�2`=" + JT_S2.getText() + " WHERE `ѧ��`=" + JT_SID.getText());
				}else{
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�2`=0 WHERE `ѧ��`=" + JT_SID.getText());
				}
				if(!JT_S3.getText().equals("") && !JT_S3.getText().equals("������ɼ�3")){
					sum += Integer.valueOf(JT_S3.getText());
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�3`=" + JT_S3.getText() + " WHERE `ѧ��`=" + JT_SID.getText());
				}else{
					Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ɼ�3`=0 WHERE `ѧ��`=" + JT_SID.getText());
				}
				Jdbc.SqlStatementUpdate("UPDATE `ѧ���ɼ�` SET `�ܷ�`=" + sum + " WHERE `ѧ��`=" + JT_SID.getText());
				BaseT.tScore.reWrite();
				this.setVisible(false);
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
}
