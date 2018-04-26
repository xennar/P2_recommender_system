import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UserMaker {
    public static void main(String[] argc){
        Path src = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Users.csv");
        System.out.println("Got here1");
        if(Files.exists(src)){
            System.out.println("Got here2");
            try {
                BufferedWriter Usermaker = Files.newBufferedWriter(src);
                System.out.println("Got here3");
                String line = "userID,password,neighborID,ignoreID\n";
                Usermaker.write(line);
                for(int i = 1; i <= 671; i++){
                    Usermaker.write(Integer.toString(i));
                    Usermaker.write(',');
                    Usermaker.write(Integer.toString(i));
                    Usermaker.write(",,\n");
                }
                Usermaker.close();
            }catch(IOException e){System.out.println("Error: File not found: "+e.getMessage());}
        }
    }
}
