package model.img;

public class ImgVO {
	private int pk;
	private String title;
	private String filename;


	public int getPk() {
		return pk;
	}

	public void setPk(int pk) {
		this.pk = pk;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "ImgVO [pk=" + pk + ", title=" + title + ", filename=" + filename + "]";
	}
	
	

}
