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
		title = new JLabel("00������");
		title.setForeground(Color.white);
		mngButton = new JButton(new ImageIcon("image/admin.jpg"));
		mngButton.setBounds(1200, 15, 80, 20);
		mngButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] str = { "ȸ�����", "�̿��ڸ��" };
				String password = JOptionPane.showInputDialog(null, "������ ��й�ȣ�� �Է��ϼ���");

				try {
					if (password.equals("hi*^^*")) {
						int choice = JOptionPane.showOptionDialog(null, "��带 �����ϼ���", "����",
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, str, str[0]);
						if (choice == JOptionPane.YES_OPTION) {
							System.out.println("ȸ������� �����ϼ˽��ϴ�.");
							memberListView();
						} else if (choice == JOptionPane.NO_OPTION) {
							System.out.println("�̿��ڸ���� �����ϼ̽��ϴ�.");
							usedMemberListView();
						}
					} else {
						JOptionPane.showMessageDialog(null, "��й�ȣ�� �� �� �Է��ϼ̽��ϴ�.");
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
				System.out.println("�̹��� �ҷ����� ����");
				System.exit(0);
			}

			g.drawImage(img, 0, 0, 1400, 50, null, this);
		}
	}

}