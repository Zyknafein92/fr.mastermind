package combinaisons;

public class Npc {

	String nPC = "";
	int optM = 4;

	private Npc() {

		for(int i= 0;i < optM;i++) 
		{
			nPC = (nPC +(int) (Math.random()*10));
		}


	}
	private String getNpc() {
		return nPC;
	}

	public void setNPC(String nPC) {
		nPC = this.nPC;
	}
}
