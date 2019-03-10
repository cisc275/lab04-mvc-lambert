import java.awt.Color;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model {
    int xloc = 0;
    int yloc = 0;
    int xIncr = 8;
    int yIncr = 2;
    
    int frameWidth;
    int frameHeight;
    int imgWidth;
    int imgHeight;
    
    Direction d = Direction.SOUTHEAST;
    
    public Model (int frameWidth, int frameHeight, int imgWidth, int imgHeight) {
    	this.frameWidth = frameWidth;
    	this.frameHeight = frameHeight;
    	this.imgWidth = imgWidth;
    	this.imgHeight = imgHeight;
    }
    
    public int getX() {
    	return this.xloc;
    }
    
    public int getY() {
    	return this.yloc;
    }
    
    public Direction getDirect() {
    	return this.d;
    }
    
    public void updateLocationAndDirection () {
    	if (xloc < 0 || xloc > frameWidth - imgWidth) {
    		xIncr = -xIncr;
    	}
    	
    	if (yloc < 0 || yloc > frameHeight - imgHeight) {
    		yIncr = -yIncr;
    	}
    	
    	switch (d) {
    	
		case SOUTHWEST:
			
			if (xIncr < 0) {
				d = Direction.SOUTHEAST;
			}
			if (yIncr < 0) {
				d = Direction.NORTHWEST;
			}
			
			break;
			
		case SOUTHEAST:
			
			if (xIncr > 0) {
				d = Direction.SOUTHWEST;
			}
			if (yIncr < 0) {
				d = Direction.NORTHEAST;
			}
			
			break;
			
		case NORTHEAST:
			
			if (xIncr > 0) {
				d = Direction.NORTHWEST;
			}
			if (yIncr > 0) {
				d = Direction.SOUTHEAST;
			}
			
			break;
			
		case NORTHWEST:
			
			if (xIncr < 0) {
				d = Direction.NORTHEAST;
			}
			if (yIncr > 0) {
				d = Direction.SOUTHWEST;
			}
			
			break;
    	}
    	
    	xloc += xIncr;
    	yloc += yIncr;
    }
}