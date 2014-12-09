package sol.missions;

public class Intel {
	protected String name;
	protected String contents;
	protected int technoBytes;
	
	public Intel(String name, String contents) {
		this.name = name;
		this.contents = contents;
		this.technoBytes = 0;
	}
	
	public Intel(String name, String contents, int technoBytes) {
		this.name = name;
		this.contents = contents;
		this.technoBytes = technoBytes;
	}
}
