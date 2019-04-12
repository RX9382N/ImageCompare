import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage; 
import javax.imageio.ImageIO; 
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths; 
  
class ImageCompare 
{ 
	 public static void main(String[] args) 
	    { 
	    	
		    long startTime = System.nanoTime();
	        BufferedImage imgA = null; 
	        BufferedImage imgB = null; 
	      
	        try
	        { 
	       
	            File fileA = new File("C:\\Users\\rx-78-1.jpg"); 
	            File fileB = new File("C:\\Users\\rx-78-2.jpg"); 
	            
	            		
	            imgA = ImageIO.read(fileA); 
	            imgB = ImageIO.read(fileB); 
	            
	        } 
	        catch (IOException e) 
	        { 
	            System.out.println(e); 
	        } 
	        
	        
            imgA = resize(imgA, 8, 8);
            imgB = resize(imgB, 8, 8);
	        

	        int width1 = imgA.getWidth(); 
	        int width2 = imgB.getWidth(); 
	        int height1 = imgA.getHeight(); 
	        int height2 = imgB.getHeight(); 
	       
	     
	        	//Take the smaller image width and height
	        	if(width1>width2) width1 = width2;
	        	if(height1>height2) height1 = height2;
	        
	        
	        long difference = 0; 
	        for (int y = 0; y < height1; y++){ 
	        	for (int x = 0; x < width1; x++){ 
	                    int rgbA = imgA.getRGB(x, y);
	                    int rgbB = imgB.getRGB(x, y); 
	                    int redA = (rgbA >> 16) & 0xff; 
	                    int greenA = (rgbA >> 8) & 0xff; 
	                    int blueA = (rgbA) & 0xff; 
	                    int redB = (rgbB >> 16) & 0xff; 
	                    int greenB = (rgbB >> 8) & 0xff; 
	                    int blueB = (rgbB) & 0xff; 
	                    difference += Math.abs(redA - redB); 
	                    difference += Math.abs(greenA - greenB); 
	                    difference += Math.abs(blueA - blueB); 

	             } 
	        } 
	   	 
	       //total number of rgb data = width * height * 3 
	       double total_rgb_data = width1 * height1 * 3; 
	  
	       // average pixels per rgb
	       double avg_different_pixels = difference / total_rgb_data; 
	  
	       // There are 255 values of pixels in total 
	       double percentage = (avg_different_pixels / 255) * 100; 
	  
	       System.out.println("Similarity Percentage::" + (100-percentage) +" %"); 
	        
	       long endTime = System.nanoTime();
	       System.out.println("Time taken::"+(endTime - startTime)+" nano sec");
			
	    }//main() ends here 
	 
	    public static BufferedImage resize(BufferedImage image, int width, int height) {
	        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g = resizedImage.createGraphics();
	        g.drawImage(image, 0, 0, width, height, null);
	        g.dispose();
	        return resizedImage;
	    }

	}//class ends here 