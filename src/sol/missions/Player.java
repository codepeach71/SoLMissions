package sol.missions;

public class Player {
	private int numAgents;
	private int agentCompetency;
	private int media;
	private int mediaRating;
	private int technoBytes;
	private int technoCred;
	
	private int money;
	private int spentMoney;
	
	public int getAvailableMoney() {
		return money - spentMoney;
	}

	public int getNumAgents() { return numAgents; }
	public void changeNumAgents(int change) { numAgents += change; }
	public int getAgentCompetency() { return agentCompetency; }
	public void changeAgentCompetency(int change) { agentCompetency += change; }
	public int getMedia() { return media; } 
	public void changeMedia(int change) { media = change; }
	public int getMediaRating() { return mediaRating; }
	public void changeMediaRating(int change) { mediaRating += change; }
	public int getTechnoBytes() { return technoBytes; }
	public void changeTechnoBytes(int change) { technoBytes += change; }
	public int getTechnoCred() { return technoCred; }
	public void changeTechnoCred(int change) {	technoCred += change; }
}
