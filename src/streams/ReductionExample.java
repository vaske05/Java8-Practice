package streams;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReductionExample {

	public static void main(String[] args) {
		
		List<Integer> list = Arrays.asList();
		
		//SUM
		/*
		Integer red =
		list.stream()
			.reduce(0, Integer::sum);
		*/
		
		//MAX
		Optional<Integer> red = list.stream().reduce(Integer::max);
		
		System.out.println("red = " + red);

	}
}
