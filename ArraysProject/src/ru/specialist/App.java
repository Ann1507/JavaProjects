package ru.specialist;

public class App {

	public static void main(String[] args) {

		//int[] nums=new int[] {5,7,12,3,8,1,35,21,17,35,12,6,9,8,7};
		String[] names= {
				"Anny",
				"Annet",
				"Anya",
				"Aanya",
				"Anna Porkova",
				"Topkova Anita",
				"Anna",
				"Penkova Anna",
				"Tenkova Annna",
				"enkova Panna",
				"Anna Perko",
				"anna",
				"Anna Penkova",
				"Penkovu Annu",
				"Anny penkovu",
				"Penkovyy Anna"
				
		};
		String name ="Anna";
		//Поиск точного соответствия с учетом регистра
		for(String n:names) {
			if(n.equals(name)) 
				System.out.printf("%s = %s\n", n,name);
		}
		System.out.println("************");
		//поиск точного соотвествия без учета регистра
		for(String n:names) {
			if(n.equalsIgnoreCase(name)) 
				System.out.printf("%s = %s\n", n,name);
		}
		System.out.println("************");
		//Поиск вхождения подстроки с учетом регистра
		for(String n:names) {
			if(n.contains(name)) 
				System.out.printf("%s contains %s\n", n,name);
		}
		System.out.println("************");
		//Поиск вхождения подстроки без учета регистра
		for(String n:names) {
			if(n.toLowerCase().contains(name.toLowerCase())) 
				System.out.printf("%s contains %s\n", n,name);
		}
		System.out.println("************");
		//Поиск вхождения подстроки без учета регистра
		for(String n:names) {
			if(n.toLowerCase().contains(name.toLowerCase())) {
				int procent=(int)Math.round(((double)name.length()/n.length())*100);
				if(procent>=90)	
					System.out.printf("%s contains %s, %d%%\n", n,name,procent);
			}
			
				
		}
		
		String fio="Penkova Anna";
		String[] fullName=fio.split(" ");
		String lastName=fullName[0];
		String firstName=fullName[1];
		System.out.println("************");
		System.out.printf("lastName: %s  \n", lastName);
		System.out.printf("firstName: %s  \n", firstName);
		System.out.println("************");
		for(String n:names) {
			if(n.toLowerCase().contains(lastName.toLowerCase()) &&
				
					n.toLowerCase().contains(firstName.toLowerCase())	
					)
			{
				int procent=(int)Math.round(((double)(lastName.length()+firstName.length()+1)/n.length())*100);
				System.out.printf("%s contains %s %s, %d%%\n", n,lastName,firstName,procent);
			}
		}
		System.out.println("************");
		fio="Penkova Anna";
		String[] words=fio.split(" ");//массив слов котор ищем
		for(String n:names) {
			boolean found=false;
			int foundLength=0;//сумма длин слов котор нашли 
			int totalLength=0;
			for(String w :words) {//ищем w в n
				totalLength+=w.length();//умма длин слов котор ищем
				/*if(n.toLowerCase().contains(w.toLowerCase())){
					found=true;	
					foundLength+=w.length();
				}*/
				for(int i=w.length()/2+w.length()%2; i>0; i--) {
					 String w1=w.substring(0, w.length()/2+i);
					 //System.out.println(w1);
					 if(n.toLowerCase().contains(w1.toLowerCase())) {
						 found=true;
						 foundLength+=w1.length();
						 break;
					 }
							
				}
			}
			int procent=(int)Math.round(100d*foundLength/totalLength);
			if (found)
				System.out.printf("%s contains %s %d%%\n", n, fio,procent);
		}
		
		
		
	}

}
