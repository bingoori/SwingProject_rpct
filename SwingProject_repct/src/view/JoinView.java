package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.UpdateModel;

public class JoinView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UpdateModel model = new UpdateModel();
	private JLabel joinLId = new JLabel("ID");
	private JLabel joinLPsw = new JLabel("PW");
	private JLabel joinLName = new JLabel("Name");
	private JLabel joinLBirth = new JLabel("Birth");
	private JTextField joinTFId;
	private JTextField joinTFPsw;
	private JTextField joinTFName;
	private JTextField joinTFBirth;
	private JButton joinButton = new JButton("����");
	private JButton cancelButton = new JButton("���");
	protected JDialog joinDialog;

	public JoinView() {
		joinDialog = new JDialog();
		joinDialog.setTitle("ȸ������");
		joinDialog.setLayout(null);
		//
		joinDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//
		joinTFId = new JTextField(20);
		joinTFPsw = new JTextField(20);
		joinTFName = new JTextField(20);
		joinTFBirth = new JTextField(20);
		//
		joinLId.setBounds(20, 100, 30, 30);
		joinLPsw.setBounds(15, 150, 30, 30);
		joinTFId.setBounds(60, 100, 200, 30);
		joinTFPsw.setBounds(60, 150, 200, 30);
		joinLName.setBounds(15, 200, 40, 30);
		joinTFName.setBounds(60, 200, 200, 30);
		joinLBirth.setBounds(15, 250, 30, 30);
		joinTFBirth.setBounds(60, 250, 200, 30);
		//
		joinButton.setBounds(40, 340, 100, 30);
		cancelButton.setBounds(145, 340, 100, 30);
		//
		joinButton.setActionCommand("����");
		cancelButton.setActionCommand("���");
		//
		joinButton.addActionListener(new EventHandler());
		cancelButton.addActionListener(new EventHandler());
		//
		joinDialog.add(joinLId);
		joinDialog.add(joinLPsw);
		joinDialog.add(joinTFId);
		joinDialog.add(joinTFPsw);
		joinDialog.add(joinLName);
		joinDialog.add(joinTFName);
		joinDialog.add(joinLBirth);
		joinDialog.add(joinTFBirth);
		joinDialog.add(joinButton);
		joinDialog.add(cancelButton);
		//
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int w = 300;
		int h = 500;
		int x = (screenSize.width - w) / 2;
		int y = (screenSize.height - h) / 2;
		joinDialog.setBounds(x, y, w, h);
		//
		joinDialog.setVisible(false);
		joinDialog.setResizable(false);

	}

	public void ShowFailMessage() {
		JOptionPane.showMessageDialog(null, "�̹� ���Ե� ���̵� �Դϴ�.");
	}

	public void ShowSuccessMessage() {
		JOptionPane.showMessageDialog(null, "���� �Ϸ� �Ǿ����ϴ�.");
	}

	public void ShowLoadMessage() {
		JOptionPane.showMessageDialog(null, "ȸ������� �ε� �Ͽ����ϴ�.");

	}

	public void ClearTextField() {
		this.joinTFId.setText("");
		this.joinTFPsw.setText("");
		this.joinTFName.setText("");
		this.joinTFBirth.setText("");
	}

	class EventHandler implements ActionListener {
		Pattern patternId;
		Matcher matcherId;
		Pattern patternName;
		Matcher matcherName;
		Pattern patternBirth;
		Matcher matcherBirth;

		@Override
		public void actionPerformed(ActionEvent event) {
			patternId = Pattern.compile("^[a-zA-Z0-9]*$");
			matcherId = patternId.matcher(joinTFId.getText());
			patternName = Pattern.compile("^[a-zA-Z��-�R,]*$");
			matcherName = patternName.matcher(joinTFName.getText());
			patternBirth = Pattern.compile("^[a-zA-Z0-9��-�R]*$");
			matcherBirth = patternBirth.matcher(joinTFBirth.getText());
			String command = event.getActionCommand();
			if (command.equals("����")) {
				// System.out.println("������Ʈ�ϴ� Ŭ������ ModelŬ������ ȸ���� ���� ������
				// �Ѱ��ݴϴ�.");

				System.out.println(joinTFPsw.getText().length());
				if (matcherId.matches() == false) {
					JOptionPane.showMessageDialog(null, "���̵�� ������ ���ڷ� ���� �� �ֽ��ϴ�.");
					return;
				} else if (joinTFId.getText().length() < 4 || joinTFId.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "���̵�� 4���� �̻� 15���� �̸��̾�� �մϴ�.");
					return;
				} else if (joinTFPsw.getText().length() < 4 || joinTFPsw.getText().length() > 20) {
					JOptionPane.showMessageDialog(null, "�н������ 4���� �̻� 20���� �̸��̾�� �մϴ�.");
					return;
				} else if (matcherName.matches() == false) {
					JOptionPane.showMessageDialog(null, "�̸��� �ѱ�, �������θ� �ۼ� �Ͻʽÿ�");
					return;
				} else if (joinTFName.getText().length() < 2) {
					JOptionPane.showMessageDialog(null, "�̸��� �� �� �Է� �ϼ̽��ϴ�.");
					return;
				} else if (matcherBirth.matches() == false) {
					JOptionPane.showMessageDialog(null, "��������� ����, ����, �ѱ۷� �ۼ� �Ͻʽÿ�");
					return;
				} else if (joinTFBirth.getText().length() < 6) {
					JOptionPane.showMessageDialog(null, "��������� 6���� �̻� �̾�� �մϴ�.");
					return;

				} else {

					String id = joinTFId.getText();
					String psw = joinTFPsw.getText();
					String name = joinTFName.getText();
					String birth = joinTFBirth.getText();
					model.setMemberList(id, psw, name, birth, 1);
					joinDialog.setVisible(false);
					ClearTextField();
				}

			} else if (command.equals("���")) {
				joinDialog.setVisible(false);

			}

		}

	}

}