package impression;

import java.io.*;
import java.io.FileInputStream;
import java.io.IOException;
import javax.print.attribute.standard.*;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;


public class Imprimante {
   public static void main(String[] args) throws IOException, PrintException {
      String directoryPath = "C:\\Users\\Public\\Intuiface\\1-bbd0feaa-60b0\\Files\\Collection\\DataFeedSnap";
		File directory = new File(directoryPath);
		File moreRecentFile = null;if (!directory.exists()) {
                    System.out.println("Le fichier/répertoire '" + directoryPath + "' n'existe pas");
		} else if (!directory.isDirectory()) {
			System.out.println("Le chemin '" + directoryPath + "' correspond à un fichier et non à un répertoire");
		} else {
			File[] subfiles = directory.listFiles();
			System.out.println("Le répertoire '" + directoryPath + "' contient " + subfiles.length + " fichiers");
			
			if( subfiles.length > 0){
				moreRecentFile = subfiles[0];
				for (int i = 1; i < subfiles.length; i++) {
					File subfile = subfiles[i];
					//System.out.println(subfile.getName());
					
					if (subfile.lastModified() > moreRecentFile.lastModified()) 
						moreRecentFile = subfile;
                                                     
				}
				System.out.println("Le fichier le plus récent du répertoire " + directoryPath + " est " + moreRecentFile.getName());
                                System.out.println(moreRecentFile + " va être imprimé");
                                
      InputStream fichierImage = new FileInputStream(moreRecentFile);
      DocFlavor type = DocFlavor.INPUT_STREAM.GIF;
      PrintRequestAttributeSet attributs = new HashPrintRequestAttributeSet();
      attributs.add(new MediaPrintableArea(1, 1, 210, 297, MediaPrintableArea.MM));
      attributs.add(OrientationRequested.PORTRAIT);
      Doc document = new SimpleDoc(fichierImage, type, null);
      PrintService[ ] services = PrintServiceLookup.lookupPrintServices(type, null);      
      PrintService service = services[0];
      DocPrintJob travail = service.createPrintJob();
      travail.print(document, attributs);
    }
                }
            }
}
   
   