package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.UpdateModel;

public class ManagerView {
	private UpdateModel model = new UpdateModel();
	private JDialog JDialog1 = new JDialog();
	private static String columnNames1[] = { "���̵�", "�н�����", "�̸�", "����" };
	private String columnNames2[] = { "���̵�", "�н�����", "�̸�", "����", "�Խǽð�", "��ǽð�", "����Ƚ��", "�¼���ġ", "������", "����" };

	// DefaultTableModel�� �����ϰ� ������ ��� >>>> Jtable�� ��������� �����ϴ� �κ�
	private DefaultTableModel defaultTableModel1 = new DefaultTableModel(null, columnNames1);
	private UserTableModel tableModel = new UserTableModel(null, columnNames2);

	// JTable�� DefaultTableModel�� ���
	private JTable jTable1 = new JTable(defaultTableModel1);
	private JTable jTable2 = new JTable(tableModel);
	JScrollPane jScollPane1 = new JScrollPane(jTable1);
	private static JButton usedMemberRemove = new JButton("����");
	private Vector vAddModel;
	private JFrame managerframe;
	// üũ�ڽ��� �̼��� ���� true �̴�. ���ð��� false
	private static boolean selectCheck = true;
	private String delId = "";
	private static int rowcheck = 0;

	public void memberListView() {
		JDialog1.getContentPane().add(jScollPane1);
		defaultMemListUp();

		JDialog1.setSize(500, 300);
		JDialog1.setVisible(true);
	}

	public void usedMemberListView() {
		managerframe = new JFrame("�¼� �̿��� ���");
		managerframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		jTable2.setCellSelectionEnabled(true);
		managerframe.getContentPane().add(new JScrollPane(jTable2));
		managerframe.getContentPane().add(usedMemberRemove, BorderLayout.SOUTH);

		usedMemListUp();
		jTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowcheck = jTable2.getSelectedRow();
				selectCheck = (boolean) jTable2.getValueAt(rowcheck, 9);
				delId = (String) jTable2.getValueAt(rowcheck, 0);

			}
		});
		usedMemberRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					tableModel.removeRow(rowcheck);
					model.functionManager(delId);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "���� �¼��� ���� ���� ȸ���� �����ϴ�.");
				}
			}
		});
		managerframe.pack();
		managerframe.setVisible(true);

	}

	public void defaultMemListUp() {

		if (defaultTableModel1.getRowCount() > 0) {
			// ȸ�� ����� �ʱ�ȭ�ϰ� ���� ����Ʈ�� �����´�.
			defaultTableModel1.setNumRows(0);
		}
		Vector[] vMemberList = model.getManagerMemberList();
		int index = model.getManagerMemberIndex();
		for (int i = 0; i < index; i++) {
			System.out.println("index : " + i);
			vAddModel = new Vector<>();
			for (int j = 0; j < vMemberList[i].size(); j++) {
				vAddModel.add(vMemberList[i].get(j));
			}
			defaultTableModel1.addRow(vAddModel);
		}

	}

	public void usedMemListUp() {
		if (tableModel.getRowCount() > 0) {
			// ȸ�� ����� �ʱ�ȭ�ϰ� ���� ����Ʈ�� �����´�.
			tableModel.setNumRows(0);
		}
		Vector[] vMemberList = model.getManagerMemberList();
		int index = model.getManagerMemberIndex();
		for (int i = 0; i < index; i++) {
			System.out.println("index : " + i);
			vAddModel = new Vector<>();

			for (int j = 0; j < vMemberList[i].size(); j++) {
				if (vMemberList[i].get(5).equals(true)) {
					if (j == 4 || j == 5) {
						continue;
					}
					vAddModel.add(vMemberList[i].get(j));
				}

			}

			if (vAddModel.size() != 0) {
				vAddModel.add(new Boolean(false));
				tableModel.addRow(vAddModel);
			}

		}

	}

	class UserTableModel extends DefaultTableModel {
		public UserTableModel(Object[][] data, Object[] columnName) {
			super(data, columnName);
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}
	}
}