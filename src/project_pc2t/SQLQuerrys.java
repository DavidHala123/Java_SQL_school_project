package project_pc2t;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLQuerrys {

		public int table;
		private Connection con; 
		public boolean connect() { 
		       con= null; 
		       try {
		              con = DriverManager.getConnection("jdbc:sqlite:ProjectDB.db");                       
		       } 
		      catch (SQLException e) { 
		            System.out.println(e.getMessage());
			    return false;
		      }
		      return true;
		}
		
		public boolean TableExist(String table_name) 
		{
			try 
			{
				 String sql = "SELECT stipendium FROM " + table_name;
	             Statement stmt  = con.createStatement();
	             stmt.executeQuery(sql);
	             MainClass.table = 0;
	             return true;
			}catch (SQLException e)
			{
				try 
				{
					 String sql = "SELECT plat FROM " + table_name;
		             Statement stmt  = con.createStatement();
		             stmt.executeQuery(sql);
		             MainClass.table = 1;
		             return true;
				}catch (SQLException i)
				{
					return false;
				}
			}
		}
		
		public boolean createTable()
		{
		     if (con==null)
		           return false;
		    String sqlStudAZame = "CREATE TABLE IF NOT EXISTS StudAZam (" + "StudID REFERENCES Studenti(id), " + "ProfID REFERENCES Zamestnanci(id)" + ")";
		    String sqlStudenti = "CREATE TABLE IF NOT EXISTS Studenti (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL," +"datum_narozeni varchar(255) NOT NULL," +  "stud_prum real," + "stipendium int" + ");";
		    String sqlZamestnanci = "CREATE TABLE IF NOT EXISTS Zamestnanci (" + "id integer PRIMARY KEY," + "jmeno varchar(255) NOT NULL," + "datum_narozeni varchar(255)," + "plat real" + ");";
		    try{
		            Statement stmt = con.createStatement();
		            stmt.execute(sqlStudenti);
		            stmt.execute(sqlZamestnanci);
		            stmt.execute(sqlStudAZame);
		            return true;
		    } 
		    catch (SQLException e) {
		    System.out.println(e.getMessage());
		    }
		    return false;
		}
		
		public void AddStudToProf(int StudID, int ProfID)
		{
		    String sql = "INSERT INTO StudAZam (StudID, ProfID) VALUES (?,?)";
	        try {
	            PreparedStatement pstmt = con.prepareStatement(sql); 
	            pstmt.setInt(1, StudID);
	            pstmt.setInt(2, ProfID);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}
		public void PrintStudAndProf(int ID)
		{
	        try 
	        {
	        	if(MainClass.table == 0) 
	        	{
	    			String sql = "SELECT jmeno, id FROM Zamestnanci LEFT JOIN StudAZam ON Zamestnanci.id = StudAZam.ProfID WHERE StudAZam.StudID = " + ID + " GROUP BY Zamestnanci.jmeno";
		             Statement stmt  = con.createStatement();
		             //ResultSet rs1   = stmt1.executeQuery(sql1);
		             ResultSet rs   = stmt.executeQuery(sql);
		             while (rs.next()) 
		             {
		            	 System.out.println( 
				         rs.getString("jmeno"));
		            }	
	        	}
	        	if(MainClass.table == 1)
	        	{
	    			String sql = "SELECT jmeno, id FROM Studenti LEFT JOIN StudAZam ON Studenti.id = StudAZam.StudID WHERE StudAZam.ProfID = " + ID + " GROUP BY Studenti.jmeno";
		             Statement stmt  = con.createStatement();
		             //ResultSet rs1   = stmt1.executeQuery(sql1);
		             ResultSet rs   = stmt.executeQuery(sql);
		             while (rs.next()) 
		             {
		            	 System.out.println( 
				         rs.getString("jmeno"));
		            }
	        	}
	        } catch (SQLException e) {
	        }
		}
		
		public void insertIntoStudenti(String jmeno, String date, double grade) {

	        String sql = "INSERT INTO Studenti (jmeno,datum_narozeni,stud_prum,stipendium) VALUES(?,?,?,?)";
	        try {
	            PreparedStatement pstmt = con.prepareStatement(sql); 
	            pstmt.setString(1, jmeno);
	            pstmt.setString(2, date);
	            pstmt.setDouble(3, grade);
	            if(grade < 2) 
	            {
		            pstmt.setInt(4, 6000);
	            }
	            else
	            {
		            pstmt.setInt(4, 0);
	            }
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		
		public void insertIntoZamestnanci(String jmeno, String date, double salary) {

	        String sql = "INSERT INTO Zamestnanci (jmeno, datum_narozeni, plat) VALUES(?,?,?)";
	        try {
	            PreparedStatement pstmt = con.prepareStatement(sql); 
	            pstmt.setString(1, jmeno);
	            pstmt.setString(2, date);
	            pstmt.setDouble(3, salary);
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }
		
		public void selectAll(String nameoftable)
		{
	        String sql = "SELECT * FROM " + nameoftable;
	        try 
	        {
	             Statement stmt  = con.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql);
	             while (rs.next()) 
	             {
	            	 System.out.println(rs.getInt("id") +  "\t" +  
	            	 rs.getString("jmeno") + "\t" + 
			         rs.getString("datum_narozeni") + "\t" + 
			         rs.getDouble("stud_prum") + "\t" + 
			         rs.getInt("stipendium"));
	            }
	        } catch (SQLException e) {
	        	try 
			    {
	        		Statement stmt  = con.createStatement();
			        ResultSet rs    = stmt.executeQuery(sql);
			        while (rs.next()) 
			        {
		            	System.out.println(rs.getInt("id") +  "\t" +  
		    	        rs.getString("jmeno") + "\t" + 
		    	        rs.getString("datum_narozeni") + "\t" + 
		    	        rs.getInt("plat"));
			       }
		        } catch (SQLException i) {
		            System.out.println(i.getMessage());
		        }
	        }
		}
		
		public void update(String nameoftable, double value, double id) 
		{
			try 
			{
				String sql = "UPDATE " + nameoftable + " SET stud_prum = ? WHERE id = ?";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setDouble(1, value);
				pstm.setDouble(2, id);
				pstm.executeUpdate();
				pstm.close();
			} catch (SQLException e) {
				try 
				{
					String sql = "UPDATE " + nameoftable + " SET plat = ? WHERE id = ?";
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setDouble(1, value);
					pstm.setDouble(2, id);
					pstm.executeUpdate();
					pstm.close();
				} catch (SQLException i) {
		            System.out.println(i.getMessage());
		        }
	        }
		}
		
		
		public void delete(String nameoftable, int id) 
		{
			String sql = "DELETE FROM " + nameoftable + " WHERE id = ?";
	        try 
	        {
	            PreparedStatement pstm = con.prepareStatement(sql);
	            pstm.setInt(1, id);
	            pstm.executeUpdate();
	            pstm.close();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}


		public void disconnect() 
		{ 
			if (con != null) 
			{
				try 
				{
					con.close();  
				} 
		        catch (SQLException ex) 
				{
		        	System.out.println(ex.getMessage()); 
		        }
		    }
		}
}

