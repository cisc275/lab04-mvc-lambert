/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JPanel {
	
	static JFrame frame = new JFrame();
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[][] pics;

    final static int frameWidth = 800;
    final static int frameHeight = 500;
    final static int imgWidth = 165;
    final static int imgHeight = 165;
    
    int xloc = 0;
    int yloc = 0;
    Direction d = Direction.SOUTHEAST;
    
    public int getWidth() {
    	return this.frameWidth;
    }
    
    public int getHeight() {
    	return this.frameHeight;
    }
    
    public int getImageWidth() {
    	return this.imgWidth;
    }
    
    public int getImageHeight() {
    	return this.imgHeight;
    }
    
    public void update(int x, int y, Direction d) {
    	this.xloc = x;
    	this.yloc = y;
    	this.d = d;
    	frame.repaint();
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	//Switch statement to determine which direction to use then calling the hasCollided statements to change the 'd' (Direction)
    	switch (d) {
    	
    		case SOUTHWEST:
    			g.drawImage(pics[6][picNum], xloc, yloc, Color.gray, this);
    			
    			break;
    			
    		case SOUTHEAST:
    			g.drawImage(pics[2][picNum], xloc, yloc, Color.gray, this);
    			
    			break;
    			
    		case NORTHEAST:
    			g.drawImage(pics[0][picNum], xloc, yloc, Color.gray, this);

    			break;
    			
    		case NORTHWEST:
    			g.drawImage(pics[1][picNum], xloc, yloc, Color.gray, this);
    			
    			break;
    	}
    	}
    
    public static void main(String[] args) {
    	Controller c = new Controller();
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	c.start();
    }
    	
     public View(){
       	File dir = new File("images/orc/");
       	ArrayList<String> validPics = new ArrayList<>();
       	pics = new BufferedImage[8][10];
       	
       	//Loads all of the files into an ArrayList as long as the criteria (contains "forward") is met
        for (File f : dir.listFiles()) {
          	if(f.getName().contains("forward")) {
        		System.out.println(f.getPath());
        		validPics.add(f.getPath());
         	}
        }
        	
    	   for(int i = 0; i < validPics.size(); i++) {
    	   	BufferedImage img = createImage(validPics.get(i));
    	    for (int j = 0; j < frameCount; j++) {
    	    	pics[i][j] = img.getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
    	    }
    	}
    }
        
        
        //Read image from file and return
    private BufferedImage createImage(String path){ //Added a path (String) parameter to accept any path as used in paint()
        BufferedImage bufferedImage;
        try {
    		System.out.println();
       		bufferedImage = ImageIO.read(new File(path)); //Utilizes the path name
       		return bufferedImage;
       	} catch (IOException e) {
       		e.printStackTrace();
        	}
    	return null;
    }
    
    
}