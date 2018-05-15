import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class UserMaker {
    void MakeUsersFile(int NumberOfUsers){
        Path src = Paths.get("C:\\Users\\emilp_ik\\Documents\\GitHub\\P2_recommender_system\\Movie_Recommender\\Users.csv");
        if(Files.exists(src)){
            try {
                BufferedWriter Usermaker = Files.newBufferedWriter(src);
                String line = "userID,password,neighborID,ignoreID\n";
                Usermaker.write(line);
                for(int i = 1; i <= NumberOfUsers; i++){
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
