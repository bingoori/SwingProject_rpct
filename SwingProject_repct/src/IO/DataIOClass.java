package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

import model.UpdateModel;

public class DataIOClass {
	private UpdateModel model;
	private FileInputStream fis = null;
	private InputStreamReader isr = null;
	private BufferedReader br;
	private Vector<String> v;
	private StringTokenizer st;
	private String str = "";
	private File file;

	public void saveMemberList(Vector[] vMemberList, int index) {
		BufferedWriter bw;

		try {
			bw = new BufferedWriter(
					new FileWriter("member/MemberList.dat", true));
			for (int i = 0; i < 4; i++) {
				if (i == 3) {
					bw.write((String) vMemberList[index].get(i));
				} else {

					bw.write((String) vMemberList[index].get(i));
					bw.write(",");
				}

			}
			bw.write("\"");
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}

	public void loadMemberList() {
		model = new UpdateModel();
		file = new File("member/MemberList.dat");
		int index = 0;
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, "MS949");
			br = new BufferedReader(isr);
			while ((str = br.readLine()) != null) {
				v = new Vector<String>();
				st = new StringTokenizer(str, ",\"");
				while (st.hasMoreTokens()) {
					// System.out.println(st.nextToken());
					v.add(st.nextToken());
					index++;
					if (index % 4 == 0) {
						model.setMemberList(v.get(0), v.get(1), v.get(2), v.get(3), 2);
						v.clear();
					}
				}

				for (int i = 0; i < v.size(); i++) {
					System.out.println(v.get(i));
				}
			}
			fis.close();
			isr.close();
			br.close();
		} catch (IOException e) {
			System.out.println("저장된 회원 목록이 없습니다.");
			return;
		}
	}

}
