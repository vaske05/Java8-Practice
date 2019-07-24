package streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JavaStreams {
	
	static String fruitsPath = "C:\\Users\\miva2911\\Desktop\\Java Streams\\gradle\\src\\res\\fruits.txt";
	static String dataPath = "C:\\Users\\miva2911\\Desktop\\Java Streams\\gradle\\src\\res\\data.txt";


	public static void main(String[] args) throws IOException {
		
		System.out.println("Examples: ");
		
		
		System.out.println("---1---");
		// 1. Integer Stream
		IntStream
			.range(1, 10)
			.forEach(System.out::print);
		System.out.println();
		
		
		System.out.println("---2---");
		// 2. Integer Stream with skip
		IntStream
			.range(1, 10)
			.skip(5)
			.forEach(x -> System.out.print(x));
		System.out.println();
		
		
		System.out.println("---3---");
		// 3. Integer Stream with sum
		int sum = IntStream
			.range(1, 10)
			.sum();
		System.out.println(sum);
		
		
		System.out.println("---4---");
		// 4. Stream.of, sorted, and findFirst
		Stream.of("Sara", "Lana", "Ana")
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);
		
		
		System.out.println("---5---");
		// 5. Stream form Array, sort, filter and print
		String[] names = {"Milan", "Darko", "Milos", "Marija", "Nikola", "Ivan", "Martina"};
		String newNames = Arrays.stream(names) // Same as Stream.of
			.filter(x -> x.startsWith("M"))
			.sorted()
			.collect(Collectors.joining(", "));
			//.forEach(System.out::println);
		System.out.println(newNames);
		
		
		System.out.println("---6---");
		// 6. Average of squares of an int array
		Arrays.stream(new int[] {2, 4, 6, 8, 10})
			.map(x -> x * x)
			.average()
			.ifPresent(System.out::println);
		
		System.out.println("---7---");
		// 7. Stream from List, filter and print
		List<String> people = Arrays.asList("Milan", "Darko", "Milos", "Marija", "Nikola", "Ivan", "Martina");
		people.stream()
			.map(String::toLowerCase)
			.filter(x -> x.startsWith("m"))
			.forEach(System.out::println);
		
		System.out.println("---8---");
		// 8. Stream rows from text file, sort, filter, and print	
		try (
				BufferedReader reader = new BufferedReader (new InputStreamReader(CollectorsExample.class.getResourceAsStream("/res/fruits.txt")));
				Stream<String> personsStream = reader.lines();
		) {
			personsStream
				.sorted()
				//.filter(x -> x.length() < 8)
				.forEach(System.out::println);
			
			reader.close();
			personsStream.close();
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		
		System.out.println("---9---");
		// 9. Stream rows from text file and save to List
		List<String> fruitList = Files.lines(Paths.get(fruitsPath))
				.filter(x -> x.contains("ry"))
				.collect(Collectors.toList());
		fruitList.forEach(System.out::println);
		
		
		System.out.println("---10---");
		// 10. Stream rows from CSV file and count
		Stream<String> rows1 = Files.lines(Paths.get(dataPath));
		int rowCount = (int)rows1
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.count();
		System.out.println(rowCount + " rows");
		rows1.close();
		
		
		System.out.println("---11---");
		// 11. Stream rows from CSV file, parse data from rows
		Stream<String> rows2 = Files.lines(Paths.get(dataPath));
		rows2
			.map(x -> x.split(","))
			.filter(x -> x.length == 3)
			.filter(x -> Integer.parseInt(x[1]) > 15)
			.forEach( x-> System.out.println(x[0] + " " + x[1] + " " + x[2] ));
		rows2.close();
		
		
		System.out.println("---12---");
		// 12. Stream rows from CSV file, store fields in HashMap
		Stream<String> rows3 = Files.lines(Paths.get(dataPath));
		Map<String, Integer> map = new HashMap<>();
		map = rows3
				.map(x -> x.split(","))
				.filter(x -> x.length == 3)
				.filter(x -> Integer.parseInt(x[1]) > 15)
				.collect(Collectors.toMap(
							x -> x[0], 
							x -> Integer.parseInt(x[1])));
		rows3.close();
		for(String key: map.keySet()) {
			System.out.println(key + " " + map.get(key));
		}
		
		
		// 13. Reduction - sum
		System.out.println("---13---");
		double total = Stream.of(7.3, 1.5, 4.8)
				.reduce(0.0, (Double a, Double b) -> a + b);
		System.out.println("Total = " + total);
		
		
		// 14. Reduction - summary statistics
		System.out.println("---14---");
		IntSummaryStatistics summaryStatistics = IntStream.of(7, 2, 19, 88, 73, 4, 10)
				.summaryStatistics();
		System.out.println("Summary statistics: " + summaryStatistics);		
	}
}
