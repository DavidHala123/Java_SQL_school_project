package project_pc2t;

public class Zamestnanci implements Interface.Person{

	SQLQuerrys sql = new SQLQuerrys();
	private String jmeno;
	private String dateofbirth;
	private double salary;
	
	public Zamestnanci(String name, String date, double salary) 
	{
		this.jmeno = name;
		this.dateofbirth = date;
		this.salary = salary;
	}
	
	@Override
	public void setname(String name) {
		// TODO Auto-generated method stub
		this.jmeno = name;
		
	}
	@Override
	public String getname() {
		// TODO Auto-generated method stub
		return jmeno;
	}
	@Override
	public void setdate(String date) {
		// TODO Auto-generated method stub
		this.dateofbirth = date;
	}
	@Override
	public String getdate() {
		// TODO Auto-generated method stub
		return dateofbirth;
	}
	@Override
	public void senddata() {
		// TODO Auto-generated method stub
		sql.connect();
		sql.insertIntoZamestnanci(this.jmeno, this.dateofbirth, this.salary);
		sql.disconnect();
	}

}