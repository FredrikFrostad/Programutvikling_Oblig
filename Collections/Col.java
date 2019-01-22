import java.util.*;
import java.lang.*;

public class Col {

	public static void main(String[] args) {


		//Oppgave 1:

		{
			List<Integer> liste = new ArrayList<>();
			liste.add(1);
			liste.add(5);
			liste.add(5);
			liste.add(2);

			System.out.println("\nSkriver ut arraylist: ");
			System.out.println(liste);

			List<Integer> lenketliste = new LinkedList<>();
			lenketliste.add(1);
			lenketliste.add(5);
			lenketliste.add(5);
			lenketliste.add(2);

			System.out.println("\nSkriver ut lenketliste: ");
			System.out.println(lenketliste);

			/**
			Skriver ut arraylist:
			[1, 5, 5, 2]

			Skriver ut lenketliste:
			[1, 5, 5, 2]

			Utskriften til konsoll er so forventet den samme for b
			både linkedlist og arraylist. Det er fordelaktig å bruke 
			linkedlist i de tilfellene hvor vi alltid skal iterere over hele 
			arrayet og hurtig gjøre endringer i det(legge til / fjerne verdier)

			Dersom vi ønsker å gjøre mange direkte aksesser mot indekser
			er Arraylist det best egnede alternativet.
			**/

			Set<Integer> treeset = new TreeSet<>();
			treeset.add(1);
			treeset.add(5);
			treeset.add(5);
			treeset.add(2);

			System.out.println("\nSkriver ut treeset: ");
			System.out.println(treeset);

			/**
			Utskriften fra TreeSet objektet er følgende: 
			
			Skriver ut treeset:
			[1, 2, 5]

			Denne utskriften er forskjellig fordi TreeSet ikke er en liste,
			men en mengde. Dennes toString metode skriver ut alle de
			distinkte elementene som er en del av mengden, sortert i naturlig orden.
			Det tas ikke hensyn til antall forekomster av hvert element.
			**/
		}

		//Oppgave 2:

		{
			String str1 = new String("OsloMet");
			String str2 = new String("OsloMet");
			boolean erlik = str1 == str2;
			System.out.println("\n" + erlik);

			/**
				Koden over vil ikke fungere som forventet, fordi vi
				ikke sammenlikner strengenes innhold. Når vi bruker
				obj1 == obj2 sammenllikner vi referansevariablene, ikke
				objektets innhold.
			**/

			//Dersom vi vil sammenlikne innholdet i tekststrengene må
			//vi gjøre følgende:

			String str3 = new String("OsloMet");
			String str4 = new String("OsloMet");
			boolean equals = str3.equals(str4);
			System.out.println("\n" + equals);

			//Denne gangen skrives det ut true til konsollen, som
			// er forventet oppførsel.

		}

		//Oppgave 3:

		{
			Car car1 = new Car("Opel", 10);
			Car car2 = new Car("Ford", 9);
			Car car3 = new Car("Audi", 3);
			Car car4 = new Car("Toyota", 7);
			Car car5 = new Car("Toyota", 4);

			Set<Car> set1 = new TreeSet<Car>();
			set1.add(car1);
			set1.add(car2);
			set1.add(car3);
			set1.add(car4);

			System.out.println("Utskrift fra treeset: " + set1);


		//Oppgave 4:

			List<Car> carlist= new ArrayList<>(set1);
			//carlist.addAll(set1);
			carlist.add(new Car("Toyota", 4));

			Collections.sort(carlist, new sortByManuf());

			System.out.println("Printer arraylist: " + carlist);
		}

		//Oppgave 5:

		//Se klasse Student lenger ned i filen
		
		//Oppgave 6 / 7 /8:

		{
			StudentGroup group = new StudentGroup();
			group.addStudent(new Student("Fredrik", "Frostad", 349503, 34), 80);
			group.addStudent(new Student("Fredrik", "Olsen", 308533, 23), 99);
			group.addStudent(new Student("Fredrik", "Olsen", 308533, 23), 99);
			group.addStudent(new Student("Ole", "Frostad", 309873, 22), 45);
			group.addStudent(new Student("Kjell", "Pettersen", 305853, 22), 34);
			group.addStudent(new Student("Nina", "Haugen", 329303, 19), 77);
			group.addStudent(new Student("Nina", "Haugen", 329303, 19), 77);

			System.out.println(group);
			group.listStudentsByGrade();
		}
	}
}

class Car implements Comparable<Car>{

	private String manfctr;
	private int age;

	public Car(String manfctr, int age) {
		this.manfctr = manfctr;
		this.age = age;
	}

	public String getManfctr() {return this.manfctr;}

	public int getAge() {return this.age;}

	@Override
	public int compareTo(Car other) {
		return manfctr.compareTo(other.manfctr);
	}

	@Override
	public String toString() {
		return "\nManufacturer: " + manfctr + "\nAge: " + age + "\n";
	}
}
	/**
	**Comparator klasse for Car som gir naturlig orden sortering
	**på Manufacturer, og deretter på Age
	**/
	class sortByManuf implements Comparator<Car> {

		@Override
		public int compare(Car c1, Car c2) {
			int res = c1.compareTo(c2);
			return res != 0 ? res : 
					Integer.toString(c1.getAge()).compareTo(Integer.toString(c2.getAge()));
		}
	}

	class Student {
		
		private String firstName;
		private String lastName;
		private int studentID;
		private int age;

		public Student(String frstnm, String lstnm, int ID, int age) {
			this.firstName = frstnm;
			this.lastName = lstnm;
			this.studentID = ID;
			this.age = age;		
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (this.getClass() != obj.getClass()) return false;

			Student other = (Student) obj;
			if (studentID == other.studentID) return true;

			return false;
		}

		@Override
		public int hashCode() {
			return 17*firstName.hashCode() + 31*lastName.hashCode()
				+ 41*Integer.hashCode(studentID) + Integer.hashCode(age);
		}

		@Override
		public String toString() {
			return firstName + " " + lastName + ", " + studentID
				+ ", " + "alder: " + age;
		}

		public String getFirstNm() {return this.firstName;}
		public String getLastNm() {return this.lastName;}
		public int getID() {return this.studentID;}
		public int getAge() {return this.age;}

	}

	//Klasse som rpresenterer en gruppe studenter ved hjelp av
	// et hashMap.
	class StudentGroup {

		private HashMap<Student, Integer> studGrp = new HashMap<>();

		public boolean addStudent(Student stud, Integer grade) {
			if (grade < 0 || grade > 100) return false;

			try {
				studGrp.put(stud, grade);
			} catch(NumberFormatException e) {
				System.out.println("Ikke en gyldig karakter!");
			}
			return true;
		}

		public void listStudentsByGrade() {
			ArrayList<Map.Entry<Student, Integer>> list = new ArrayList<>(studGrp.entrySet());
			list.sort(Map.Entry.comparingByValue(new gradeComparator()));
			System.out.println("\n" + list);
		}	

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			
			for (HashMap.Entry<Student, Integer> entry : this.studGrp.entrySet()) {
				sb.append(entry.getKey().toString()).append(", Grade: ")
					.append(Integer.toString(entry.getValue())+"\n");
			}
			sb.append("]");
		return sb.toString();
		}
 	}

 	class gradeComparator implements Comparator<Integer> {

 		@Override
 		public int compare(Integer int1, Integer int2) {
 			return int1.compareTo(int2);
 		}
 	}