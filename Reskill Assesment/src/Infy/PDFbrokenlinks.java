package Infy;


	

	import java.io.BufferedInputStream;
	import java.io.File;
	import java.io.FileInputStream;

	import java.io.IOException;
	import java.net.HttpURLConnection;
	import java.net.URL;
	import java.util.List;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.apache.pdfbox.cos.COSDocument;
	import org.apache.pdfbox.io.RandomAccessRead;
	import org.apache.pdfbox.pdfparser.PDFParser;
	import org.apache.pdfbox.pdmodel.PDDocument;
	import org.apache.pdfbox.pdmodel.PDPage;
	import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
	import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
	import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
	import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
	import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
	import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
	import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
	import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
	import org.apache.pdfbox.text.PDFTextStripper;
	import org.openqa.selenium.WebElement;



	public class PDFbrokenlinks 
	{

		
		

		public static void main(String[] s) throws IOException
		{
			
			/*File file=new File("C:\Users\LENOVO\OneDrive\Desktop\Infosys Assesment.pdf");
	FileInputStream fis1=new FileInputStream(file);
			PDFParser parser=new PDFParser(fis1);
			parser.parse();
			COSDocument cosdoc=parser.getDocument();
			PDDocument pd=new PDDocument(cosdoc);
			PDFTextStripper text=new PDFTextStripper();
			String data=text.getText(pd);
			System.out.println(data);
			cosdoc.close();
			pd.close();*/
			
			
			
			 File file = new File("C:\\Users\\LENOVO\\OneDrive\\Desktop\\Infosys Assesment\\IVS Reskilling-Functional.pdf");
		        PDDocument document = PDDocument.load(file); 
		    
	           for (PDPage page : document.getPages()) {
	              
	                List<PDAnnotation> annotations = page.getAnnotations();
	                for (PDAnnotation annot : annotations) {
	                    if (annot instanceof PDAnnotationLink) {
	                        // get dimension of annottations
	                        PDAnnotationLink link = (PDAnnotationLink) annot;
	                        // get link action include link url and internal link
	                        PDAction action = link.getAction();
	                        // get link internal some case specal
	                        PDDestination pDestination = link.getDestination();

	                        if (action != null) {
	                            if (action instanceof PDActionURI || action instanceof PDActionGoTo) {
	                                if (action instanceof PDActionURI) {
	                                    // get uri link
	                                    PDActionURI uri = (PDActionURI) action;
	                                  //  System.out.println( uri.getURI());
	                                    verifylinkactive(uri.getURI());
	                                } else {
	                                    if (action instanceof PDActionGoTo) {
	                                        // get internal link
	                                        PDDestination destination = ((PDActionGoTo) action).getDestination();
	                                        PDPageDestination pageDestination;
	                                        if (destination instanceof PDPageDestination) {
	                                            pageDestination = (PDPageDestination) destination;
	                                        } else {
	                                            if (destination instanceof PDNamedDestination) {
	                                                pageDestination = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) destination);
	                                            } else {
	                                                // error handling
	                                                break;
	                                            }
	                                        }

	                                        if (pageDestination != null) {
	                                            System.out.println("page move: " + (pageDestination.retrievePageNumber() + 1));
	                                        }
	                                    }
	                                }
	                            }
	                        } else {
	                            if (pDestination != null) {
	                                PDPageDestination pageDestination;
	                                if (pDestination instanceof PDPageDestination) {
	                                    pageDestination = (PDPageDestination) pDestination;
	                                } else {
	                                    if (pDestination instanceof PDNamedDestination) {
	                                        pageDestination = document.getDocumentCatalog().findNamedDestinationPage((PDNamedDestination) pDestination);
	                                    } else {
	                                        // error handling
	                                        break;
	                                    }
	                                }

	                                if (pageDestination != null) {
	                                    System.out.println("page move: " + (pageDestination.retrievePageNumber() + 1));
	                                }
	                            } else {
	                                //    
	                            }
	                        }
	                    }
		        
		        
		        
	                }}}
		        
		        
		        
		        
		        
		        
		        
		       /*PDFTextStripper pdfTextStripper = new PDFTextStripper();
		        pdfTextStripper.setStartPage(1);
		        pdfTextStripper.setEndPage(1);
		        String text = pdfTextStripper.getText(document);
		        String value=text.toString();
		     // String pattern = "(http?|https)://[-a-zA-Z0-9\n\r].*";
		     // String pattern="((http?|https|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		      String pattern="https:.*";
		        Pattern r = Pattern.compile(pattern);
		   Matcher m= r.matcher(value);
		   int count=0;
		
		   while(m.find())
		   {
			
			  // System.out.println(++count+"."+m.group(0));
			   verifylinkactive(m.group(0));
		   }
		 
		

		}*/
		     public static void verifylinkactive(String linkurl) throws IOException
		     {
		    	 try
		    	 {
		    	 URL url=new URL(linkurl);
		 		HttpURLConnection httpurlconnect=(HttpURLConnection)url.openConnection();
		 		httpurlconnect.connect();
		 		httpurlconnect.setConnectTimeout(6000);
		 		
		 	if(httpurlconnect.getResponseCode()==200)
		 	{
		 		System.out.println(linkurl+"-"+httpurlconnect.getResponseMessage()+httpurlconnect.getResponseCode());
		 		
		 		
		 	}
		 		
		 	if(httpurlconnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
	        {
	            System.out.println(linkurl+" - "+httpurlconnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
	         }}
		    	 
		    	 
		    	 
		    	 catch(Exception e)
		    	 {
		    		 
		    	 }
		    	 
		     }  
		     }
		      



