package com.madiv.pdfbox.image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;



public class ReadPDFTest {
	public static void main(String[] args) {
		System.out.println("Testing..");
		String pdfFilePath="C:\\Users\\Maddy\\AffirmSoftwareGroup\\TaskworkedUpon\\01.Issues\\147-CC-5250-CovidSecurePDF\\Attachments\\OtherSecurePDF\\COMPLIANCE_connx_prod_record_id_100204431.pdf";
		
		File file = new File(pdfFilePath); 
		
		try {
			FileInputStream fis = new FileInputStream(file);

			BufferedInputStream bis = new BufferedInputStream(fis, 12000);
			
			PdfReader reader = new PdfReader(bis);
			System.out.println("Done !! " + com.itextpdf.text.pdf.PdfEncryptor.isPrintingAllowed((int)reader.getPermissions()));
			System.out.println("Done !! " + com.itextpdf.text.pdf.PdfEncryptor.isCopyAllowed((int)reader.getPermissions()));
			
		
		} catch (FileNotFoundException e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Exception : " + e);
			e.printStackTrace();
		} finally {
			System.out.println("Fin!!");
		}
		
	}
}
