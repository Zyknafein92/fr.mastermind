package tools;

public enum Colors {


	Rouge(1),
	Vert(2),
	Bleu(3),
	Jaune(4),
	Orange(5),
	Noir(6),
    Blanc(7),
	Rose(8),
	Violet(9);

	
	private final int colors;
	
	
	Colors (int Colors) {
		
		this.colors = Colors;
	}

	public int getColors() {
		return colors;
	}
	
	public String getColorAsString() {
		return String.valueOf(colors);
	
	}
}