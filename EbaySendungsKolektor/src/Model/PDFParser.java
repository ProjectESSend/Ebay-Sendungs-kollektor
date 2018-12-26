package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;

public class PDFParser {
	
	public EtiketInformation readEtiketInformationFromPdf(URI uri) throws Exception {
		System.out.println(uri);
		String line = "";
		BufferedReader br = new BufferedReader(new FileReader(new File(uri)));
		while(line != null) {
			System.out.println(line);
			line = br.readLine();
		}
		return null;
	}

}
