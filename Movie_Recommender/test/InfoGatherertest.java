import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Scanner;

public class InfoGatherertest {

    @Test
    public void testArraySize() throws Exception{
        File RatingsFile = new File("adjratings.csv");
        Scanner scan = new Scanner(RatingsFile);
        String line = scan.nextLine();
        String[] adjRatings = line.split(",");
        Assertions.assertEquals(3, adjRatings.length);
    }

}
