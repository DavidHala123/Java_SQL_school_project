package project_pc2t;
import java.util.Scanner;

public class MainClass {

	public static int table;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		boolean ongoing = true;
		Scanner sc =  new Scanner(System.in);
		SQLQuerrys sql = new SQLQuerrys();
		String checktableName = "";
		String name = "";
		String date = "";
		double value = 0.0;
		sql.connect();
		sql.createTable();
		sql.disconnect();
		
		while(ongoing) 
		{
			Scanner scTable = new Scanner(System.in);
			while(checktableName == "")
			{
				sql.connect();
				while(!sql.TableExist(checktableName))
				{
					System.out.print("MOMENTÁLNÌ NENÍ VYBRÁNA ŽÁDNÁ TABULKA, prosím vyberte tabulku, než budete pokraèovat: ");
					checktableName = scTable.next();
				}
				sql.disconnect();
			}
			System.out.println(table);
			if (checktableName != "")
				System.out.println("PRACUJETE S TABULKOU: " + checktableName + "\n");
			if(sql.table == 0) 
			{
				System.out.println(
						  "1 - Zmìnit tabulku\n"
						+ "2 - Pøidat osobu do databáze\n"
						+ "3 - Smazat osobu z databáze\n"
						+ "4 - Zobrazit tabulku databaze\n"
						+ "5 - Pøiøadit zamìstnance ke studentovi\n"
						+ "6 - Zobrazit zamìstnance pøiøazeny ke studentovi\n"
						+ "7 - Pøidat studentovi známku \n" //Toto nejak upravit na rozeznavani, jestli je tabulka studentu nebo zamestnancu
						+ "8 - Uložit do databáze\n"
						+ "9 - Ukonèit\n");			
			}
			else if(sql.table == 1) 
			{
				System.out.println(
						  "1 - Zmìnit tabulku\n"
						+ "2 - Pøidat osobu do databáze\n"
						+ "3 - Smazat osobu z databáze\n"
						+ "4 - Zobrazit tabulku databaze\n"
						+ "5 - Pøiøadit studenta zamìstnanci\n"
						+ "6 - Zobrazit studenty zamìstnance\n"
						+ "7 - Upravit plat zamìstnanci \n" //Toto nejak upravit na rozeznavani, jestli je tabulka studentu nebo zamestnancu
						+ "8 - Uložit do databáze\n"
						+ "9 - Ukonèit\n");			
			}
			System.out.print("\nZadejte požadovanou akci: ");
			while (!sc.hasNextInt()) 
			{
				System.out.print("Zadejte požadovanou akci: ");
				sc.next();
			}
			int choice = sc.nextInt();
			
			
			switch(choice) 
			{
			
			
			case 1:
				Scanner scAns = new Scanner(System.in);
				System.out.print("Jste si jisti? Veškerá neuložená data budou ztracena.	(Y - ano, N - ne) --> ");
				String answer = scAns.nextLine();
				if(answer.equals("Y")) 
				{
					checktableName = "";
				}
				else if (!answer.equals("Y") && !answer.equals("N")) {
					System.out.println("Nebylo možné rozeznat vaši odpovìï.");
					System.out.println(answer);
				}
				break;
				
				
			case 2:
				try 
				{
					 Class.forName("project_pc2t." + checktableName);
				}catch(ClassNotFoundException e) 
				{
					System.out.println("Nenašla odpovídající class k tabulce");
					break;
				}
				Scanner scName = new Scanner(System.in);
				System.out.print("\t-->Zadejte jmeno: ");
				while (!scName.hasNextLine()) 
				{
					scName.next();
				}
				name = scName.nextLine();
				Scanner scDate = new Scanner(System.in);
				System.out.print("\t-->Zadejte datum narozeni: ");
				while (!scDate.hasNextLine()) 
				{
					scDate.next();
				}
				date = scDate.next();
				Scanner scVal = new Scanner(System.in);
				if(table == 0)
				{
					System.out.print("\t-->Zadejte známku studentovi: ");
					while (!scVal.hasNextDouble()) 
					{
						scVal.nextDouble();
					}
					value = scVal.nextDouble();
					break;
				}
				else if(table == 1)
				{
					System.out.print("\t-->Zadejte plat zamìstnanci: ");
					while (!scVal.hasNextDouble()) 
					{
						scVal.nextDouble();
					}
					value = scVal.nextDouble();
					break;
				}
				System.out.println("Osoba byla úspìšnì pøidána.");

				
			case 3:
				sql.connect();
				System.out.print("\t-->Zadejte ID osoby, kterou si pøejete odstranit: ");
				while (!sc.hasNextInt()) 
				{
					sc.next();
				}
				int id = sc.nextInt();
				sql.delete(checktableName ,id);
				sql.disconnect();
				System.out.println("Osoba s ID : " + id + " byl úspìšnì odebrána.");
				break;
				
				
			case 4:
				sql.connect();
				sql.selectAll(checktableName);
				sql.disconnect();
				break;
				
			
			case 5:
				Scanner idtoid1 = new Scanner(System.in);
				Scanner idtoid2 = new Scanner(System.in);
				if(table == 0)
				{
					System.out.print("\t-->ID studenta: ");
					while (!idtoid1.hasNextInt()) 
					{
						idtoid1.nextInt();
					}
					int Id1 = idtoid1.nextInt();
					System.out.print("\t-->ID zamìstnance: ");
					while (!idtoid2.hasNextInt()) 
					{
						idtoid2.nextInt();
					}
					int Id2 = idtoid2.nextInt();
					sql.connect();
					sql.AddStudToProf(Id1, Id2);
					sql.disconnect();
				}
				break;
				
				
			case 6:
				Scanner idofstud = new Scanner(System.in);
				System.out.println("\t-->ID zamìstnance: ");
				while (!idofstud.hasNextInt()) 
				{
					idofstud.nextInt();
				}
				int idtosend = idofstud.nextInt();
				sql.connect();
				sql.PrintStudAndProf(idtosend);
				sql.disconnect();
				break;
				
				
			case 7:
				Scanner scUpdVal = new Scanner(System.in);
				Scanner scUpdId = new Scanner(System.in);
				System.out.print("Zadejte ID osoby, kterou chcete zmìnit: ");	//tuto cast zmenit, at tu neni milion sysout
				while (!scUpdId.hasNextDouble()) 
				{
					System.out.print("Zadejte ID osoby, kterou chcete zmìnit: ");
					scUpdId.next();
				}
				double updateId = scUpdId.nextInt();
				System.out.print("Zadejte hodnotu, kterou chcete zadat této osobì: ");
				while (!scUpdVal.hasNextDouble()) 
				{
					System.out.print("Zadejte hodnotu, kterou chcete zadat této osobì: ");
					scUpdVal.next();
				}
				double updateVal = scUpdVal.nextInt();
				sql.connect();
				sql.update(checktableName, updateVal, updateId);
				sql.disconnect();
				System.out.println("Osoba s ID : " + updateId + " byla úspìšnì upravena.");
				break;
				
				
			case 8:
				Scanner scAns1 = new Scanner(System.in);
				if (name != "" && date != "")
				{
					System.out.print("Opravdu si pøejete uložit data ( jmeno : " + name + ", datum narození : " + date + ") do tabulky " + checktableName + "?	(Y - ano, N - ne) --> ");
					String answer1 = scAns1.nextLine();
					if(answer1.equals("Y")) 
					{
						Class<?> cl = Class.forName("project_pc2t." + checktableName);
						cl.getMethod("senddata").invoke(cl.getDeclaredConstructor(String.class, String.class, double.class).newInstance(name, date, value));
						name = "";
						date = "";
						value = 0.0;
					}
					else if (!answer1.equals("Y") && !answer1.equals("N"))
						System.out.println("Nebylo možné rozeznat vaši odpovìï.");
				}
				else
					System.out.println("Nebyla zadána žádná data.");
				break;
				
				
			case 9:
				ongoing = false;
				scTable.close();
				sql.disconnect();
				break;
			}
		}
		sc.close();
	}
}
