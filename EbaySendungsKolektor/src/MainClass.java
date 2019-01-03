import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import Model.*;

public class MainClass {

	public static void main(String[] args) throws Exception {
		//Pfad eingeben:
		Path p = Paths.get("Assets/send2.pdf");
		
		// PDF PARSER LADEN:
		ParserPDF parser = new ParserPDF();
		
		//Methode read aufrufen:
		parser.readEtiketInformationFromPdf((p.toUri()));
		//parser.writeImages(p.toUri());

//		PDDocument pd = new PDDocument();
//	    PDPage page = new PDPage();
//	    pd.addPage(page);
//	    page.setRotation(90);
//	    PDPageContentStream contentStream = new PDPageContentStream(pd, page);
//
//	    int x = 150;
//	    int y = 300;
//
//	    // draw unrotated
//	    contentStream.drawXObject(ximage, x, y, ximage.getWidth() / 2, ximage.getHeight() / 2);
//
//	    // draw 90° rotated, placed on the right of the first image
//	    AffineTransform at = new AffineTransform(ximage.getHeight() / 2, 0, 0, ximage.getWidth() / 2, x + ximage1.getWidth(), y);
//	    at.rotate(Math.toRadians(90));
//	    contentStream.drawXObject(ximage, at);
//		PDFManager pdfManager = new PDFManager();
//        pdfManager.setFilePath(p.toFile().getPath());
//        String text = pdfManager.toText();
//		System.out.println(text);
    }
}
