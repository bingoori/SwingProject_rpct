package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.UpdateModel;

public final class LoginView extends JoinView implements ActionListener {

	private UpdateModel model = new UpdateModel();
	private JLayeredPane loginLayeredPane2;
	private LoginImage loginViewImage;
	private int rowNum;
	private int col;
	private String readingroomCheck;
	private String seatLocation;
	private char row;
	///////////////////////////////////////////
	private static JTextArea loginTA;
	private static JLabel loginLId;
	private static JTextField loginTFId; // ��¿�� ����
	private static JLabel loginLPsw;
	private static JTextField loginTFPsw;
	private static JButton loginButton;
	private static JButton joinButton;
	private static JButton logoutButton;
	private static JButton extensionButton;
	private static JButton outButton;
	private static JButton moveButton;
	private static int remainHour = 0;
	private static int remainMinute = 0;
	private static int nowHour = 0;
	private static int nowMinute = 0;
	private static int nowSecond = 0;
	private static String exRemainTime = "";
	private static String etcTime = "";

	////////////////////////////////////////////
	JLayeredPane CreateLoginView() {
		loginLayeredPane2 = new JLayeredPane();
		loginLayeredPane2.setBounds(0, 0, 350, 800);
		loginLayeredPane2.setLayout(null);
		//
		loginViewImage = new LoginImage();
		loginViewImage.setBounds(0, 0, 350, 800);
		//
		loginTA = new JTextArea();
		loginTA.setBounds(10, 200, 320, 220);
		loginTA.setForeground(Color.white);
		loginTA.setOpaque(false);
		//
		loginLId = new JLabel("ID :");
		loginLId.setBounds(45, 250, 40, 20);
		loginLId.setForeground(Color.white);
		loginLPsw = new JLabel("PW :");
		loginLPsw.setBounds(40, 300, 40, 20);
		loginLPsw.setForeground(Color.white);
		loginTFId = new JTextField(20);
		loginTFId.setBounds(70, 250, 200, 20);
		loginTFPsw = new JTextField(20);
		loginTFPsw.setBounds(70, 300, 200, 20);
		//
		loginButton = new JButton(new ImageIcon("image/login.jpg"));
		loginButton.setBounds(65, 350, 100, 30);
		joinButton = new JButton(new ImageIcon("image/join.jpg"));
		joinButton.setBounds(170, 350, 100, 30);
		logoutButton = new JButton(new ImageIcon("image/logout.jpg"));
		logoutButton.setBounds(5, 450, 80, 30);
		extensionButton = new JButton(new ImageIcon("image/extension.jpg"));
		extensionButton.setBounds(90, 450, 80, 30);
		outButton = new JButton(new ImageIcon("image/checkout.jpg"));
		outButton.setBounds(175, 450, 80, 30);
		moveButton = new JButton(new ImageIcon("image/move.jpg"));
		moveButton.setBounds(260, 450, 80, 30);
		//
		loginButton.setActionCommand("�α���");
		joinButton.setActionCommand("ȸ������");
		logoutButton.setActionCommand("�α׾ƿ�");
		extensionButton.setActionCommand("����");
		outButton.setActionCommand("���");
		moveButton.setActionCommand("�̵�");
		//
		loginButton.addActionListener(this);
		joinButton.addActionListener(this);
		logoutButton.addActionListener(this);
		extensionButton.addActionListener(this);
		outButton.addActionListener(this);
		moveButton.addActionListener(this);
		//
		loginLayeredPane2.add(loginTA);
		loginLayeredPane2.add(loginLId);
		loginLayeredPane2.add(loginLPsw);
		loginLayeredPane2.add(loginTFId);
		loginLayeredPane2.add(loginTFPsw);
		loginLayeredPane2.add(loginButton);
		loginLayeredPane2.add(joinButton);
		loginLayeredPane2.add(logoutButton);
		loginLayeredPane2.add(extensionButton);
		loginLayeredPane2.add(outButton);
		loginLayeredPane2.add(moveButton);
		loginLayeredPane2.add(loginViewImage);
		// �̹����� �������� �߰��ؾ� ��ġ�� ��ư,�ؽ�Ʈ���� �Ȱ�����
		//
		loginLayeredPane2.setOpaque(false);// �̹����� �������Ƿ� �г��� ����� �������� �����Ѵ�.
		loginTA.setVisible(false);// �α���ȭ�� ��ȯ�� ���� �α��� ������ ��ġ�Ǵ� ��ư���� �̸� ����д�.
		logoutButton.setVisible(false);
		extensionButton.setVisible(false);
		outButton.setVisible(false);
		moveButton.setVisible(false);
		//
		return loginLayeredPane2;
	}

	public void loginViewConvert(String Id, int check) {
		LoginView.loginLId.setVisible(false);
		LoginView.loginLPsw.setVisible(false);
		LoginView.loginTFId.setVisible(false);
		LoginView.loginTFPsw.setVisible(false);
		LoginView.loginButton.setVisible(false);
		LoginView.joinButton.setVisible(false);
		LoginView.logoutButton.setVisible(true);
		LoginView.extensionButton.setVisible(true);
		LoginView.outButton.setVisible(true);
		LoginView.moveButton.setVisible(true);
		LoginView.loginTA.setVisible(true);
		if (check == 1) {
			LoginView.loginTA.setText("\t" + Id + " ȸ���� �湮�� ȯ���մϴ�.");
		}

	}

	public void loginViewReset() {
		LoginView.loginTA.setVisible(false);
		LoginView.outButton.setVisible(false);
		LoginView.extensionButton.setVisible(false);
		LoginView.logoutButton.setVisible(false);
		LoginView.moveButton.setVisible(false);
		LoginView.loginLId.setVisible(true);
		LoginView.loginTFId.setVisible(true);
		LoginView.loginTFId.setText("");
		LoginView.loginLPsw.setVisible(true);
		LoginView.loginTFPsw.setVisible(true);
		LoginView.loginTFPsw.setText("");
		LoginView.joinButton.setVisible(true);
		LoginView.loginButton.setVisible(true);
	}

	public void ShowFailMessage() {
		JOptionPane.showMessageDialog(null, "���� ���� ���� ȸ���Դϴ�.");
	}

	public void SeatInfoView(String Id, String readingRoom, String seatLocation, String nowTime, String endTime,
			int ExtensionNum) {
		LoginView.loginTA.setText("\n\n    " + Id + " ȸ���� �ȳ��ϼ���.\n\n    �¼� : " + readingRoom + " " + seatLocation
				+ "\n\n    �Խǽð� : " + nowTime + "\n\n    ��ǿ����ð� : " + endTime + "\n\n    ����Ƚ�� : " + ExtensionNum);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		switch (command) {
		case "�α���": {
			model.login(loginTFId.getText(), loginTFPsw.getText());
			break;
		}
		case "ȸ������": {
			joinDialog.setVisible(true);
			break;
		}
		case "�α׾ƿ�": {
			model.LoginUserUpdate();
			JOptionPane.showMessageDialog(null, "�α׾ƿ��մϴ�.");
			this.loginViewReset();
			break;
		}
		case "���": {
			CheckOut();
			// JOptionPane.showMessageDialog(null, "����մϴ�.");
			break;
		}
		case "����": {
			ExtendOutTime();
			break;
		}
		case "�̵�": {
			MoveRight();
			break;
		}

		}
	}

	private void ExtendOutTime() {
		{

			int i = 0;
			Calendar nowTime = Calendar.getInstance();
			String outTime = model.userExTimeCheck();

			try {
				StringTokenizer st = new StringTokenizer(outTime, "�ú���");
				if (this.model.userSeatCheck() == false) {
					JOptionPane.showMessageDialog(null, "�¼������� �����ϴ�. �¼������� �� �� ������ �����մϴ�.");
					return;
				} else {
					while (st.hasMoreTokens()) {
						if (i == 0) {
							nowHour = Integer.parseInt(st.nextToken());
							i++;
						} else if (i == 1) {
							nowMinute = Integer.parseInt(st.nextToken());
							i++;
						} else {
							nowSecond = Integer.parseInt(st.nextToken());
							i++;
						}

					}
					// ������ �ϰ� �Ǹ� ���� ��ǽð��� +2 �ð��� ���Ѵ�.
					// �ּ��� �����̰� ���� �� �׽�Ʈ
					// LoginView.remainHour = LoginView.nowHour -
					// (nowTime.get(Calendar.HOUR));
					// if (LoginView.remainHour > 1) {
					// JOptionPane.showMessageDialog(null, "������ ��ǿ����ð� 1�ð�������
					// �����մϴ�.");
					// } else if (LoginView.remainHour <= 1) {
					// // ����� �ʿ��� ���̾�α� �ڽ��� ������ ���´�.
					// this.model.extensionInfo();
					// }
					LoginView.remainMinute = LoginView.nowMinute - (nowTime.get(Calendar.MINUTE));
					if (LoginView.remainMinute > 1) {
						JOptionPane.showMessageDialog(null, "������ ��ǿ����ð� 1�ð������� �����մϴ�.");
					} else if (LoginView.remainMinute <= 1) {
						// ���� �ð� 20�� �ø���.
						LoginView.nowSecond += 20;
						if (LoginView.nowSecond > 59) {
							LoginView.nowMinute += 1;
							LoginView.nowSecond -= 60;
							LoginView.exRemainTime = LoginView.nowHour + "��" + LoginView.nowMinute + "��"
									+ LoginView.nowSecond + "��";
						}
						if (LoginView.nowMinute > 59) {
							LoginView.nowHour += 1;
							LoginView.nowMinute -= 60;
							LoginView.exRemainTime = LoginView.nowHour + "��" + LoginView.nowMinute + "��"
									+ LoginView.nowSecond + "��";
						}
						if (LoginView.nowHour > 23) {
							LoginView.nowHour -= 24;
							LoginView.exRemainTime = LoginView.nowHour + "��" + LoginView.nowMinute + "��"
									+ LoginView.nowSecond + "��";
						} else {
							LoginView.exRemainTime = LoginView.nowHour + "��" + LoginView.nowMinute + "��"
									+ LoginView.nowSecond + "��";
						}

						// ����� �ʿ��� ���̾�α� �ڽ��� ������ ���´�.
						this.model.extensionInfo();
					}
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "���� ���� �¼��� �����ϴ�.");
				return;
			}

		}
	}

	public void extensionInfoView(String readingRoom, String seatLocation, String nowTime, String endTime,
			int ExtensionNum) {

		String[] str = { "����", "���" };

		// int choice = JOptionPane.showOptionDialog(null,
		// "���� �Ͻðڽ��ϱ�??\n�¼�:" + readingRoom + seatLocation + "\n�Խǽð�:" + nowTime
		// + "\n ��ǿ����ð� : " + exRemainTime
		// + "\n ����Ƚ�� :" + ExtensionNum + "\n*��� ������ ��ǽð� 1�� ������ ����\n ����� ��ǽð�
		// 2�ð� ����",
		// "����", JOptionPane.YES_NO_CANCEL_OPTION,
		// JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
		// ���� �ּ��� �����̰� �Ʒ��� �׽�Ʈ 2�� ����
		int choice = JOptionPane.showOptionDialog(null,
				"���� �Ͻðڽ��ϱ�??\n�¼�:" + readingRoom + seatLocation + "\n�Խǽð�:" + nowTime + "\n ��ǿ����ð� : " + exRemainTime
						+ "\n ����Ƚ�� :" + ExtensionNum + "\n*��� ������ ��ǽð� 1�� ������ ����\n ����� ��ǽð� 2�� ����",
				"����", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
		if (choice == JOptionPane.YES_OPTION) {
			if (this.model.userExCount() > 2) {
				JOptionPane.showMessageDialog(null, "������ 3ȸ������ �����մϴ�.(" + this.model.userExCount() + "/3)");
			}
			String extensionHour = exRemainTime;
			// ������Ʈ Ŭ�������� �α����� ������ ����Ƚ���� ������Ʈ �Ѵ�. +1
			this.model.userExCountUp(extensionHour);
			int extensionNum = this.model.userExCount();
			JOptionPane.showMessageDialog(null, "����" + extensionNum + "ȸ  �ϼ̽��ϴ�. (" + extensionNum + "/3)");

			// TextArea�� ������ ������Ʈ �Ѵ�.
			this.model.extensionTaReLoad();
		} else {
			return;
		}
	}

	public void CheckOut() {
		model = new UpdateModel();
		// üũ ���� 1�̸� �¼� �̻��
		if (model.LoginCheck() == 1) {
			JOptionPane.showMessageDialog(null, "�������� �¼��� �����Ƿ� �α׾ƿ��մϴ�.");
			this.loginViewReset();
		} else if (model.LoginCheck() == 2) {
			// ����� �ؾ��� ������ ���� �¼��� ��ġ�� �������� �޼���
			setSeatLocation();
			// ����ϴ� ������ �¼��� ���� ������ ��� �����Ѵ�.
			model.UserInfoDel();
			// �������ǿ� ���� ���°��� �޾� �¼��� �ʱ�ȭ�Ѵ�. 1�������� : 1 <> 2������ : 2
			if (readingroomCheck.equals("1������")) {
				model.SeatInit(rowNum, (col - 1), row, 1);
				JOptionPane.showMessageDialog(null, "��� �Ͽ����ϴ�.");
				model.setSeatCountUp1();
			} else if (readingroomCheck.equals("2������")) {
				model.SeatInit(rowNum, (col - 1), row, 2);
				JOptionPane.showMessageDialog(null, "��� �Ͽ����ϴ�.");
				model.setSeatCountUp2();
			}

			model.seatMoveStateFalse();
			this.loginViewReset();

		}
	}

	private void setSeatLocation() {

		// ���ڿ��� ����Ǿ��ִ� �����ǰ� �¼� ��ġ�� �� �� �ε��� ������ ��ȯ�ϴ� �޼���
		this.readingroomCheck = model.CheckOutInfoRoom();
		this.seatLocation = model.CheckOutInfoLocation();

		this.row = seatLocation.charAt(0);// A,B,C,D....
		this.col = Integer.parseInt(seatLocation.charAt(2) + "");// 1��,2��....
		if (row == 'A') {
			this.rowNum = 0;
		} else if (row == 'B') {
			this.rowNum = 1;
		} else if (row == 'C') {
			this.rowNum = 2;
		} else if (row == 'D') {
			this.rowNum = 3;
		} else if (row == 'E') {
			this.rowNum = 4;
		} else if (row == 'F') {
			this.rowNum = 5;
		}
	}

	private void MoveRight() {

		// üũ ���� 1�̸� �¼� �̻��
		if ((this.model.LoginCheck() == 1)) {
			JOptionPane.showMessageDialog(null, "�������� �¼��� ������ �¼��� �����ϼ���.");
		} else if ((this.model.LoginCheck() == 2)) {
			JOptionPane.showMessageDialog(null, "�̵��� �¼��� �����Ͽ� �ֽʽÿ�.");
			// �̵��� �ϱ����ؼ� �̿����� �¼� �̿뿩�θ� �����Ѵ�.
			this.model.SeatUserUpdateFalse();
			// �̵��ϴ� ������ �¼��� ���� ������ ��� �����Ѵ�.
			this.model.seatMoveStateTrue();
		}
	}

	class LoginImage extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image img = null;

		@Override
		public void paint(Graphics g) {
			try {
				img = ImageIO.read(new File("image/LeftPan_Test2.png"));
			} catch (IOException e) {
				System.out.println("�̹��� �ҷ����� ����");
				System.exit(0);
			}
			g.drawImage(img, -20, 50, this);
		}

	}

}