package src.game_world_elements;

import java.util.ArrayList;
import java.awt.*;
import res.ImageReader;

public abstract class BuildBlock extends PhysicsElement
{
    private boolean isFixed;
    private Image markerImage;
    
    public BuildBlock(){
        super(true);
        isFixed = false;
        markerImage = ImageReader.readImage(ImageReader.getEffectLocation() + "block_marker.png");
    }
    
    public void behave(){
        if(!isFixed) {
            alignToBuildBlock();
            isFixed = true;
        }
    }
    
    public int alignToBuildBlock() {
        ArrayList<BuildBlock> blockNeighbours = new ArrayList<BuildBlock>();
        for(WorldElement e : getWorld().getElements()) {
            if(e instanceof BuildBlock)
                blockNeighbours.add((BuildBlock)(e));
        }
        
        for(BuildBlock e : blockNeighbours) {
            int relLoc = e.getRelativeLocationOf(getLocation());
            if(relLoc > 0) {
                e.pull(this,relLoc);
                return relLoc;
            }
        }
        return -1;
    }
    
    public void pull(BuildBlock target, int side) {
        Rectangle dest = target.getHitBox();
        switch(side) {
            case 1 :
            dest = getRightSpot();
            break;
            case 2 :
            dest = getTopSpot();
            break;
            case 3 :
            dest = getLeftSpot();
            break;
            case 4 :
            dest = getBottomSpot();
            break;
        }
        target.setLocation((int)(dest.getX()+dest.getWidth()/2),(int)(dest.getY()+dest.getHeight()/2));
    }
    
    public int getRelativeLocationOf(Point spot) {
        if(getRightSpot().contains(spot))
            return 1;
        else if(getTopSpot().contains(spot))
            return 2;
        else if(getLeftSpot().contains(spot))
            return 3;
        else if(getBottomSpot().contains(spot))
            return 4;
        return -1;
    }
    
    public void draw(Graphics g) {
        super.draw(g);
        drawMarkers(g);
    }
    
    public void drawMarkers(Graphics g) {
        if(getWorld().getLastPlaced().equals(this)) {
            g.drawImage(markerImage,(int)getRightSpot().getX(),(int)getRightSpot().getY(),getWorld());
            g.drawImage(markerImage,(int)getTopSpot().getX(),(int)getTopSpot().getY(),getWorld());
            g.drawImage(markerImage,(int)getLeftSpot().getX(),(int)getLeftSpot().getY(),getWorld());
            g.drawImage(markerImage,(int)getBottomSpot().getX(),(int)getBottomSpot().getY(),getWorld());
        }
    }
    
    public boolean isFixed() {
        return isFixed;
    }
    
    public Rectangle getRightSpot() {
        return new Rectangle(getX() + getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }
    public Rectangle getTopSpot() {
        return new Rectangle(getX() - getWidth()/2, getY() - getHeight()/2 - getHeight(), getWidth(), getHeight());
    }
    public Rectangle getLeftSpot() {
        return new Rectangle(getX() - getWidth()/2 - getWidth(), getY() - getHeight()/2, getWidth(), getHeight());
    }
    public Rectangle getBottomSpot() {
        return new Rectangle(getX() - getWidth()/2, getY() + getHeight()/2, getWidth(), getHeight());
    }
}
