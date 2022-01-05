package CSV;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import app.Measure;
public class writeCsv {
	private static String name;
	private static String[] header;
	private static String path;
	private static List<Measure> data;


	/**
	 * @param path String,name String,data List<Measure>,header
	 * */


	@SuppressWarnings("static-access")
	public writeCsv(String path,String name, List<Measure> data, String[] header){
		this.setName(name);
		this.setData(data);
		this.setHeader(header);
		this.setPath(path);
	}
	/*
	 * It initialize the Header gived by the constructor params and set in the local Attribute header then it write it in the CSV file 
	 * then for each element in the List<Measures> data it write it in the same lign but different column  
	 * */
	public static void StartWriting() throws IOException {  
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(getPath());
			String header = "";
			for (int i = 0; i < getHeader().length; i++) {
				if(i == getHeader().length-1) {
					header = header + getHeader()[i] + "\n";
				}
				else {
					header = header +getHeader()[i] + ", ";
				}
			}
			fileWriter.append(header);
			for(Measure u: getData()) {
				fileWriter.append(String.valueOf(u.getAire()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(u.getCentre_x()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(u.getCentre_y()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(u.getXstart()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(u.getYstart()));
				fileWriter.append(",");
				fileWriter.append(String.valueOf(u.getWidth()));
				fileWriter.append(",");
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


	public static String getName() {
		return name;
	}

	public void setName(String name) {
		writeCsv.name = name;
	}
	public static List<Measure> getData() {
		return data;
	}
	public void setData(List<Measure> data) {
		writeCsv.data = data;
	}
	public static String[] getHeader() {
		return header;
	}
	public void setHeader(String[] header) {
		writeCsv.header = header;
	}
	public static String getPath() {
		return path;
	}
	public static void setPath(String path) {
		writeCsv.path = path;
	}
}
