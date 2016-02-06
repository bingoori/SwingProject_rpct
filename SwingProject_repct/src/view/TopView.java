package view;

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
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.UpdateModel;

public final class TopView extends ManagerView {
	private UpdateModel model = new UpdateModel();
	private JLabel title;
	private JButton mngButton;
	private TopImage topImage;
	private JLayeredPane topLayeredPane;

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
				String[] str = { "회원목록", "이용자목록" };
				String password = JOptionPane.showInputDialog(null, "관리자 비밀번호를 입력하세요");

				try {
					if (password.equals("hi*^^*")) {
						int choice = JOptionPane.showOptionDialog(null, "모드를 선택하세요", "선택",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
						if (choice == JOptionPane.YES_OPTION) {
							System.out.println("회원목록을 선택하셧습니다.");
							memberListView();
						} else if (choice == JOptionPane.NO_OPTION) {
							System.out.println("이용자목록을 선택하셨습니다.");
							usedMemberListView();
						}
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 잘 못 입력하셨습니다.");
					}
				} catch (NullPointerException e2) {
				}

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
