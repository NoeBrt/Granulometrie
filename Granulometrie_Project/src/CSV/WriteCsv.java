package CSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import Model.GranuloData;
import app.Measure;
import javafx.stage.FileChooser;

public class WriteCsv {
	private static String[] header;
	private static String path;
	private static GranuloData data;

	/**
	 * @param path String,name String,data List<Measure>,header
	 */

	@SuppressWarnings("static-access")
	public WriteCsv(GranuloData data, String[] header, String path) {
		this.setData(data);
		this.setHeader(header);
		this.setPath(path);
	}

	/*
	 * It initialize the Header gived by the constructor params and set in the local
	 * Attribute header then it write it in the CSV file then for each element in
	 * the List<Measures> data it write it in the same lign but different column
	 */
	public void StartWriting() throws IOException {
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getPath());
			String header = "";
			for (int i = 0; i < getHeader().length; i++) {
				if (i == getHeader().length - 1) {
					header = header + getHeader()[i] + "\n";
				} else {
					header = header + getHeader()[i] + ";";
				}
			}
			fileWriter.append(header);
			for (Measure u : getData().getMeasuresAfterScale()) {
				fileWriter.append(String.valueOf(u.getAire()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getCentre_x()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getCentre_y()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getXstart()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getYstart()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getWidth()));
				fileWriter.append(";");
				fileWriter.append(String.valueOf(u.getHeight()));
				fileWriter.append("\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static GranuloData getData() {
		return data;
	}

	public void setData(GranuloData data) {
		WriteCsv.data = data;
	}

	public static String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		WriteCsv.header = header;
	}

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		WriteCsv.path = path;
	}
}
