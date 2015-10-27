package DB;

/**
 *
 * @author Joshua Ryan
*/
public class ProductDescription extends DBClass{
    private static String path = "ProductDescription.txt";
    public static int getNextCode(){
        return nextId(path);
    }
    
    public static void insert(String row){
        insert(path, row);
    }

    public static void delete(int id){
        delete(path, id);
    }

    public static void update(int id){
        update(path, id);
    }
}
