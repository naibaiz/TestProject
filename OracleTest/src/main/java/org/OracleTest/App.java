package org.OracleTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 * 
 */
public class App {
	static String[] args = {"lazy", "lion", "is", "always"};
	public static void main(String[] args) {
		System.out.println(args[1] + " " + args[2] + " " + args[3] + " jumping");
		System.out.println("Hello World!");
		// q23
		String[] arr = new String[4];
		arr[1] = "Unix";
		arr[2] = "Linux";
		arr[3] = "Solaris";

		for (String a : arr) {
			System.out.println(a + " ");
		}

		int x = 9;
		if (x > 10) {
			System.out.println(">");
		} else if (x < 10) {
			System.out.println("<");
		} else {
			System.out.println("=");
		}

		System.out.println(x > 10 ? ">" : x < 10 ? "<" : "=");

		// Q17
		int iArr[] = { 65, 68, 69 };
		iArr[2] = iArr[0];
		iArr[0] = iArr[1];
		iArr[1] = iArr[2];

		for (int element : iArr) {
			System.out.println(element + " ");
		}

		// Q25
		boolean isChecked = false;
		int array[] = { 1, 3, 5, 7, 8, 9 };
		int index = array.length;
		while (index > 0) {
			if (array[index - 1] % 2 == 0) {
				isChecked = true;
			}
			index--;
		}
		System.out.println(array[index] + " " + isChecked);

		// Q28
		String ta = "A ";
		ta = ta.concat("B ");
		System.out.println(ta);
		String tb = "C ";
		ta = ta.concat(tb);
		System.out.println(ta);
		ta.replace('C', 'D');
		System.out.println(ta);
		ta = ta.concat(tb);
		System.out.println(ta);

		// Q36
		// chs S S
		// S S S S S
		String[][] chs = new String[2][];
		chs[0] = new String[2];
		chs[1] = new String[5];
		System.out.println(chs.length);
		int i = 97;
		for (int a = 0; a < chs.length; a++) {
			for (int b = 0; b < chs.length; b++) {
				chs[a][b] = "" + i;
				i++;
			}
		}
		for (String[] c : chs) {
			for (String ca : c) {
				System.out.print(ca);
			}
			System.out.println();
		}
		// Q41
		int[] xx = { 6, 7, 8 };
		for (int xa : xx) {
			System.out.print(xa);
			xa++;
		}
		System.out.println();

		// Q42
		List<Alpha> strs = new ArrayList<Alpha>();
		strs.add(new Alpha());
		// strs.add(new Beta()); compilation fails
		// strs.add(new Gemma()); compilation fails

		int ii = 10;
		int j = 20;
		int k = j += ii / 5;
		System.out.println(ii + " " + j + " " + k);

		Planet[] planets = { new Planet("Mercury", 0), new Planet("Venus", 0), new Planet("Earth", 1),
				new Planet("Mars", 2) };
		System.out.println(planets);
		System.out.println(planets[2]);
		System.out.println(planets[2].moon);
		// Q58
		int var = 9;
		if (var++ < 10) {
			System.out.println(var + ", Hello World");
		} else {
			System.out.println(var + ", Hello Universe");
		}
		// Q59
		X xref = new Y();
		Y yref = (Y) xref;

		yref.my();
		xref.mx();

		// for (;;) {
		//
		// }

		// Q66
		List objs = new ArrayList();
		contract c1 = new Super();
		contract c2 = new Sub();
		Super s1 = new Sub();

		objs.add(c1);
		objs.add(c2);
		objs.add(s1);

		for (Object o : objs) {
			System.out.println(o.getClass().getName());
		}

		// Q71
		// LocalDate
		Period pp = Period.ofDays(1);

		// Q76
		double p, b, h;
		if (Triangle.area == 0) {

		}
		// Triangle.area = p * b * h;

		// Q77
		Sun obj2 = new Sun();
		Star obj3 = obj2;
		((Sun) obj3).doStuff();
		((Star) obj2).doStuff();
		((Universe) obj2).doStuff();

		// Q79
		String color = "teal";
		System.out.println(color.equals("Teal"));
		 switch (color) {
		 case "Teal":
		 System.out.println("Found Teal");
		 }

		// Q83
		int iVar = 100;
		float fVar = 100.0f;
		double dVar = 123;
		// iVar = fVar;
		fVar = iVar;
		dVar = fVar;
		// fVar = dVar;
		dVar = iVar;
		// iVar = dVar;

		// Q100
		int[] nums = new int[2];
		nums[0] = 10;
		nums[1] = 20;

		nums = new int[4];
		nums[2] = 30;
		nums[3] = 40;
		for (int xxx : nums) {
			System.out.print(xxx);
		}

		// Q109
		List<String> aL = new ArrayList<String>();
		aL.add("SE");
		aL.add("EE");
		aL.add("ME");
		aL.add("SE");
		aL.add("EE");
		aL.remove("SE");
		System.out.print(aL);

		// Q102
		ArrayList myList = new ArrayList();
		String[] myArray;

		// try {
		// while (true) {
		// myList.add("My String");
		// }
		// } catch (RuntimeException re) {
		// System.out.println("Caught runtime");
		// } catch (Exception e) {
		// System.out.println("Caught ex");
		// }
		// System.out.println("Ready");

		// Q115
		TestField tf1 = new TestField();
		tf1.x = 100;
		tf1.y = 200;
		TestField tf2 = new TestField();
		tf2.doStuff(tf1.x, tf1.y);
		tf1.display();
		tf2.display();

		int bb = 3;
		if (!(bb > 3)) {
			System.out.println("Square");
		}
		{
			System.out.println("Circle");
		}
		System.out.println("...");

		// Q117
		StringBuilder sb1 = new StringBuilder("Duke");
		String str1 = sb1.toString();
		String str2 = new String(str1);
		// String str2 = "Duke";
		// String str2 = sb1.toString();
		// String str2 = str1;
		System.out.println(str1 == str2);

		// Q122
		Caller c = new Caller();
		c.started();
		// c.init(); // not visible

		// Q123
		List<Integer> list = new ArrayList<Integer>();
		list.add(21);
		list.add(13);
		list.add(30);
		list.add(11);
		list.add(2);

		list.removeIf(e -> e % 2 == 0);

		System.out.println(list);

		// Q126
		try {
			doSomething();
		} catch (SpecialException se) {
			System.out.println("SpecialException");
		}

		// Q132
		Tour.main(args);

		// Q134
		int num = 5;
		int sum = 0;
		do {
			sum += num;
		} while ((num--) > 1);
		System.out.println("Sum is: " + sum);

		// Q140
		Integer number = Integer.valueOf("808");

		// Q154
		float[] fltArray = { 10.20f, 20.30f, 30.40f, 50.60f };
		int indexE = 0;
		boolean isFound = false;
		float key = 30.40f;
		// while (indexE <= 4) {
		// if (key == fltArray[indexE]) {
		// indexE++;
		// isFound = true;
		// break;
		// }
		// }
		// while (key == fltArray[indexE++]) {
		// isFound = true;
		// }
		// System.out.println(isFound);
		// while (indexE++ < 5) {
		// if (key == fltArray[indexE]) {
		// isFound = true;
		// }
		// }
		while (indexE < 5) {
			if (key == fltArray[indexE]) {
				isFound = true;
				break;
			}
			indexE++;
		}
		System.out.println(isFound);
		
		//Q156
		String ss = "A";
		switch(ss) {
			case "a":
				System.out.println("simple a");
			default:
				System.out.println("default");
			case "A":
				System.out.println("cap A");
		}
		
		//Q160
		int aaa = -10;
		int bbb = 17;
		int ccc = ++aaa;
		int ddd = bbb--;
		System.out.println(ccc + " " + ddd);
		ccc++;
		ddd--;
		System.out.println(ccc + " " + ddd);
		
		//Q161
		Short sh1 = 160;
		Integer int1 = 400;
		Long l1 = (long)sh1 + int1;
//		String s4 = (String) (l1 * int1); // can not cast long to string
		
		//Q167
		int nnn = 5;
		do {
			System.out.println(nnn-- + " ");
		} while (nnn==0);
		
		//Q171
		Boolean[] boo = new Boolean[2];
		boo[0] = new Boolean("true");
		boo[1] = new Boolean(null);
		System.out.println(boo[0] + " " + boo[1]);
		
		doStuff("9009");
	
		//Q189
		List<Person> persons = Arrays.asList(new Person("Hank", 45), new Person("Charlie", 40), new Person("Smith", 38));
		checkAge(persons, per -> per.getAge() > 40);
		
		//Q204
		String[][] sssss = {{"A", "B", "C"}, {"D", "E"}};
		for (int is = 0; is < sssss.length; is++) {
			for (int js = 0; js < sssss[is].length; js++) {
				System.out.println(sssss[is][js] + "");
				if (sssss[is][js].equals("B")) {
					break;
				}				
			}
			continue;			
		}
		
		//Q205
		List colors = new ArrayList();
		colors.add("Green");
		colors.add("Red");
		colors.add("Blue");
		colors.add("Yellow");
		colors.remove(2);
		colors.add(2, "Cyan");
		System.out.println(colors);
		
		//Q227
		int[] ar1 = {2, 4, 6, 8};
		int[] ar2 = {1, 3, 5, 7, 9};
		ar2 = ar1;
		for(int ar : ar2) {
			System.out.println(ar);
		}
		
		//Q234
		StringBuilder sb11 = new StringBuilder(5);
		String s = "";
		if (sb11.equals(s)) {
			System.out.println("Match 1");
		} else if (sb11.toString().equals(s.toString())) {
			System.out.println("Match 2");
		} else {
			System.out.println("No Match");
		}
		
		LocalDate date1 = LocalDate.now();
		LocalDate date2 = LocalDate.of(2014, 06, 24);
		LocalDate date3 = LocalDate.parse("2014-06-24", DateTimeFormatter.ISO_DATE);
		
//		String dddate = LocalDate.parse("2014-05-04").format(DateTimeFormatter.ISO_DATE_TIME);
		
		
		System.out.println("Date 1 " + date1);
		System.out.println("Date 2 " + date2);
		System.out.println("Date 3 " + date3);
		
//		System.out.println("dddate " + dddate);
		
		//Q42
		List<AAA> str = new ArrayList<AAA>();
		str.add(new AAA());
		str.add(new BBB());
		str.add(new CCC());
		
		for (AAA t : str) {
			System.out.println(t.duStuff("Java"));
		}
		
//		Runnable r = 0 -> {System.out.println(t.duStuff("Java"));};
		
		
	}
	
	public static void checkAge(List<Person> persons, Predicate<Person> predicate) {
		for (Person p : persons) {
			if (predicate.test(p)) {
				System.out.println(p.name + " ");
			}
		}
		
	}

	public static void doStuff(String str) {
		int myNum = 0;
		try {
			String ss = str;
			myNum = Integer.valueOf(str);
		} catch (Exception e) {
			
		}
//		System.out.println("MyStr" + " " + ss + " MyNum " + myNum); compile fails on ss
	}
	public static void doSomething() throws SpecialException {
		int[] arrayInt = new int[4];
		// arrayInt[4] = 13;
	}

	public static List update() {
		return null;
	}
}

class Alpha {
	public String doStuff(String msg) {
		return msg;
	}
}

class SpecialException extends Exception {
	public SpecialException(String msg) {
		super(msg);
	}
}

class Beta {
	public String doStuff(String msg) {
		return msg.replace('a', 'e');
	}
}

class Gemma {
	public String doStuff(String msg) {
		return msg.substring(2);
	}
}

class Planet {
	public int moon = 0;
	public String name = "";

	public Planet(String name, int moon) {
		this.moon = moon;
		this.name = name;

	}
}

class X {
	public void mx() {
		System.out.println("Xm1");
	}
}

class Y extends X {
	public void mx() {
		System.out.println("Xm2");
	}

	public void my() {
		System.out.println("Ym");
	}
}

interface contract {
}

class Super implements contract {
}

class Sub extends Super {
}

class Triangle {
	static double area;
	int b = 2, h = 3;
}

class Star {
	public void doStuff() {
		System.out.println("Twinking star");
	}
}

interface Universe {
	void doStuff();
}

class Sun extends Star implements Universe {
	public void doStuff() {
		System.out.println("Shining sun");
	}

}

class TestField {
	int x, y;

	public void doStuff(int x, int y) {
		this.x = x;
		y = this.y;
	}

	public void display() {
		System.out.println(x + " " + y + ":");
	}
}

class Caller {
	private void init() {
		System.out.println("Initialised...");
	}

	public void started() {
		init();
		System.out.println("Started...");
	}
}

class Tour {
	public static void main(String[] args) {
		System.out.println("Happy journey! " + args[1]);
	}
}

interface DoThingInterface {
	void m1(int n);

	public void m2(int n);
}

class DoThing implements DoThingInterface {
	int x1, x2;

	public DoThing() {
		this.x1 = 0;
		this.x2 = 10;
	}

	public void m1(int p1) {
		x1 += p1;
		System.out.println(x1);

	}

	public void m2(int p1) {
		x2 += p1;
		System.out.println(x2);
	}
}

class Person {
	String name;
	int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
}

class Overloading {
	int x(double d) {
		return 0;
	}
	
//	double x(double d) {
//		return 0.0;
//	}
}

class AAA {
	public String duStuff(String msg) {
		return msg;		
	}
}

class BBB extends AAA {
	public String duStuff(String msg) {
		return msg.replace('a', 'e');
	}
}

class CCC extends AAA {
	public String duStuff(String msg) {
		return msg.substring(2);
	}
}