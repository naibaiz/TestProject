package org.OracleTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sql.interview.MyHashMap;
import com.sql.interview.MyQueue;
import com.sql.interview.MyStack;

import junit.framework.Assert;

public class AppTwo {

	int fvar;
	int sum = 0;
	static int cvar;
	private static final String filePath = "G:\\cleanbuild\\TestProject\\OracleTest\\src\\main\\resources\\course.txt";

	public static void main(String[] args) throws Exception {

		HashSet<String> sss = new HashSet<String>();
		MyHashMap<Integer, String> hashMapCustom = new MyHashMap<Integer, String>();
		hashMapCustom.put(21, "12");
		hashMapCustom.put(25, "121");
		hashMapCustom.put(30, "151");
		hashMapCustom.put(33, "15");
		hashMapCustom.put(35, "89");
		hashMapCustom.put(null, "89");
		System.out.println(hashMapCustom);

		System.out.println("value corresponding to key 21=" + hashMapCustom.get(21));
		System.out.println("value corresponding to key 51=" + hashMapCustom.get(51));
		System.out.println("value corresponding to key null=" + hashMapCustom.get(null));

		System.out.println("\n\nvalue corresponding to key 21 removed: " + hashMapCustom.remove(21));
		System.out.println("value corresponding to key 51 removed: " + hashMapCustom.remove(51));
		System.out.println(hashMapCustom);

		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(8);
		stack.push(9);
		stack.push(0);
		stack.display();
		System.out.println("poping: " + stack.pop());
		stack.display();
		stack.push(3);
		stack.push(4);
		System.out.println("peeking: " + stack.peek());
		stack.display();
		
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(8);
		queue.display();
		
		System.out.println("removing: " + queue.remove());
		queue.display();
		queue.add(3);
		queue.add(4);
		System.out.println("peeking: " + queue.peek());
		queue.display();
		// Q2 file I/O
		int i;
		char c;
		try {
			FileInputStream fis = new FileInputStream(filePath);
			InputStreamReader isr = new InputStreamReader(fis);
			while (isr.ready()) { // line n1
				isr.skip(2);
				i = isr.read();
				c = (char) i;
				System.out.print(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Q7 JDBC
		try {
			// DriverManager.getDriver(url);
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}
		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		Statement st = null;
		Statement stNew = null;
		connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/OracleTest", "naibaiz",
				"uzrXen11");
		String query = "SELECT id FROM Employee";
		try (Statement stmt = (Statement) connection.createStatement()) {
			st = (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stNew = (Statement) connection.createStatement();

			st.execute("SELECT * FROM Employee");
			ResultSet rst = stmt.executeQuery(query);
			// stmt.executeQuery("SELECT * FROM Student");
			while (rst.next()) {
				// process the results
				System.out.println("Employee ID: " + rst.getInt("id"));
			}

			ResultSet rs = st.getResultSet();
			while (rs.next()) {
				if (rs.getInt(1) == 2) {
					rs.updateString(2, "Jack");
				}
			}
			rs.absolute(2);
			System.out.println(rs.getInt(1) + " " + rs.getString(2));

			stNew.executeUpdate("INSERT INTO student VALUES (102, 'Kelvin')");
		} catch (SQLException ex) {
			System.out.println("Exception is raised");
		} finally {
			if (st != null) {
				st.close();
			}
			if (stNew != null) {
				stNew.close();
			}
			if (connection != null) {
				connection.close();
			}
		}

		// Q8
		List<String> colors = Arrays.asList("red", "green", "yellow");
		Predicate<String> test = n -> {
			System.out.println("Searching...");
			return n.contains("red");
		};
		System.out.println();
		colors.stream().filter(ch -> ch.length() > 3).allMatch(test);

		// Q11
		Address address = null;
		Optional<Address> addrs1 = Optional.ofNullable(address);
		Employee e1 = new Employee(addrs1);
		String eAddress = (addrs1.isPresent()) ? addrs1.get().getCity() : "City Not available";
		System.out.println(eAddress);

		// Q12
		try (ImageScanner ir = new ImageScanner(); ImagePrinter iw = new ImagePrinter()) {
			ir.scanImage();
			iw.printImage();
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		System.out.println();

		// Q14
		fly(() -> new Bird());
		fly(Penguin::new);

		// Q18
		ZonedDateTime depart = ZonedDateTime.of(2015, 1, 15, 3, 0, 0, 0, ZoneId.of("UTC-7"));
		ZonedDateTime arrive = ZonedDateTime.of(2015, 1, 15, 9, 0, 0, 0, ZoneId.of("UTC-5"));
		long hrs = ChronoUnit.HOURS.between(depart, arrive); // line n1
		System.out.println("Travel time is" + hrs + "hours");

		// Q19
		List<Product> products = new ArrayList<Product>(
				Arrays.asList(new Product(1, 10), new Product(2, 30), new Product(2, 30)));
		Product p = products.stream().reduce(new Product(4, 0), (p1, p2) -> {
			p1.price += p2.price;
			return new Product(p1.id, p1.price);
		});
		products.add(p);
		products.stream().parallel().reduce((p1, p2) -> p1.price > p2.price ? p1 : p2).ifPresent(System.out::println);

		// 20
		USCurrency usCoin = USCurrency.DIME;
		System.out.println(usCoin.getValue());

		// 25
		Vehicle v = new SolarVehicle(1, "sss");
		v.ride();

		// 28
		try {
			Candidate ca1 = new Candidate("James", 20);
			Candidate ca2 = new Candidate("Will", 32);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// Q30
		UnaryOperator<Double> uo1 = s -> s * 2;// line n1
		List<Double> loanValues = Arrays.asList(1000.0, 2000.0);
		loanValues.stream().filter(lv -> lv >= 1500).map(lv -> uo1.apply(lv)).forEach(s -> System.out.print(s + " "));

		// Q34
		String names[] = new String[3];
		names[0] = "Mary Brown";
		names[1] = "Nancy Red";
		names[2] = "Jessy Orange";
		for (String name : names) {
			try {
				String pwd = name.substring(0, 3) + name.substring(6, 10);
				System.out.println(pwd);
			} catch (StringIndexOutOfBoundsException e) {
				System.out.println("String out of limits");
			}
		}

		// Q35
		List<String> strs = Arrays.asList("Java", "Java EE", "Java ME");
		Predicate<String> cf1 = s -> s.length() > 3;
		Predicate cf2 = new CourseFilter() { // line n1
			public boolean test(String s) {
				return s.contains("Java");
			}
		};
		long cc = strs.stream().filter(cf1).filter(cf2)// line n2
				.count();
		System.out.println(cc);

		// Q36

		// Q38
		try (Folder f = new Folder()) {
			f.open();
		}

		// 40
		Stream<Path> files = Files.walk(Paths.get(System.getProperty("user.home")));
		// files.forEach(fName -> {// line n1
		// try {
		// Path aPath = fName.toAbsolutePath();// line n2
		// System.out.println(fName + ":" + Files.readAttributes(aPath,
		// BasicFileAttributes.class).creationTime());
		// } catch (IOException ex) {
		// ex.printStackTrace();
		// }
		// });

		try {
			doStuff();
		} catch (ArithmeticException | NumberFormatException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// 46
		LocalDate valentinesDay = LocalDate.of(2015, Month.FEBRUARY, 14);
		LocalDate nextYear = valentinesDay.plusYears(1);
		nextYear.plusDays(15); // line n1
		System.out.println(nextYear);

		// 49
		Book b1 = new Book(101, "Java Programing");
		Book b2 = new Book(102, "Java Programing");
		System.out.println(b1.equals(b2)); // line n2

		// 50
		List<Country> couList = Arrays.asList(new Country("Japan", Country.Continent.ASIA),
				new Country("Italy", Country.Continent.EUROPE), new Country("Germany", Country.Continent.EUROPE));
		Map<Country.Continent, List<String>> regionNames = couList.stream().collect(
				Collectors.groupingBy(Country::getRegion, Collectors.mapping(Country::getName, Collectors.toList())));
		System.out.println(regionNames);

		// 51
		Rideable car = Car::new;
		Car vehicle = car.getCar("MyCar");

		// 53
		XX xRef = new YY();
		YY yRef = (YY) xRef;
		yRef.mY();
		xRef.mX();

		// 55
		AppApp t = new AppApp();
		t.doRegister("Mathew", 60);

		// 59
		List<TechName> tech = Arrays.asList(new TechName("Java-"), new TechName("Oracle DB-"), new TechName("J2EE-"));
		Stream<TechName> stre = tech.stream();
		stre.map(a -> a.techName).forEach(System.out::println);

		// 60
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"G:\\cleanbuild\\TestProject\\OracleTest\\src\\main\\resources\\Message.properties");
		prop.load(fis);
		System.out.println(prop.getProperty("welcome1"));
		System.out.println(prop.getProperty("welcome2", "Test"));// line
																	// n1
		System.out.println(prop.getProperty("welcome3"));

		// 66
		List<String> nL = Arrays.asList("Jim", "John", "Jeff");
		Function<String, String> funVal = s -> "Hello : ".concat(s);
		nL.stream().map(funVal).peek(System.out::print);

		// 68
		char x = '8';
		int y = 8;
		System.out.println(doMsg(x));
		System.out.println(doMsg(y));

		// 69
		Stream<List<String>> iStr = Stream.of(Arrays.asList("1", "John"), Arrays.asList("2", null));
		// Stream<String> nInSt = iStr.flatMapToInt((x) -> x.stream());
		// nInSt.forEach(System.out::print);

		// 70
		IntStream stream = IntStream.of(1, 2, 3);
		IntFunction<IntUnaryOperator> inFu = sx -> sy -> sx * sy;// line n1
		IntStream newStream = stream.map(inFu.apply(10));// line n2
		newStream.forEach(System.out::print);

		// 71
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		System.out.print("Enter GDP: ");
//		int GDP = (br.read());

		// 73
		ForkJoinPool fjPool = new ForkJoinPool();
		int data[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// fjPool.invoke(new Sum(data, 0, data.length));

		// 74
		List<Integer> nums = Arrays.asList(10, 20, 8);
		System.out.println(nums.stream().max(Comparator.comparing(a -> a)).get()
		// line n1
		);

		// 75
		Map<Integer, String> books = new TreeMap<>();
		books.put(1007, "A");
		books.put(1002, "C");
		books.put(1001, "B");
		books.put(1003, "B");
		System.out.println(books);

		// 76
		String str = "Java is a programming language";
		ToIntFunction<String> indexVal = str::indexOf; // line n1
		int xxxx = indexVal.applyAsInt("Java");// line n2
		System.out.println(xxxx);

		// 82
		BiFunction<Integer, Double, Integer> val = (t1, t2) -> t1 + t2.intValue();// line n1
		System.out.println(val.apply(10, 10.5));

		// 84
		Path path1 = Paths.get("/app/./sys/");
		Path res1 = path1.resolve("log");
		Path path2 = Paths.get("/server/exe/");
		Path res2 = path2.resolve("/readme/");
		System.out.println(res1);
		System.out.println(res2);

		// 85
		Locale loc4 = Locale.UK;
		Locale loc5 = new Locale("ru", "RU");

		// 87
		TestTest<String> type = new TestTest<>();
		TestTest type1 = new TestTest();// line n1
		type.set("Java");
		type1.set(100);// line n2
		System.out.print(type.get() + " " + type1.get());

		// 88
		AppTwo at = new AppTwo();
		at.fvar = 10;
		cvar = 10;

		at.fvar = 200;
		AppTwo.cvar = 400;

		// 90
		ExecutorService es = Executors.newFixedThreadPool(4); // line n1
		Future f1 = es.submit(new CallerThread("Call"));
		String strrr = f1.get().toString();
		System.out.println(strrr);

		// 93
		List<Student> stds = Arrays.asList(new Student("Jessy", "Java ME", "Chicago"),
				new Student("Helen", "Java EE", "Houston"), new Student("Mark", "Java ME", "Chicago"));
		stds.stream().collect(Collectors.groupingBy(Student::getCourse)).forEach((src, res) -> System.out.println(src));

		// 95
		Customer c1 = new Customer("Larry", "Smith");
		Customer c2 = new Customer("Pedro", "Gonzales");
		Customer c3 = new Customer("Penny", "Jones");
		Customer c4 = new Customer("Lars", "Svenson");
		c4 = null;
		c3 = c2;
		System.out.println(Customer.getCount());

		// 98
		Moveable m = n -> System.out.println("Running" + n);
		m.walk(100);
		m.run(20);

		// 99
		Stream<Path> paths = Stream.of(Paths.get("G:/data.doc"), Paths.get("G:/data.txt"), Paths.get("G:/data.xml"));
		paths.filter(s -> s.toString().endsWith("txt")).forEach(s -> {
			try {
				Files.readAllLines(s).stream().forEach(System.out::println); // line n1
			} catch (IOException e) {
				System.out.println("Exception");
			}
		});
		// 101
		Path p1 = Paths.get("/Pics/MyPic.jpeg");
		System.out.println(p1.getNameCount() + ":" + p1.getName(0) + ":" + p1.getName(1) + p1.getFileName());

		// 102
		List<Integer> values = Arrays.asList(1, 2, 3);
		values.stream().map(n -> n * 2)// line n1
				.peek(System.out::print)// line n2
				.count();

		// 104
		List<String> empDetails = Arrays.asList("100, Robin, HR", "200, Mary, AdminServices", "101, Peter, HR");
		empDetails.stream().filter(s -> s.contains("1")).sorted().forEach(System.out::println); // line n1

		// 105
		String[] strArray = new String[] { "Tiger", "Rat", "Cat", "Lion" };
		// line n1
		Arrays.sort(strArray, CheckClass::checkValue);
		for (String s : strArray) {
			System.out.print(s + " ");
		}
		System.out.println();
		// 106
		String ssssss = "Hello World";
		// System.out.println(ssssss.charAt(11));

		// 109
		Set<Vehicle> vehicles = new TreeSet<>();
		vehicles.add(new Vehicle(10123, "Ford"));
		vehicles.add(new Vehicle(10124, "BMW"));
		System.out.println(vehicles);

		// 110
		List<String> listVal = Arrays.asList("Joe", "Paul", "Alice", "Tom");
		System.out.println(listVal.stream().filter(xxx -> xxx.length() > 3).count() // line n1
		);

		// 111
		List<String> strstr = Arrays.asList("my", "pen", "is", "your", "pen");
		Predicate<String> testttt = s -> {
			int iii = 0;
			boolean result = s.contains("pen");
			System.out.print(iii++ + ":");
			return result;
		};
		strstr.stream().filter(testttt).findFirst().ifPresent(System.out::print);

		// 114
		int rateOfInterest = 0;
		String accountType = "LOAN";
		switch (accountType) {
		case "RD":
			rateOfInterest = 5;
			break;
		case "FD":
			rateOfInterest = 10;
			break;
		default:
			// assert false : "No interest for this account"; // line n1 works with -ea
			// option
		}
		System.out.println("Rate of interest: " + rateOfInterest);

		// 115
		List<String> codes = Arrays.asList("DOC", "MPEG", "JPEG");
		codes.forEach(ccc -> System.out.print(ccc + " "));
		String fmt = codes.stream().filter(s -> s.contains("PEG")).reduce((s, tt) -> s + tt).get();
		System.out.println("\n" + fmt);

		// 117
		List<Emp> li = Arrays.asList(new Emp("Sam", 20), new Emp("John", 60), new Emp("Jim", 51));
		Predicate<Emp> agVal = s -> s.getEAge() > 50;// line n1
		li = li.stream().filter(agVal).collect(Collectors.toList());
		Stream<String> names1 = li.stream().map(Emp::getEName);// line n2
		names1.forEach(n -> System.out.print(n + " "));

		// 118
		ExecutorService eses = Executors.newFixedThreadPool(2);
		Future ff1 = eses.submit(new Callerer("Call"));
		Future ff2 = eses.submit(new Runnerer("Run"));
		String str1 = (String) ff1.get();
		String str2 = (String) ff2.get();// line n1
		System.out.println(str1 + ":" + str2);

		// 119
		Map<Integer, String> unsortMap = new HashMap<>();
		unsortMap.put(10, "z");
		unsortMap.put(5, "b");
		unsortMap.put(1, "d");
		unsortMap.put(7, "e");
		unsortMap.put(50, "j");
		Map<Integer, String> treeMap = new TreeMap<Integer, String>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.compareTo(o1);
			}
		});
		treeMap.putAll(unsortMap);
		// treeMap.entrySet().forEach(sts -> System.out.println(sts));
		for (Map.Entry<Integer, String> entry : treeMap.entrySet()) {
			System.out.print(entry.getValue() + " ");
		}

		// 120
		List<Integer> codess = Arrays.asList(10, 20);
		UnaryOperator<Double> uo = s -> s + 10.0;
		// codess.replaceAll(uo); // can not compile due to List type difference
		codes.forEach(ccc -> System.out.println(ccc));

		// 121

		// 122
		List<EmpEmp> emp = Arrays.asList(new EmpEmp("John", "Smith"), new EmpEmp("Peter", "Sam"),
				new EmpEmp("Thomas", "Wale"));
		emp.stream().sorted(Comparator.comparing(EmpEmp::getfName).reversed().thenComparing(EmpEmp::getlName))// line n1
				.collect(Collectors.toList());
		emp.forEach(e -> System.out.println(e.toString()));

		// 125
		int a = 10;
		int b = -1;
		assert (b >= 1) : "Invalid Denominator";
		int c11 = a / b;
		System.out.println(c11);

		// 124
		Vehhicle vvv = new Vehhicle(100);
		vvv.increSpeed(60);

		// 127
		int[] arrint = { 100, 100 };
		try {
			int arr2 = arrint[2];
		} catch (IllegalArgumentException eI) {
			System.out.println("Illegal");
		} catch (Exception e) {
			System.out.println("Exxx");
		}

		// 128
		Path source = Paths.get(filePath);
		Path destination = Paths.get("G:/");
		// Files.copy(source, destination.resolve(source.getFileName())); // this is how
		// to copy a file to a directory with
		// // original file name.
		System.out.println(Arrays.equals(findLongestUniqueStringArray("aabbbccdde"), new int[] { 2, 3 }));
		System.out.println(Arrays.equals(findLongestUniqueStringArray("aaaaabbbccdde"), new int[] { 0, 5 }));
		System.out.println(Arrays.equals(findLongestUniqueStringArray("aabbbccccccccdde"), new int[] { 5, 8 }));
		System.out.println(Arrays.equals(findLongestUniqueStringArray("aabbbccddeeeeeee"), new int[] { 9, 7 }));

	}

	static String doMsg(int i) {
		return "Good luck!";
	}

	static String doMsg(char i) {
		return "Good day!";
	}

	static void doCheck(int number) {
		int sum = 0;
		if (number % 2 == 0) {
			// break;
		} else {

		}
	}

	static int[] findLongestUniqueStringArray(String string) {
		int startPosition = -1;
		int longestLengthString = 0;

		char previous = '\t';
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c != previous) {
				if (i == 0) {
					startPosition = 0;
					count = 1;
					longestLengthString = 1;
				} else { // start the count again
					count = 1;
				}
			} else { // alread exist
				count += 1;
			}
			if (longestLengthString < count) {
				longestLengthString = count;
				startPosition = i - longestLengthString + 1;
			}
			previous = c;
		}

		return new int[] { startPosition, longestLengthString };
	}

	static void fly(Supplier<Bird> bird) {
		bird.get().fly();
	}

	static void doStuff() throws ArithmeticException, NumberFormatException, Exception {
		if (Math.random() > -1)
			throw new Exception("Try again");
	}

}

class Employee {
	Optional<Address> address;

	Employee(Optional<Address> address) {
		this.address = address;
	}

	public Optional<Address> getAddress() {
		return address;
	}
}

class Address {
	String city = "New York";

	public String getCity() {
		return city;
	}

	public String toString() {
		return city;
	}
}

class ImageScanner implements AutoCloseable {
	public void close() throws Exception {
		System.out.print("Scanner closed.");
	}

	public void scanImage() throws Exception {
		System.out.print("Scan.");
		throw new Exception("Unable to scan.");
	}
}

class ImagePrinter implements AutoCloseable {
	public void close() throws Exception {
		System.out.print("Printer closed.");
	}

	public void printImage() {
		System.out.print("Print.");
	}
}

class Bird {
	public void fly() {
		System.out.print("Can fly");
	}
}

class Penguin extends Bird {
	public void fly() {
		System.out.print("Cannot fly");
	}
}

class Product {
	int id;
	int price;

	public Product(int id, int price) {
		this.id = id;
		this.price = price;
	}

	public String toString() {
		return id + ":" + price;
	}
}

interface CourseFilter extends Predicate<String> {
	public default boolean test(String str) {
		return str.equals("Java");
	}
}

class Book {
	int id;
	String name;

	public Book(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public boolean equals(Object obj) { // line n1
		boolean output = false;
		Book b = (Book) obj;
		if (this.name.equals(b.name)) {
			output = true;
		}
		return output;
	}
}

class Country {
	public enum Continent {
		ASIA, EUROPE
	}

	String name;
	Continent region;

	public Country(String na, Continent reg) {
		name = na;
		region = reg;
	}

	public String getName() {
		return name;
	}

	public Continent getRegion() {
		return region;
	}
}

interface Rideable {
	Car getCar(String name);
}

class Car {
	private String name;

	public Car(String name) {
		this.name = name;
	}
}

class XX {
	public void mX() {
		System.out.println("Xm1");
	}
}

class YY extends XX {
	public void mX() {
		System.out.println("Xm2");
	}

	public void mY() {
		System.out.println("Ym");
	}
}

class AppApp {
	public void doRegister(String name, int age) throws UserException, AgeOutOfLimitException {
		if (name.length() < 6) {
			throw new UserException();
		} else if (age >= 60) {
			System.out.println("User is over 60.");
			// throw new AgeOutOfLimitException();
		} else {
			System.out.println("User is registered.");
		}
	}
}

class UserException extends Exception {
}

class AgeOutOfLimitException extends UserException {
}

class TechName {

	String techName;

	TechName(String techName) {
		this.techName = techName;
	}
}

class Sum extends RecursiveAction {

	@Override
	protected void compute() {
		// TODO Auto-generated method stub

	}

}

class TestTest<T> {
	private T t;

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}
}

class CallerThread implements Callable<String> {
	String str;

	public CallerThread(String s) {
		this.str = s;
	}

	public String call() throws Exception {
		return str.concat("Call");
	}
}

class Student {
	String course, name, city;

	public Student(String name, String course, String city) {
		this.course = course;
		this.name = name;
		this.city = city;
	}

	public String toString() {
		return course + ":" + name + ":" + city;
	}

	public String getCourse() {
		return this.course;
	}
}

class Customer {
	private String fName;
	private String lName;
	private static int count;

	public Customer(String first, String last) {
		fName = first;
		lName = last;
		++count;
	}

	static {
		count = 0;
	}

	public static int getCount() {
		return count;
	}
}

class Canvas implements Drawable {
	public void draw() {
	}
}

abstract class Board extends Canvas {
}

class Paper extends Canvas {
	protected void draw(int color) {
	}
}

class Frame extends Canvas implements Drawable {
	public void resize() {
	}
}

interface Drawable {
	public abstract void draw();
}

interface Moveable<Integer> {
	default void walk(Integer distance) {
		System.out.println("Walking");
	}

	void run(Integer distance);
}

class CheckClass {
	public static int checkValue(String s1, String s2) {
		return s1.length() - s2.length();
	}
}

@SuppressWarnings("rawtypes")
class Vehicle implements Comparable {
	int vno;
	String name;

	public Vehicle(int vno, String name) {
		this.vno = vno;
		this.name = name;
	}

	public String toString() {
		return vno + ":" + name;
	}

	protected void ride() throws Exception {// line n1
		System.out.println("Happy Journey!");
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Vehicle) {
			return this.vno - ((Vehicle) o).vno;
		} else {
			return 0;
		}
	}
}

class SolarVehicle extends Vehicle {
	public SolarVehicle(int vno, String name) {
		super(vno, name);
	}

	public void ride() throws Exception {// line n2
		super.ride();
	}
}

class FuelNotAvailException extends Exception {
}

class Emp {
	private String eName;
	private Integer eAge;

	Emp(String eN, Integer eA) {
		this.eName = eN;
		this.eAge = eA;
	}

	public Integer getEAge() {
		return eAge;
	}

	public String getEName() {
		return eName;
	}
}

class Callerer implements Callable<String> {
	String str;

	public Callerer(String s) {
		this.str = s;
	}

	public String call() throws Exception {
		return str.concat("Caller");
	}
}

class Runnerer implements Runnable {
	String str;

	public Runnerer(String s) {
		this.str = s;
	}

	public void run() {
		System.out.println(str.concat("Runner"));
	}
}

abstract class Shape {
	Shape() {
		System.out.println("Shape");
	}

	protected void area() {
		System.out.println("Shape");
	}
}

class Square extends Shape {
	int side;

	Square(int side) {
		/* insert code here */
		this.side = side;
	}

	public void area() {
		System.out.println("Square");
	}
}

class Rectangle extends Square {
	int len, br;

	Rectangle(int x, int y) {
		/* insert code here */
		super(x);
		len = x;
		br = y;
	}

	public void area() {
		System.out.println("Rectangle");
	}
}

class EmpEmp {
	String fName;
	String lName;

	public EmpEmp(String fn, String ln) {
		fName = fn;
		lName = ln;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String toString() {
		return fName + " " + lName;

	}
}

class Vehhicle {
	int distance;// line n1

	Vehhicle(int x) {

		this.distance = x;
	}

	public void increSpeed(int time) {// line n2
		int timeTravel = time;// line n3
		class Car {
			int value = 0;

			public void speed() {
				value = distance / timeTravel;
				System.out.println("Velocity with new speed" + value + "kmph");
			}
		}
		new Car().speed();
	}
}

class DBHandler {
	DBConfiguration configureDB() {
		return new DBConfiguration();
	}
}

enum USCurrency {
	PENNY(1), NICKLE(5), DIME(10), QUARTER(25);

	private int value;

	USCurrency(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

class MissingInfoException extends Exception {
}

class AgeOutOfRangeException extends Exception {
}

class Candidate {
	String name;
	int age;

	public Candidate(String name, int age) throws Exception {

	}
}

final class Folder implements AutoCloseable {// line n1
	// line n2
	public void open() {
		System.out.print("Open");
	}

	@Override
	public void close() {
		System.out.print("Close");

	}
}

class Sum2 extends RecursiveAction { // line n1
	static final int THRESHOLD_SIZE = 3;
	int stIndex, lstIndex;
	int[] data;

	public Sum2(int[] data, int start, int end) {
		this.data = data;
		this.stIndex = start;
		this.lstIndex = end;
	}

	protected void compute() {
		int sum = 0;
		if ((lstIndex - stIndex) <= THRESHOLD_SIZE) {
			for (int i = stIndex; i < lstIndex; i++) {
				sum += data[i];
			}
			System.out.println(sum);
		} else {
			new Sum2(data, stIndex + THRESHOLD_SIZE, lstIndex).fork();
			new Sum2(data, stIndex, Math.min(lstIndex, stIndex + THRESHOLD_SIZE)).compute();
		}
	}
}

class Test<T> {
	private T t;

	public T get() {
		return t;
	}

	public void set(T t) {
		this.t = t;
	}
}

class MySingleTon {
	HashSet<String> sss = new HashSet<String>();
	private static MySingleTon instance;

	private static class MySingleTonHelper {
		private static final MySingleTon INSTANCE = new MySingleTon();
	}

	private MySingleTon() {

	}

	public static MySingleTon getMySingleTonInstance() {
		// if (instance == null) {
		// synchronized (MySingleTon.class) {
		// if (instance == null) {
		// instance = new MySingleTon();
		// }
		// }
		// }
		// return instance;

		return MySingleTonHelper.INSTANCE;
	}
}