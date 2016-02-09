package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.UpdateModel;

public final class TopView extends ManagerView {
	private UpdateModel model = new UpdateModel();
	private JLabel title;
	private JButton mngButton;
	private TopImage topImage;
	private JLayeredPane topLayeredPane;
	//
	private JLabel managerTitle;
	private JLabel managerId;
	private JLabel managerPassword;
	private JTextField manageTFId;
	private JPasswordField managerTFPassword;
	private JLayeredPane managerPane;
	private JDialog managerDialog;
	private JButton access;

	JLayeredPane CreateTopView() {
		topLayeredPane = new JLayeredPane();
		topLayeredPane.setBounds(0, 0, 1400, 50);
		topLayeredPane.setLayout(null);
		title = new JLabel("00도서관");
		title.setForeground(Color.white);

		mngButton = new JButton(new ImageIcon("image/admin.jpg"));
		mngButton.setBounds(1200, 15, 80, 20);
		mngButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				managerDialog = new JDialog();
				managerPane = new JLayeredPane();
				managerTitle = new JLabel("관리자 아이디와 패스워드를 입력하세요");
				managerId = new JLabel("ID : ");
				managerPassword = new JLabel("PSW : ");
				manageTFId = new JTextField();
				managerTFPassword = new JPasswordField();
				access = new JButton("확인");

				managerDialog.setSize(250, 150);
				managerDialog.setLocation(900, 300);
				managerPane.setSize(300, 250);
				managerId.setBounds(20, 12, 50, 20);
				managerPassword.setBounds(20, 32, 50, 20);
				manageTFId.setBounds(60, 12, 150, 20);
				managerTFPassword.setBounds(60, 32, 150, 20);
				managerTFPassword.setEchoChar('*');
				access.setSize(300, 50);
				managerPane.add(managerId);
				managerPane.add(managerPassword);
				managerPane.add(manageTFId);
				managerPane.add(managerTFPassword);
				managerDialog.add(managerTitle, BorderLayout.NORTH);
				managerDialog.add(managerPane, BorderLayout.CENTER);
				managerDialog.add(access, BorderLayout.SOUTH);
				managerDialog.setVisible(true);
				access.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String[] str = { "회원목록", "이용자목록" };
						String Psw = "";
						for (char pass : managerTFPassword.getPassword()) {
							Psw += pass;
						}
						try {
							if (manageTFId.getText().equals("admin") && Psw.equals("hi*^^*")) {
								System.out.println("관리자 로그인");
								int choice = JOptionPane.showOptionDialog(null, "모드를 선택하세요", "선택",
										JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str,
										str[0]);
								if (choice == JOptionPane.YES_OPTION) {
									System.out.println("회원목록을 선택하셧습니다.");
									managerDialog.dispose();
									memberListView();
								} else if (choice == JOptionPane.NO_OPTION) {
									System.out.println("이용자목록을 선택하셨습니다.");
									managerDialog.dispose();
									usedMemberListView();
								} else {
									JOptionPane.showMessageDialog(null, "아이디 또는 패스워드를 잘 못 입력하셨습니다.");
								}
							}
						} catch (NullPointerException e2) {
						}

					}
				});
			}
		});
		topImage = new TopImage();
		topImage.setBounds(0, 0, 1400, 50);
		topLayeredPane.add(mngButton);
		topLayeredPane.add(topImage);

		return topLayeredPane;
	}

	class TopImage extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Image img = null;

		@Override
		public void paint(Graphics g) {
			try {
				img = ImageIO.read(new File("image/TopPen2.png"));
			} catch (IOException e) {
				System.out.println("이미지 불러오기 실패");
				System.exit(0);
			}

			g.drawImage(img, 0, 0, 1400, 50, null, this);
		}
	}

}
