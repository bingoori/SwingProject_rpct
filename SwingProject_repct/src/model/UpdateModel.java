package model;

import java.util.Vector;

import javax.swing.JOptionPane;

import IO.DataIOClass;
import view.JoinView;
import view.LoginView;
import view.ManagerView;
import view.SeatView1;
import view.SeatView2;

public class UpdateModel {
	// -------------------���� ����(Ŭ����)----------------------------------------
	private DataIOClass dataIOClass;
	// ----------------------------------------------------------------------
	// ------------------ManagerView(������ ��) -------------------------------
	private ManagerView managerView;
	// ---------------------------------------------------------------------
	// ------------------SeatTabView(�¼� ��) ������Ʈ �����-----------------------
	private static String curPaneTitle;
	// ---------------------------------------------------------------------
	// ------------------JoinView(ȸ������â) ȸ����� ������Ʈ�� ���� �����--------------
	private JoinView joinView;
	// ȸ�� 200���� �̿��� �� �ִ� �����̾� ������
	private static Vector[] vMemberList = new Vector[200];
	private static Vector<String> vMemberCheckList = new Vector<String>();
	private static int index = 0; // ������� ����Ʈ�� ȸ���� �ֱ����� ����
	// --------------------------------------------------------------------
	// ------------------LoginView(�α���â) ������Ʈ�� ���� �����--------------------
	private LoginView loginView;
	private int memberIndex = 0; // ȸ���� ����� ����Ʈ�� �ִ��� �� �񱳸� ���� �ε���
	// ---------------------------------------------------------------------
	// -------------- SeatView(������) ������Ʈ�� ���� �����----------------------
	private SeatView1 seatView1;
	private SeatView2 seatView2;
	private static boolean moveState = false;
	private static String accessId = "";
	private static int accessIdIndex = 0;
	private static int SeatCount1 = 72;
	private static int SeatCount2 = 72;

	// ---------------------------------------------------------------------
	// ---------------------------ManagerView------------------------------------
	public Vector[] getManagerMemberList() {
		return this.vMemberList;
	}

	public int getManagerMemberIndex() {
		return this.index;
	}

	public void functionManager(String delId) {

		setMemberIndex(delId);
		String readingroomCheck = CheckOutInfoRoom();
		String seatLocation = CheckOutInfoLocation();
		UserInfoDel();
		char row = seatLocation.charAt(0);// A,B,C,D....
		int col = Integer.parseInt(seatLocation.charAt(2) + "");// 1��,2��....
		int rowNum = 0;
		if (row == 'A') {
			rowNum = 0;
		} else if (row == 'B') {
			rowNum = 1;
		} else if (row == 'C') {
			rowNum = 2;
		} else if (row == 'D') {
			rowNum = 3;
		} else if (row == 'E') {
			rowNum = 4;
		} else if (row == 'F') {
			rowNum = 5;
		}
		// �������ǿ� ���� ���°��� �޾� �¼��� �ʱ�ȭ�Ѵ�. 1�������� : 1 <> 2������ : 2
		if (readingroomCheck.equals("1������")) {
			SeatInit(rowNum, (col - 1), row, 1);
			JOptionPane.showMessageDialog(null, "�������� �Ǿ����ϴ�.");
		} else if (readingroomCheck.equals("2������")) {
			SeatInit(rowNum, (col - 1), row, 2);
			JOptionPane.showMessageDialog(null, "�������� �Ǿ����ϴ�.");
		}
		seatMoveStateFalse();
	}

	// -------------- SeatTabView(�¼� �� ȭ��) ��� ó�� �κ�------------------------
	public void setRoomName(String roomName) {
		this.curPaneTitle = roomName;
	}

	public String getRoomName() {

		return this.curPaneTitle;
	}

	// ---------------------------------------------------------------------
	/////////////// JoinView(ȸ������â)�� ��� ó�� �κ�//////////////////////

	public void setMemberIndex(String Id) {
		// ���� ȸ�����̵� ����� ����Ʈ���� �ε��� ���� ������ �´�.
		this.memberIndex = vMemberCheckList.indexOf(Id);
		this.accessIdIndex = vMemberCheckList.indexOf(Id);
	}

	public boolean MemberOverwrapCheck(String Id) {
		boolean result = false;
		/*
		 * ���� �����ϴ� ���̵� ȸ������ �ƴ��� �ߺ� üũ�ϴ� �޼���
		 */
		if (vMemberCheckList.contains(Id)) {
			setMemberIndex(Id);
			result = true;
		} else if (!vMemberCheckList.contains(Id)) {
			result = false;
		}
		return result;
	}

	public void addIdCheckList(String Id) {
		this.vMemberCheckList.add(Id);
	}

	public void setMemberList(String Id, String Psw, String Name, String Birth, int state) {
		joinView = new JoinView();
		dataIOClass = new DataIOClass();
		boolean result = MemberOverwrapCheck(Id);
		System.out.println(state + "state");
		System.out.println(Id + " " + Psw + " " + Name + " " + Birth);
		/*
		 * JoinView(ȸ������) Ŭ�����κ��� ȸ�� ������ �޾� ����Ʈ�� �����ϴ� �޼���
		 */
		if (result == true) {
			// MemberOverwrapCheck() �޼��忡�� ���̵� �Ⱑ�������� �ƴ��� Ȯ���� �� ���� �޴´�.
			System.out.println("�̹� ���Ե� ȸ���̹Ƿ� ȸ������â(Ŭ����)���� ���� �޼����� ����մϴ�.");
			joinView.ShowFailMessage();
		} else if (result == false) {

			System.out.println("���Ե� ȸ���� �ƴϹǷ�, vMemberList -> �� �����մϴ�.");
			addIdCheckList(Id);// vMemberCheckList�� ���̵� �߰�
			// ���̵� 0 , �н����� 1 , �̸� 2, ���� 3, �α��� �̿� 4, �¼���� 5, �Խ� 6, ��� 7, ���� 8,
			// �¼���ġ 9, ������ 10, �¼� �̵� ���� 11
			vMemberList[index] = new Vector<String>();
			vMemberList[index].add(Id);// 0
			vMemberList[index].add(Psw);// 1
			vMemberList[index].add(Name);// 2
			vMemberList[index].add(Birth);// 3
			vMemberList[index].add(false);// 4 �α���
			vMemberList[index].add(false);// 5 �¼����
			vMemberList[index].add(null);// 6
			vMemberList[index].add(null);// 7
			vMemberList[index].add(null);// 8
			vMemberList[index].add(null);// 9
			vMemberList[index].add(null);// 10

			index++;

			System.out.println(" ������ ������Ʈ(����) �Ͽ���, �ε����� �������׽��ϴ�.");
			System.out.println("�׸��� ����ȸ�� ���� �ƴ��� Ȯ���� ���� vMemberCheckList�� ���̵� �����Ͽ����ϴ�.");
			if (state == 1) {
				joinView.ShowSuccessMessage();
				dataIOClass.saveMemberList(vMemberList, (index - 1));
			}
		}

	}

	/////////////////////////////////////////////////////////////////
	/////////////// LoginView(�α���â)�� ��� ó�� �κ�///////////////////////
	public int LoginCheck() {
		int result = 0;
		setMemberIndex(accessId);
		// ȸ���� �α��� �������� �Ǵ� �¼��� �̿������� ������ ���� 1�� ���� �Ѵ�.
		// ȸ���� �α��� �������� �Ǵ� �¼��� �̿��̸� ���� 2�� ���� �Ѵ�
		if (vMemberList[accessIdIndex].get(4).equals(true) && vMemberList[accessIdIndex].get(5).equals(false)) {
			result = 1;
		} else if (vMemberList[accessIdIndex].get(4).equals(true) && vMemberList[accessIdIndex].get(5).equals(true)) {
			result = 2;
		}
		return result;
	}

	public void login(String Id, String Psw) {
		// �α����ϴ� �޼���
		loginView = new LoginView();
		if (MemberOverwrapCheck(Id) == true && (vMemberList[memberIndex].get(1).equals(Psw))) {
			System.out.println("�α��� �Ǿ����ϴ�.");
			// System.out.println((String)vMemberList[accessIdIndex].get(4));
			// �¼��� ������̸�
			if (vMemberList[accessIdIndex].get(5).equals(true)) {
				vMemberList[accessIdIndex].set(4, true); // �ε��� 4�� �α��� ����
				// ���̵�,�¼���ġ,������,�Խ�,���,����Ƚ���� �信 �Ѱ���
				loginView.loginViewConvert(Id, 0);
				loginView.SeatInfoView((String) vMemberList[accessIdIndex].get(0),
						(String) vMemberList[accessIdIndex].get(10), (String) vMemberList[accessIdIndex].get(9),
						(String) vMemberList[accessIdIndex].get(6), (String) vMemberList[accessIdIndex].get(7),
						(int) vMemberList[accessIdIndex].get(8));
			} else if (vMemberList[accessIdIndex].get(5).equals(false)) {
				// ȭ�� ��ȯ�� ���� �޼���
				loginView.loginViewConvert(Id, 1);
				// �α����� ���̵�� �α��� ���°� �ǵ��� ���� �����Ѵ�.
				vMemberList[accessIdIndex].set(4, true); // �ε��� 4�� �α��� ����
			}

			// ������ ���̵� ����
			this.accessId = Id;
		} else {
			loginView.ShowFailMessage();
		}

	}

	public void LoginUserUpdate() {
		setMemberIndex(accessId);// ���� ������ ȸ���� �ε����� �������� �޼���
		vMemberList[accessIdIndex].set(4, false);
	}

	// ���� �α����� ������ �¼��� ��������� �ƴ��� Ȯ���ϴ� �޼���
	public boolean userSeatCheck() { // ���̵� 0 , �н����� 1 , �̸� 2, ���� 3, �α��� �̿� 4,
										// �¼���� 5, �Խ� 6, ��� 7, ���� 8,
										// �¼���ġ 9, ������ 10, �¼� �̵� ���� 11
		return (boolean) vMemberList[accessIdIndex].get(5);
	}

	public void userLoginTrue() {
		vMemberList[accessIdIndex].set(4, true);

		System.out.println(vMemberList[accessIdIndex].get(0) + "0");
		System.out.println(vMemberList[accessIdIndex].get(4) + "4");
	}

	// ���� �α����� ������ �Խ� �ð��� ��ȯ�ϴ� �޼���
	public String userInTimeCheck() {
		return (String) vMemberList[accessIdIndex].get(6);
	}

	// ���� �α����� ������ ��ǽð��� ��ȯ�ϴ� �޼���
	public String userExTimeCheck() {
		return (String) vMemberList[accessIdIndex].get(7);
	}

	public int userExCount() {
		// ���� �α����� ������ ���� Ƚ���� ��ȯ�ϴ� �޼���
		return (int) vMemberList[accessIdIndex].get(8);
	}

	public void userExCountUp(String extensionHour) {

		vMemberList[accessIdIndex].set(7, extensionHour);
		System.out.println(extensionHour);
		int count = (int) vMemberList[accessIdIndex].get(8);
		vMemberList[accessIdIndex].set(8, (count + 1));

	}

	public void extensionInfo() {
		// ������ ����� �ʿ��� ������ �α��� �� Ŭ������ ���� �Ѵ�.
		// ���̵� 0 , �н����� 1 , �̸� 2, ���� 3, �α��� �̿� 4, �¼���� 5, �Խ� 6, ��� 7, ���� 8,
		// �¼���ġ 9, ������ 10, �¼� �̵� ���� 11
		loginView = new LoginView();
		// ������ + �¼���ġ, �Խǽð�, ��ǽð�, ���� Ƚ�� �� �α��� �� Ŭ������ �ѱ��.

		loginView.extensionInfoView((String) vMemberList[accessIdIndex].get(10),
				(String) vMemberList[accessIdIndex].get(9), (String) vMemberList[accessIdIndex].get(6),
				(String) vMemberList[accessIdIndex].get(7), (int) vMemberList[accessIdIndex].get(8));

	}

	public void extensionTaReLoad() {
		loginView = new LoginView();
		loginView.SeatInfoView((String) vMemberList[accessIdIndex].get(0), (String) vMemberList[accessIdIndex].get(10),
				(String) vMemberList[accessIdIndex].get(9), (String) vMemberList[accessIdIndex].get(6),
				(String) vMemberList[accessIdIndex].get(7), (int) vMemberList[accessIdIndex].get(8));
	}

	public void UserInfoDel() {// ����ϴ� ������ �¼��� ���� ������ ��� �����ϰ�, ������,�¼������� �̿뿩�ε�
		// �ʱ�ȭ
		vMemberList[accessIdIndex].set(4, false);
		vMemberList[accessIdIndex].set(5, false);
		vMemberList[accessIdIndex].set(6, null); // 6 �Խǽð�
		vMemberList[accessIdIndex].set(7, null); // 7 ��ǽð�
		vMemberList[accessIdIndex].set(8, null); // 8 ����Ƚ��
		vMemberList[accessIdIndex].set(9, null); // 9 �¼���ġ
		vMemberList[accessIdIndex].set(10, null); // 10 ������

	}

	/////////////////////////////////////////////////////////////////
	/////////////// SeatView1(�α���â)�� ��� ó�� �κ�///////////////////////
	// ȸ���� �¼� ��������� ������Ʈ �Ѵ�.
	public void SeatUserUpdateTrue() {
		vMemberList[accessIdIndex].set(5, true);
		System.out.println(vMemberList[accessIdIndex].get(5) + "5");
	}

	public void SeatUserUpdateFalse() {
		vMemberList[accessIdIndex].set(5, false);
	}

	public void SeatInfoUpdate(String nowTime, String endTime, int extensionNum, String seatLocation,
			String readingRoom) {
		loginView = new LoginView();

		// ������ ������� �¼��� ���� ������ ������Ʈ �Ѵ�.
		vMemberList[accessIdIndex].set(6, nowTime); // 6 �Խǽð�
		vMemberList[accessIdIndex].set(7, endTime); // 7 ��ǽð�
		vMemberList[accessIdIndex].set(8, extensionNum); // 8 ����Ƚ��
		vMemberList[accessIdIndex].set(9, seatLocation); // 9 �¼���ġ
		vMemberList[accessIdIndex].set(10, readingRoom); // 10 ������
		// ���̵� 0 , �н����� 1 , �̸� 2, ���� 3, �α��� �̿� 4, �¼���� 5, �Խ� 6, ��� 7, ���� 8, �¼���ġ
		// 9, ������ 10
		// ���̵�,�¼���ġ,������,�Խ�,���,����Ƚ���� �信 �Ѱ���
		loginView.SeatInfoView((String) vMemberList[accessIdIndex].get(0), (String) vMemberList[accessIdIndex].get(10),
				(String) vMemberList[accessIdIndex].get(9), (String) vMemberList[accessIdIndex].get(6),
				(String) vMemberList[accessIdIndex].get(7), (int) vMemberList[accessIdIndex].get(8));
	}

	public String CheckOutInfoRoom() { // ���� �̿����� �����Ǹ��� ��ȯ
		return (String) vMemberList[accessIdIndex].get(10);
	}

	public String CheckOutInfoLocation() { // ���� �̿����� �¼� ��ġ�� ��ȯ
		return (String) vMemberList[accessIdIndex].get(9);
	}

	public void SeatInit(int rowNum, int col, char row, int state) {
		seatView1 = new SeatView1();
		seatView2 = new SeatView2();
		if (state == 1) {
			seatView1.SeatInit(rowNum, col, row);
		} else if (state == 2) {
			seatView2.SeatInit(rowNum, col, row);
		}

	}

	public void seatMoveStateTrue() {
		this.moveState = true;

	}

	public void seatMoveStateFalse() {
		this.moveState = false;

	}

	public boolean getMoveState() {
		return this.moveState;
	}

	public void setSeatCountUp1() {
		++this.SeatCount1;
	}

	public void setSeatCountDo1() {
		--this.SeatCount1;
		System.out.println(this.SeatCount1);
	}

	public int getSeatCount1() {
		return this.SeatCount1;
	}

	public void setSeatCountUp2() {
		++this.SeatCount2;
	}

	public void setSeatCountDo2() {
		--this.SeatCount2;
		System.out.println(this.SeatCount2);
	}

	public int getSeatCount2() {
		return this.SeatCount2;
	}
	/////////////////////////////////////////////////////////////////

}