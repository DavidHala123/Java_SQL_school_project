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
					System.out.print("MOMENT�LN� NEN� VYBR�NA ��DN� TABULKA, pros�m vyberte tabulku, ne� budete pokra�ovat: ");
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
						  "1 - Zm�nit tabulku\n"
						+ "2 - P�idat osobu do datab�ze\n"
						+ "3 - Smazat osobu z datab�ze\n"
						+ "4 - Zobrazit tabulku databaze\n"
						+ "5 - P�i�adit zam�stnance ke studentovi\n"
						+ "6 - Zobrazit zam�stnance p�i�azeny ke studentovi\n"
						+ "7 - P�idat studentovi zn�mku \n" //Toto nejak upravit na rozeznavani, jestli je tabulka studentu nebo zamestnancu
						+ "8 - Ulo�it do datab�ze\n"
						+ "9 - Ukon�it\n");			
			}
			else if(sql.table == 1) 
			{
				System.out.println(
						  "1 - Zm�nit tabulku\n"
						+ "2 - P�idat osobu do datab�ze\n"
						+ "3 - Smazat osobu z datab�ze\n"
						+ "4 - Zobrazit tabulku databaze\n"
						+ "5 - P�i�adit studenta zam�stnanci\n"
						+ "6 - Zobrazit studenty zam�stnance\n"
						+ "7 - Upravit plat zam�stnanci \n" //Toto nejak upravit na rozeznavani, jestli je tabulka studentu nebo zamestnancu
						+ "8 - Ulo�it do datab�ze\n"
						+ "9 - Ukon�it\n");			
			}
			System.out.print("\nZadejte po�adovanou akci: ");
			while (!sc.hasNextInt()) 
			{
				System.out.print("Zadejte po�adovanou akci: ");
				sc.next();
			}
			int choice = sc.nextInt();
			
			
			switch(choice) 
			{
			
			
			case 1:
				Scanner scAns = new Scanner(System.in);
				System.out.print("Jste si jisti? Ve�ker� neulo�en� data budou ztracena.	(Y - ano, N - ne) --> ");
				String answer = scAns.nextLine();
				if(answer.equals("Y")) 
				{
					checktableName = "";
				}
				else if (!answer.equals("Y") && !answer.equals("N")) {
					System.out.println("Nebylo mo�n� rozeznat va�i odpov��.");
					System.out.println(answer);
				}
				break;
				
				
			case 2:
				try 
				{
					 Class.forName("project_pc2t." + checktableName);
				}catch(ClassNotFoundException e) 
				{
					System.out.println("Nena�la odpov�daj�c� class k tabulce");
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
					System.out.print("\t-->Zadejte zn�mku studentovi: ");
					while (!scVal.hasNextDouble()) 
					{
						scVal.nextDouble();
					}
					value = scVal.nextDouble();
					break;
				}
				else if(table == 1)
				{
					System.out.print("\t-->Zadejte plat zam�stnanci: ");
					while (!scVal.hasNextDouble()) 
					{
						scVal.nextDouble();
					}
					value = scVal.nextDouble();
					break;
				}
				System.out.println("Osoba byla �sp�n� p�id�na.");

				
			case 3:
				sql.connect();
				System.out.print("\t-->Zadejte ID osoby, kterou si p�ejete odstranit: ");
				while (!sc.hasNextInt()) 
				{
					sc.next();
				}
				int id = sc.nextInt();
				sql.delete(checktableName ,id);
				sql.disconnect();
				System.out.println("Osoba s ID : " + id + " byl �sp�n� odebr�na.");
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
					System.out.print("\t-->ID zam�stnance: ");
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
				System.out.println("\t-->ID zam�stnance: ");
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
				System.out.print("Zadejte ID osoby, kterou chcete zm�nit: ");	//tuto cast zmenit, at tu neni milion sysout
				while (!scUpdId.hasNextDouble()) 
				{
					System.out.print("Zadejte ID osoby, kterou chcete zm�nit: ");
					scUpdId.next();
				}
				double updateId = scUpdId.nextInt();
				System.out.print("Zadejte hodnotu, kterou chcete zadat t�to osob�: ");
				while (!scUpdVal.hasNextDouble()) 
				{
					System.out.print("Zadejte hodnotu, kterou chcete zadat t�to osob�: ");
					scUpdVal.next();
				}
				double updateVal = scUpdVal.nextInt();
				sql.connect();
				sql.update(checktableName, updateVal, updateId);
				sql.disconnect();
				System.out.println("Osoba s ID : " + updateId + " byla �sp�n� upravena.");
				break;
				
				
			case 8:
				Scanner scAns1 = new Scanner(System.in);
				if (name != "" && date != "")
				{
					System.out.print("Opravdu si p�ejete ulo�it data ( jmeno : " + name + ", datum narozen� : " + date + ") do tabulky " + checktableName + "?	(Y - ano, N - ne) --> ");
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
						System.out.println("Nebylo mo�n� rozeznat va�i odpov��.");
				}
				else
					System.out.println("Nebyla zad�na ��dn� data.");
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
