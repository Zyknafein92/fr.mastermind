package gamemastermind;

public enum Colors {

	 
	B ("Bleu"),   
	J ("Jaune"),
	N ("Noir"),
	R ("Rouge"),
	V ("Vert");
	
	private String colors;

	Colors (String colors) {
		
		this.setColors(colors);
	}

	public String getColors() {
		return colors;
	}

	public void setColors(String colors) {
		this.colors = colors;
	}
}
