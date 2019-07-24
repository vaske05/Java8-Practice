package streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsExample {

	public static void main(String[] args) {
		
		String path = "/res/people.txt";
		
		List<Person> personList = new ArrayList<>();
		
		try (
				BufferedReader reader = new BufferedReader (
						new InputStreamReader(CollectorsExample.class.getResourceAsStream(path)));
				
				Stream<String> stream = reader.lines();
		) {
			stream.map(line-> {
				String[] s = line.split(" ");
				Person p = new Person(s[0].trim(), Integer.parseInt(s[1]));
				personList.add(p);
				return p;
			}).forEach(System.out::println);
			
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		
		//Stream<Person> stream = personList.stream();
		
		Optional<Person> opt = 
		personList.stream().filter(p -> p.getAge() > 20).min(Comparator.comparing(Person::getAge));
		System.out.println(opt);
		
		Optional<Person> opt2 = personList.stream().max(Comparator.comparing(Person::getAge));
		System.out.println(opt2);
		
		Map<Integer, String/*List<String>*//*List<Person>*//*Long*/> map = 
				personList.stream()
							.collect(
									Collectors.groupingBy (
											Person::getAge,
											//Collectors.counting()
											Collectors.mapping (
													Person::getName, 
													//Collectors.toList()
													Collectors.joining(", ")
											)
									)
							);
		System.out.println();
		System.out.println(map);
		
	}

}
