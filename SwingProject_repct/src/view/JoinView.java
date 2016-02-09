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
	private JButton joinButton = new JButton("가입");
	private JButton cancelButton = new JButton("취소");
	protected JDialog joinDialog;

	public JoinView() {
		joinDialog = new JDialog();
		joinDialog.setTitle("회원가입");
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
		joinButton.setActionCommand("가입");
		cancelButton.setActionCommand("취소");
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
		JOptionPane.showMessageDialog(null, "이미 가입된 아이디 입니다.");
	}

	public void ShowSuccessMessage() {
		JOptionPane.showMessageDialog(null, "가입 완료 되었습니다.");
	}

	public void ShowLoadMessage() {
		JOptionPane.showMessageDialog(null, "회원목록을 로드 하였습니다.");

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
			patternName = Pattern.compile("^[a-zA-Z가-힣,]*$");
			matcherName = patternName.matcher(joinTFName.getText());
			patternBirth = Pattern.compile("^[a-zA-Z0-9가-힣]*$");
			matcherBirth = patternBirth.matcher(joinTFBirth.getText());
			String command = event.getActionCommand();
			if (command.equals("가입")) {
				// System.out.println("업데이트하는 클랙스인 Model클래스에 회원의 가입 정보를
				// 넘겨줍니다.");

				System.out.println(joinTFPsw.getText().length());
				if (matcherId.matches() == false) {
					JOptionPane.showMessageDialog(null, "아이디는 영문과 숫자로 만들 수 있습니다.");
					return;
				} else if (joinTFId.getText().length() < 4 || joinTFId.getText().length() > 15) {
					JOptionPane.showMessageDialog(null, "아이디는 4글자 이상 15글자 미만이어야 합니다.");
					return;
				} else if (joinTFPsw.getText().length() < 4 || joinTFPsw.getText().length() > 20) {
					JOptionPane.showMessageDialog(null, "패스워드는 4글자 이상 20글자 미만이어야 합니다.");
					return;
				} else if (matcherName.matches() == false) {
					JOptionPane.showMessageDialog(null, "이름은 한글, 영문으로만 작성 하십시오");
					return;
				} else if (joinTFName.getText().length() < 2) {
					JOptionPane.showMessageDialog(null, "이름을 잘 못 입력 하셨습니다.");
					return;
				} else if (matcherBirth.matches() == false) {
					JOptionPane.showMessageDialog(null, "생년월일은 영문, 숫자, 한글로 작성 하십시오");
					return;
				} else if (joinTFBirth.getText().length() < 6) {
					JOptionPane.showMessageDialog(null, "생년월일은 6글자 이상 이어야 합니다.");
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

			} else if (command.equals("취소")) {
				joinDialog.setVisible(false);

			}

		}

	}

}
