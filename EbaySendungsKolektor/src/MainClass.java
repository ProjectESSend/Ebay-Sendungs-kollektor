import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import Model.*;

public class MainClass {

	public static void main(String[] args) throws Exception {
		//Pfad eingeben:
		Path p = Paths.get("Assets/send1.pdf");
		
		// PDF PARSER LADEN:
		PDFParser parser = new PDFParser();
		
		//Methode read aufrufen:
		parser.readEtiketInformationFromPdf(p.toUri());

		
//		PDFManager pdfManager = new PDFManager();
//        pdfManager.setFilePath(p.toFile().getPath());
//        String text = pdfManager.toText();
//		System.out.println(text);
    }
}
