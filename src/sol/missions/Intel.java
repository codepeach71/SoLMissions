package sol.missions;

public abstract class Intel {
	protected String name;	
	protected String shortDescription;
	protected String contents;	
	protected String action;
	protected String whereFound;
	protected String personName;
	protected int technoByte;
	
	public String getName() { return name; }
	public String getShortDescription() { return shortDescription; }
	public String getContents() { return contents; }
	public String getPersonName() { return personName; }
	public String getAction() { return action; }
	public String getWhereFound() { return whereFound; }
	public int getTechnoByte() { return technoByte;	}
	
	
	public Intel(String name, String shortDescription, String contents, String action, String whereFound, String personName, int technoByte) {
		this.name = name;
		this.shortDescription = shortDescription;
		this.contents = contents;
		this.action = action;
		this.whereFound = whereFound;
		this.personName = personName;
		this.technoByte = technoByte;
	}
	
	
	
}