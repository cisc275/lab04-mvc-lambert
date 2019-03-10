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
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {
		static JFrame frame = new JFrame();
	final int frameCount = 10;
    int picNum = 0;
    BufferedImage[] pics;
    BufferedImage[][] subPics;

    final static int frameWidth = 800;
    final static int frameHeight = 500;
    final int imgWidth = 165;
    final int imgHeight = 165;
    
    int xloc = 0;
    int yloc = 0;
    Direction direction = Direction.SOUTHEAST;
    
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
    
    public int getX() {
    	return this.xloc;
    }
    
    public int getY() {
    	return this.yloc;
    }
    
    public void update(int x, int y, Direction d) {
    	xloc = x;
    	yloc = y;
    	direction = d;
    	frame.repaint();
    	try {
    		Thread.sleep(100);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
	@SuppressWarnings("incomplete-switch")
	public void paint(Graphics g) {
    	picNum = (picNum + 1) % frameCount;
    	//Switch statement to determine which direction to use then calling the hasCollided statements to change the 'direction' (Direction)
    	switch (direction) {
    	
    		case SOUTHWEST:
    			g.drawImage(subPics[3][picNum], getX(), getY(), Color.gray, this);
    			break;
    			
    		case SOUTHEAST:
    			g.drawImage(subPics[2][picNum], getX(), getY(), Color.gray, this);	
    			break;
    			
    		case NORTHEAST:
    			g.drawImage(subPics[0][picNum], getX(), getY(), Color.gray, this);
    			break;
    			
    		case NORTHWEST:
    			g.drawImage(subPics[1][picNum], getX(), getY(), Color.gray, this);
    			break;
    	}
    	}
    
    public static void main(String[] args) {
    	Controller control = new Controller();
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);
    	
    	control.start();
    }
    	
     public View(){
    	subPics = new BufferedImage[8][10];
     	pics = new BufferedImage[4];
     	pics[0] = createImage("src/images/orc/orc_forward_northeast.png");
     	pics[1] = createImage("src/images/orc/orc_forward_northwest.png");
     	pics[2] = createImage("src/images/orc/orc_forward_southeast.png");
     	pics[3] = createImage("src/images/orc/orc_forward_southwest.png");
     	
 	    for(int i = 0; i < 4; i++) {
 	        for (int j = 0; j < frameCount; j++) {
 	        	subPics[i][j] = pics[i].getSubimage(imgWidth*j, 0, imgWidth, imgHeight);
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