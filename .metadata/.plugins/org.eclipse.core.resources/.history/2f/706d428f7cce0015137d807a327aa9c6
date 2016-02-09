package view;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.UpdateModel;

public final class SeatTabView implements ChangeListener {
	private UpdateModel model = new UpdateModel();
	private JTabbedPane readingRoom = new JTabbedPane();
	private SeatView1 seatView1 = new SeatView1();
	private SeatView2 seatView2 = new SeatView2();

	// SeatView1 , 2 추가해야함

	JTabbedPane CreateSeatTab() {
		readingRoom.setBounds(347, 47, 1000, 685);
		readingRoom.setVisible(true);

		readingRoom.addChangeListener(this);
		readingRoom.addTab("1열람실", seatView1.CreateSeatView1());
		readingRoom.addTab("2열람실", seatView2.CreateSeatView2());
		// readingRoom.addTab("2열람실", cen2.SetCenPan());
		return readingRoom;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int curSelIndex = readingRoom.getSelectedIndex();
		String curPaneTitle = readingRoom.getTitleAt(curSelIndex);
		model.setRoomName(curPaneTitle);
	}

}
