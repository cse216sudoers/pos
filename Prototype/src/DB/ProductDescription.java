package DB;

/**
 *
 * @author Joshua Ryan
*/
public class ProductDescription extends DBClass{
    public static int getNextCode(){
        return nextId("ProductDescription.txt");
    }
}
