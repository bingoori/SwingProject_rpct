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
import javax.swing.JPasswordField;
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
	private static JTextField loginTFId; // 어쩔수 없음
	private static JLabel loginLPsw;
	private static JPasswordField loginTFPsw;
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
		loginTFPsw = new JPasswordField(20);
		loginTFPsw.setBounds(70, 300, 200, 20);
		loginTFPsw.setEchoChar('*');
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
		loginButton.setActionCommand("로그인");
		joinButton.setActionCommand("회원가입");
		logoutButton.setActionCommand("로그아웃");
		extensionButton.setActionCommand("연장");
		outButton.setActionCommand("퇴실");
		moveButton.setActionCommand("이동");
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
		// 이미지는 마지막에 추가해야 배치된 버튼,텍스트들이 안가려짐
		//
		loginLayeredPane2.setOpaque(false);// 이미지를 덮었으므로 패널의 배경은 투명으로 설정한다.
		loginTA.setVisible(false);// 로그인화면 변환을 위해 로그인 이후의 배치되는 버튼들은 미리 감춰둔다.
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
			LoginView.loginTA.setText("\t" + Id + " 회원님 방문을 환영합니다.");
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
		JOptionPane.showMessageDialog(null, "가입 되지 않은 회원입니다.");
	}

	public void SeatInfoView(String Id, String readingRoom, String seatLocation, String nowTime, String endTime,
			int ExtensionNum) {
		LoginView.loginTA.setText("\n\n    " + Id + " 회원님 안녕하세요.\n\n    좌석 : " + readingRoom + " " + seatLocation
				+ "\n\n    입실시간 : " + nowTime + "\n\n    퇴실예정시간 : " + endTime + "\n\n    연장횟수 : " + ExtensionNum);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		switch (command) {
		case "로그인": {
			String Psw = "";
			for (char pass : loginTFPsw.getPassword()) {
				Psw += pass;
			}
			model.login(loginTFId.getText(), Psw);
			break;
		}
		case "회원가입": {
			joinDialog.setVisible(true);
			break;
		}
		case "로그아웃": {
			model.LoginUserUpdate();
			JOptionPane.showMessageDialog(null, "로그아웃합니다.");
			this.loginViewReset();
			break;
		}
		case "퇴실": {
			CheckOut();
			// JOptionPane.showMessageDialog(null, "퇴실합니다.");
			break;
		}
		case "연장": {
			ExtendOutTime();
			break;
		}
		case "이동": {
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
				StringTokenizer st = new StringTokenizer(outTime, "시분초");
				if (this.model.userSeatCheck() == false) {
					JOptionPane.showMessageDialog(null, "좌석정보가 없습니다. 좌석배정을 한 후 연장이 가능합니다.");
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
					// 연장을 하게 되면 현재 퇴실시간에 +2 시간을 더한다.
					// 주석이 원본이고 지금 건 테스트
					// LoginView.remainHour = LoginView.nowHour -
					// (nowTime.get(Calendar.HOUR));
					// if (LoginView.remainHour > 1) {
					// JOptionPane.showMessageDialog(null, "연장은 퇴실예정시간 1시간전부터
					// 가능합니다.");
					// } else if (LoginView.remainHour <= 1) {
					// // 연장시 필요한 다이얼로그 박스의 정보를 얻어온다.
					// this.model.extensionInfo();
					// }
					LoginView.remainMinute = LoginView.nowMinute - (nowTime.get(Calendar.MINUTE));
					if (LoginView.remainMinute > 1) {
						JOptionPane.showMessageDialog(null, "연장은 퇴실예정시간 1시간전부터 가능합니다.");
					} else if (LoginView.remainMinute <= 1) {
						// 연장 시간 20초 늘린다.
						LoginView.nowSecond += 20;
						if (LoginView.nowSecond > 59) {
							LoginView.nowMinute += 1;
							LoginView.nowSecond -= 60;
							LoginView.exRemainTime = LoginView.nowHour + "시" + LoginView.nowMinute + "분"
									+ LoginView.nowSecond + "초";
						}
						if (LoginView.nowMinute > 59) {
							LoginView.nowHour += 1;
							LoginView.nowMinute -= 60;
							LoginView.exRemainTime = LoginView.nowHour + "시" + LoginView.nowMinute + "분"
									+ LoginView.nowSecond + "초";
						}
						if (LoginView.nowHour > 23) {
							LoginView.nowHour -= 24;
							LoginView.exRemainTime = LoginView.nowHour + "시" + LoginView.nowMinute + "분"
									+ LoginView.nowSecond + "초";
						} else {
							LoginView.exRemainTime = LoginView.nowHour + "시" + LoginView.nowMinute + "분"
									+ LoginView.nowSecond + "초";
						}

						// 연장시 필요한 다이얼로그 박스의 정보를 얻어온다.
						this.model.extensionInfo();
					}
				}
			} catch (NullPointerException e) {
				JOptionPane.showMessageDialog(null, "배정 받은 좌석이 없습니다.");
				return;
			}

		}
	}

	public void extensionInfoView(String readingRoom, String seatLocation, String nowTime, String endTime,
			int ExtensionNum) {

		String[] str = { "연장", "취소" };

		// int choice = JOptionPane.showOptionDialog(null,
		// "연장 하시겠습니까??\n좌석:" + readingRoom + seatLocation + "\n입실시간:" + nowTime
		// + "\n 퇴실예정시간 : " + exRemainTime
		// + "\n 연장횟수 :" + ExtensionNum + "\n*퇴실 연장은 퇴실시간 1분 전부터 가능\n 연장시 퇴실시간
		// 2시간 연장",
		// "선택", JOptionPane.YES_NO_CANCEL_OPTION,
		// JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
		// 위의 주석이 원본이고 아래는 테스트 2분 증가
		int choice = JOptionPane.showOptionDialog(null,
				"연장 하시겠습니까??\n좌석:" + readingRoom + seatLocation + "\n입실시간:" + nowTime + "\n 퇴실예정시간 : " + exRemainTime
						+ "\n 연장횟수 :" + ExtensionNum + "\n*퇴실 연장은 퇴실시간 1분 전부터 가능\n 연장시 퇴실시간 2분 연장",
				"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
		if (choice == JOptionPane.YES_OPTION) {
			if (this.model.userExCount() > 2) {
				JOptionPane.showMessageDialog(null, "연장은 3회까지만 가능합니다.(" + this.model.userExCount() + "/3)");
			}
			String extensionHour = exRemainTime;
			// 업데이트 클래스에서 로그인한 유저의 연장횟수를 업데이트 한다. +1
			this.model.userExCountUp(extensionHour);
			int extensionNum = this.model.userExCount();
			JOptionPane.showMessageDialog(null, "연장" + extensionNum + "회  하셨습니다. (" + extensionNum + "/3)");

			// TextArea의 정보를 업데이트 한다.
			this.model.extensionTaReLoad();
		} else {
			return;
		}
	}

	public void CheckOut() {
		model = new UpdateModel();
		// 체크 값이 1이면 좌석 미사용
		if (model.LoginCheck() == 1) {
			JOptionPane.showMessageDialog(null, "배정받은 좌석이 없으므로 로그아웃합니다.");
			this.loginViewReset();
		} else if (model.LoginCheck() == 2) {
			// 퇴실을 해야할 열람실 명과 좌석의 위치를 가져오는 메서드
			setSeatLocation();
			// 퇴실하는 유저의 좌석에 대한 정보를 모두 제거한다.
			model.UserInfoDel();
			// 각열람실에 대한 상태값을 받아 좌석을 초기화한다. 1열람실은 : 1 <> 2열람실 : 2
			if (readingroomCheck.equals("1열람실")) {
				model.SeatInit(rowNum, (col - 1), row, 1);
				JOptionPane.showMessageDialog(null, "퇴실 하였습니다.");
				model.setSeatCountUp1();
			} else if (readingroomCheck.equals("2열람실")) {
				model.SeatInit(rowNum, (col - 1), row, 2);
				JOptionPane.showMessageDialog(null, "퇴실 하였습니다.");
				model.setSeatCountUp2();
			}

			model.seatMoveStateFalse();
			this.loginViewReset();

		}
	}

	private void setSeatLocation() {

		// 문자열로 저장되어있는 열람실과 좌석 위치를 행 열 인덱스 값으로 변환하는 메서드
		this.readingroomCheck = model.CheckOutInfoRoom();
		this.seatLocation = model.CheckOutInfoLocation();

		this.row = seatLocation.charAt(0);// A,B,C,D....
		this.col = Integer.parseInt(seatLocation.charAt(2) + "");// 1열,2열....
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

		// 체크 값이 1이면 좌석 미사용
		if ((this.model.LoginCheck() == 1)) {
			JOptionPane.showMessageDialog(null, "배정받은 좌석이 없으니 좌석을 선택하세요.");
		} else if ((this.model.LoginCheck() == 2)) {
			JOptionPane.showMessageDialog(null, "이동할 좌석을 선택하여 주십시오.");
			// 이동을 하기위해서 이용자의 좌석 이용여부를 변경한다.
			this.model.SeatUserUpdateFalse();
			// 이동하는 유저의 좌석에 대한 정보를 모두 제거한다.
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
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}
			g.drawImage(img, -20, 50, this);
		}

	}

}
