package project_pc2t;

public class Studenti implements Interface.Person{

	SQLQuerrys sql = new SQLQuerrys();
	private String jmeno;
	private String dateofbirth;
	private double grade;
	
	public Studenti(String name, String date, double grade) 
	{
		this.jmeno = name;
		this.dateofbirth = date;
		this.grade = grade;
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
		sql.insertIntoStudenti(this.jmeno, this.dateofbirth, this.grade);
		sql.disconnect();
	}

}
