import java.util.ArrayList;
public class ElementMenu //TODO: remove default values/returns
{
    private int numItems = 2;
    
    public ElementMenuItem getItem(int index) {
        ElementMenuItem item;
        switch(index) {
            case 0 :
            item = new ElementMenuItem("Dorito",0,new Dorito());
            break;
            case 1 :
            item = new ElementMenuItem("Dirt",1,new Dirt());
            break;
            default :
            item = new ElementMenuItem("Dorito",0,new Dorito());
            break;
        }
        return item;
    }
    
    public int getNumItems() { return numItems; }
}