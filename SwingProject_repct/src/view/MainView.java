package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import IO.DataIOClass;

public final class MainView extends JFrame {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private TopView topView = new TopView();
	private LoginView loginView = new LoginView();
	private SeatTabView seatTabView = new SeatTabView();
	private DataIOClass dataio = new DataIOClass();
	// private JoinView joinView;

	public MainView() {
		setTitle("도서관 좌석 관리 프로그램");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(topView.CreateTopView(), BorderLayout.NORTH);
		this.add(loginView.CreateLoginView(), BorderLayout.WEST);
		this.add(seatTabView.CreateSeatTab(), BorderLayout.CENTER);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();

		int w = 1345;
		int h = 750;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;

		setBounds(x, y, w, h);

		setResizable(false);
		setVisible(true);
		//
		new JoinView();
		// 기존 가입된 회원 목로을 불러온다.
		dataio.loadMemberList();
	}
}
