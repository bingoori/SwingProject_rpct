package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.UpdateModel;

public final class SeatView1 extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int totalCount = 72;
	protected UpdateModel model = new UpdateModel();
	private JLayeredPane seatViewLayered1 = new JLayeredPane();
	private JPanel[][] seatBaseImage = new JPanel[6][12];// 좌석의 백그라운드 이미지가 들어가는
															// 부분
	private static JLabel[][] seatText = new JLabel[6][12];// 각 좌석의 텍스트 들어가는 부분
	private SeatBackImage seatBackImage = new SeatBackImage();
	private static UsedSeatImage[][] usedSeatImage = new UsedSeatImage[6][12];
	private static SeatImage[][] seatImage = new SeatImage[6][12];
	////////////////////////////////////////
	// 시간 체크 관련 속성들
	private Calendar timeCheck;
	private static String nowTime;
	private static String endTime;
	private static int ExtensionNum = 0;// 연장횟수
	////////////////////////////////////////
	// Thread 부분
	private JLabel seatInfo = new JLabel(); // 텍스트로 남은 좌석 보여주기 위한 라벨
	SeatCountThread sct = new SeatCountThread(seatInfo);

	//////////////////////////////////

	JLayeredPane CreateSeatView1() {
		seatViewLayered1.setSize(1000, 750);
		seatViewLayered1.setLayout(null);
		seatInfo.setBounds(700, -220, 500, 500);
		seatInfo.setFont(new Font(null, 0, 20));
		seatInfo.setForeground(Color.black);
		sct.start();
		seatViewLayered1.add(seatInfo);
		int row = 15;
		int col = 65;
		char rr = 'A';

		for (int i = 0; i < 6; i++) {

			if (i == 2 || i == 4) {
				col += 35;
			}
			for (int j = 0; j < 12; j++) {
				seatBaseImage[i][j] = new JPanel();
				seatImage[i][j] = new SeatImage();
				usedSeatImage[i][j] = new UsedSeatImage();
				seatBaseImage[i][j].setLayout(null);
				if (j == 0) {
					seatBaseImage[i][j].setBounds(row, col, 70, 70);

				} else {
					row += 80;
					seatBaseImage[i][j].setBounds(row, col, 70, 70);
				}
				seatText[i][j] = new JLabel(rr + "열" + (j + 1) + "석");
				// 라벨에 좌석의 위치를 지정한다.
				seatText[i][j].setFont(new Font(null, 0, 10));// 글씨크기
				// 폰트 나 글시크기 변경시 사용
				seatText[i][j].setBounds(10, 2, 60, 15); // 텍스트 넣기
				seatText[i][j].setForeground(Color.white);

				seatImage[i][j].setBounds(0, 0, 70, 70);
				seatImage[i][j].setOpaque(true);

				usedSeatImage[i][j].setBounds(0, 0, 70, 70);
				usedSeatImage[i][j].setOpaque(true);
				usedSeatImage[i][j].setVisible(false);

				seatBaseImage[i][j].add(seatText[i][j]);
				seatBaseImage[i][j].add(seatImage[i][j]);
				seatBaseImage[i][j].add(usedSeatImage[i][j]);

				seatBaseImage[i][j].addMouseListener(new EventHandler(i, j));
				seatBaseImage[i][j].setOpaque(false);
				seatBaseImage[i][j].setVisible(true);

				add(seatBaseImage[i][j]);
				seatViewLayered1.add(seatBaseImage[i][j]);

			}
			rr++;
			col += 80;
			row = 15;
		}
		seatBackImage.setBounds(0, 0, 1050, 1500);
		seatBackImage.setOpaque(false);

		seatViewLayered1.add(seatBackImage);

		return seatViewLayered1;
	}

	class EventHandler implements MouseListener {
		int i, j = 0;

		public EventHandler(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			try {
				System.out.println(model.getMoveState());
				if (model.LoginCheck() == 1 && model.getMoveState() == false) {
					SeatAssign();
				} else if (model.getMoveState() == true) {
					SeatMove();
				}
			} catch (ArrayIndexOutOfBoundsException e2) {
				JOptionPane.showMessageDialog(null, "로그인을 하시고 좌석을 선택하세요.");
			}

		}

		private void SeatAssign() {
			String[] str = { "입실", "취소" };
			String seatLocation = seatText[i][j].getText();
			String readingRoom = model.getRoomName();
			timeCheck();// 현재 시간, 종료시간값을 받아온다
			int choice = JOptionPane.showOptionDialog(null,
					"입실을 하시겠습니까?\n 좌석:" + readingRoom + seatLocation + "\n 입실시간:" + nowTime + "\n 퇴실예정시간:" + endTime
							+ "\n *퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
					"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if (choice == JOptionPane.YES_OPTION) {

				// 기존의 텍스트와 이미지를 변경한다.
				seatText[i][j].setText("좌석 사용중..");
				seatText[i][j].setLocation(5, 26);
				seatImage[i][j].setVisible(false);
				usedSeatImage[i][j].setVisible(true);
				// 중복 선택 방지를 위해 회원의 좌석 사용 여부를 업데이트 하고, 이동 금지 상태값을 재업데이트한다.
				model.SeatUserUpdateTrue();
				model.seatMoveStateFalse();
				// 로그인 화면에서 좌석 정보를 출력하기 위해 좌석 정보를 업데이트 클래스에 업데이트 한다.
				model.SeatInfoUpdate(nowTime, endTime, ExtensionNum, seatLocation, readingRoom);
				model.setSeatCountDo1();
			} else if (choice == JOptionPane.NO_OPTION) {
				return;
			}
		}

		private void SeatMove() {
			String[] str = { "입실", "취소" };
			String move_SeatLocation = seatText[i][j].getText();

			// 현재 유저의 기존 데이터를 가져온다.
			int ExtensionNum = model.userExCount();
			// 현재 로그인한 유저의 기존 열람실명을 저장
			String original_ReadingRoom = model.CheckOutInfoRoom();
			// 현재 로그인한 유저의 기존 좌석 위치를 저장
			String original_SeatLocation = model.CheckOutInfoLocation();
			// 이동 할 열람실명을 가지고온다.
			String move_readingRoom = model.getRoomName();
			// 현재 유저의 입실, 퇴실 시간 정보를 가져온다.
			String nowTime = model.userInTimeCheck();
			String endTime = model.userExTimeCheck();
			//
			int choice = JOptionPane.showOptionDialog(null,
					" 이동을 하시겠습니까?\n 현재좌석: " + original_ReadingRoom + original_SeatLocation + "\n 이동좌석: "
							+ move_readingRoom + move_SeatLocation + "\n 입실시간: " + nowTime + "\n 퇴실예정시간: " + endTime
							+ "\n *퇴실 연장은 퇴실시간 1시간 전부터 가능\n",
					"선택", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
			if (choice == JOptionPane.YES_OPTION) {

				char row = original_SeatLocation.charAt(0);// A,B,C,D....
				int col = Integer.parseInt(original_SeatLocation.charAt(2) + "");// 1열,2열....
				int rowNum = 0;
				if (row == 'A')
					rowNum = 0;
				else if (row == 'B')
					rowNum = 1;
				else if (row == 'C')
					rowNum = 2;
				else if (row == 'D')
					rowNum = 3;
				else if (row == 'E')
					rowNum = 4;
				else if (row == 'F')
					rowNum = 5;

				// 유저의 좌석에 대한 정보를 모두 제거한다.
				model.UserInfoDel();
				// 선택한 좌석의 라벨의 텍스트를 변경한다.
				seatText[i][j].setText("좌석 사용중..");
				seatText[i][j].setLocation(1, 26);

				seatImage[i][j].setVisible(false);
				usedSeatImage[i][j].setVisible(true);

				// 각열람실에 대한 상태값을 받아 좌석을 초기화한다. 1열람실은 : 1 <> 2열람실 : 2
				if (original_ReadingRoom.equals("1열람실")) {
					model.SeatInit(rowNum, (col - 1), row, 1);

				} else if (original_ReadingRoom.equals("2열람실")) {
					model.SeatInit(rowNum, (col - 1), row, 2);
					model.setSeatCountDo1();
					model.setSeatCountUp2();
				}
				// 유저의 데이터를 새로 업데이트 한다.
				model.SeatInfoUpdate(nowTime, endTime, ExtensionNum, move_SeatLocation, move_readingRoom);
				// 좌석 사용 상태값을 이용중으로 true로 바꾼다.
				model.SeatUserUpdateTrue();
				// 좌석 이동 완료후 이동 상태값을 false로 바꾼다.
				model.seatMoveStateFalse();
				// 로그인 상태를 유지한다.
				model.userLoginTrue();
			} else if (choice == JOptionPane.NO_OPTION) {
				model.seatMoveStateFalse();
				JOptionPane.showMessageDialog(null, "다시 이동을 원하면 이동 버튼을 다시 누르세요");
			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void timeCheck() {
		timeCheck = Calendar.getInstance(); // 재생성 해야 새로운 시간이 저장됨
		int hour = timeCheck.get(Calendar.HOUR);
		int minute = timeCheck.get(Calendar.MINUTE);
		int second = (timeCheck.get(Calendar.SECOND) + 20);
		

		this.nowTime = (timeCheck.get(Calendar.HOUR)) + "시" + timeCheck.get(Calendar.MINUTE) + "분"
				+ timeCheck.get(Calendar.SECOND) + "초";

		if (second > 59) {
			minute += 1;
			second -= 60;
			this.endTime = hour + "시" + minute + "분" + second + "초";
		}
		if (minute > 59) {
			hour += 1;
			minute -= 60;
			this.endTime = hour + "시" + minute + "분" + second + "초";
		}
		if (hour > 23) {
			hour -= 24;
			this.endTime = hour + "시" + minute + "분" + second + "초";
		}
		else {
			this.endTime = hour + "시" + minute + "분" + second + "초";
		}

	}

	public void SeatInit(int rowNum, int col, char row) {

		usedSeatImage[rowNum][col].setVisible(false);
		seatImage[rowNum][col].setVisible(true);
		seatText[rowNum][col].setText(row + "열" + (col + 1) + "석");
		seatText[rowNum][col].setBounds(10, 2, 60, 15);

	}

	class SeatBackImage extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image img = null; // 이미지아직 안넣음

		@Override
		public void paint(Graphics g) {
			try {
				img = ImageIO.read(new File("image/white33.jpg"));
			} catch (IOException e) {
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}

			g.drawImage(img, 0, 0, 1050, 1500, null, this);
		}
	}

	class SeatImage extends JPanel {

		private static final long serialVersionUID = 1L;
		Image img = null;

		@Override
		public void paint(Graphics g) {
			try {
				img = ImageIO.read(new File("image/seat.png"));
			} catch (IOException e) {
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}

			g.drawImage(img, 0, 0, 70, 70, null, this);
		}
	}

	class UsedSeatImage extends JPanel {
		/**
		 * y
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image img = null; // 이미지아직 안넣음

		@Override
		public void paint(Graphics g) {
			try {
				img = ImageIO.read(new File("image/seat2.png"));
			} catch (IOException e) {
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}

			g.drawImage(img, 0, 0, 70, 70, null, this);
		}
	}

	class SeatCountThread extends Thread {
		private JLabel SeatInfo;
		private Calendar ThreadTime;
		private String ThreadTimeCheck = "";
		private Vector[] vMemberList;
		private int index = 0;

		SeatCountThread(JLabel seatInfo) {
			this.SeatInfo = seatInfo;
		}

		@Override
		public void run() {
			while (true) {
				ThreadTime = Calendar.getInstance();
				ThreadTimeCheck = (ThreadTime.get(Calendar.HOUR)) + "시" + ThreadTime.get(Calendar.MINUTE) + "분"
						+ ThreadTime.get(Calendar.SECOND) + "초";

				try {

					Thread.sleep(700);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}

				SeatInfo.setText("[ 남은 좌석:" + model.getSeatCount1() + "  /  총 좌석 :" + totalCount + " ]");
				vMemberList = model.getManagerMemberList();
				index = model.getManagerMemberIndex();

				for (int i = 0; i < index; i++) {
					if (vMemberList[i] != null) {

						if (ThreadTimeCheck.equals(vMemberList[i].get(7))) {
							if("1열람실".equals(vMemberList[i].get(10)))
							{
								model.setSeatCountUp1();
							}else if("2열람실".equals(vMemberList[i].get(10)))
							{
								model.setSeatCountUp2();
							}
							model.functionManager((String) vMemberList[i].get(0));
							
			
						}

					} else {
						System.out.println(ThreadTimeCheck);
						System.out.println(index);
					}
				}

			}

		}
	}
}
