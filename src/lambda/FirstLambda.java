package lambda;
import java.io.File;
import java.io.FileFilter;

public class FirstLambda {
	
	public static void main(String [] args) {
		
		/*
		FileFilter fileFilter = new FileFilter() {
			
			@Override
			public boolean accept(File path) {
				return path.getName().contains(".log");
			}
		};
		*/
		
		FileFilter fileFilter = (File path) -> path.getName().contains(".log"); 
		
		File dir = new File("c:/");
		
		File [] files = dir.listFiles(fileFilter);
		
		for (File file : files) {
			System.out.println(file);
		}
		
	}

}
