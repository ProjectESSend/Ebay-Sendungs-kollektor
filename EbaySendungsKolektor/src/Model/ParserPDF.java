package Model;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

public class ParserPDF {
	private static final String OUTPUT_DIR = "Assets/tmp/";
	
	public EtiketInformation readEtiketInformationFromPdf(URI uri) throws Exception  {
		System.out.println(uri);
		String line = "";
		
		
		 PDFTextStripper ocrStripper = new PDFOCRTextStripper();
	        //PDFTextStripper ocrStripper = new PDFTextStripper();
	        PDDocument document = PDDocument.load("Assets/send1.pdf");
	        String data = ocrStripper.getText(document);
	        document.close();
	        System.out.println("data "+data);
		
		
		
//		PDDocument doc = PDDocument.load(new File(uri));
//        PDFTextStripper pdfStripper = new PDFTextStripper();
//        String pdDocText = pdfStripper.getText(doc);
//		String parsedText;
//		File f = new File(uri);
//		PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
//		parser.parse();
//		
//		COSDocument cosDoc = parser.getDocument();
//		PDFTextStripper pdfStripper = new PDFTextStripper();
//		PDDocument pdDoc = new PDDocument(cosDoc);
//		parsedText = pdfStripper.getText(pdDoc);
//		System.out.println(parsedText);
		//System.out.println(pdDocText + "inhaltende");
        return null;
	}
	
	
	
	private List<RenderedImage> getImagesFromResources(PDResources resources) throws IOException {

        List<RenderedImage> images = new ArrayList<>();
        
        for (COSName xObjectName : resources.getXObjectNames()) {
            PDXObject xObject = resources.getXObject(xObjectName);
            if (xObject instanceof PDImageXObject) {
            	PDImageXObject image = (PDImageXObject)xObject;
            	
                images.add(((PDImageXObject) xObject).getImage());
            } else if (xObject instanceof PDFormXObject) {
            	 PDFormXObject image = (PDFormXObject)xObject;
                images.addAll(getImagesFromResources(((PDFormXObject) xObject).getResources()));
                
            }
            
        }
        return images;
    }
	
	public void writeImages(URI p) throws IOException {
		int i = 1;
		URL ul =p.toURL();
		InputStream resource =ul.openStream() ;
		PDDocument document = PDDocument.load(resource);

        PDPage page = document.getPage(0);
        System.out.println(page.getContents());
        InputStream inputStream = page.getContents();
        
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        
        System.out.println(result);
        PDResources pageResources = page.getResources();
		List<RenderedImage> limage = getImagesFromResources(pageResources);
		for(RenderedImage imageO : limage) {
			String filename = "C://Users//robocop//Desktop//Assets//tmp//" + i + ".png";//p.OUTPUT_DIR + "extracted-image-" + i + ".png";
			File f = new File(filename);
			ImageOutputStream ios = ImageIO.createImageOutputStream(f);
			ImageIO.write(imageO, "png", ios);
			i++;
		}
	
	}

}
