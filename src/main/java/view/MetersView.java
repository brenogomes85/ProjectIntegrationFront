package main.java.view;

public class MetersView {
	
	private Integer id;
	private String line;
	private String category;
	private String model;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	/*@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getId()+", ");
		
		builder.append(getLine()+", ");
		
		builder.append(getCategory()+", ");
		
		builder.append(getModel());
		
		return builder.toString();
	}*/
	
}
