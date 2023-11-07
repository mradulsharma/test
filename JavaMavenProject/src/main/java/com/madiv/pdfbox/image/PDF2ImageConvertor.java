package com.madiv.pdfbox.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDF2ImageConvertor {
	public static void main(String[] args) throws IOException {
		String pdfFilePath="C:\\Users\\mradu\\Downloads\\ETHAN TURCO COVID-19 Digital Certificate 20211013.pdf";
		System.out.println("Conveting image of PDF file = " + pdfFilePath);
		PDDocument doc = PDDocument.load(new File(pdfFilePath));
		
		PDFRenderer pdfRenderer = new PDFRenderer(doc);
		
		for(int page = 0; page < doc.getNumberOfPages(); ++page) {
			BufferedImage biBufferedImage = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
			ImageIOUtil.writeImage(biBufferedImage, "C:\\Users\\mradu\\Downloads\\Testing.png", 300);
		}
		
		doc.close();
		
		
		System.out.println("Done !!");
		
	}
}
