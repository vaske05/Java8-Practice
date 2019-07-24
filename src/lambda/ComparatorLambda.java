package lambda;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorLambda {
	
	public static void main(String... args) {
		
		Comparator<String> comp = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.length(), o2.length());
			}
		};
		
		Comparator<String> lambdaComparator = (String s1, String s2) 
				-> Integer.compare(s1.length(), s2.length());
		
		
		List<String> list = Arrays.asList("***", "**", "*****", "****", "*");
		
		//Collections.sort(list, comp);
		
		list.sort(lambdaComparator);
		
		list.forEach(element -> System.out.println(element));
		
	}
}
