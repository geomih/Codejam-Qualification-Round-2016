
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by geomih on 9/4/2016.
 */
public class Main {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("input.txt"), StandardCharsets.UTF_8)) {
            String line;
            List<Integer> fileNumbers = new ArrayList<>();//all the numbers in the file.the Ns
            while ((line = reader.readLine()) != null) {
                fileNumbers.add(Integer.parseInt(line));
            }
            //fileNumbers.forEach(System.out::println);
            int numOfCases = fileNumbers.get(0);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardCharsets.UTF_8);
            for (int i = 1; i <= numOfCases; i++) { // numOfCases !
                Set<Integer> uniqueNumbers = new HashSet<>();// only insert single digits number
                int N = fileNumbers.get(i); //N
                int multiplier = 0;        //#xN = temp (next line0
                int temp, lastNumber = - 1;
                while (uniqueNumbers.size() != 10 && N != 0) { //the main loop to check if we have all the unique numbers
                    multiplier++;
                    temp = multiplier * N;
                    lastNumber = temp;
                    //parse digits of temp eg 1357 = 1,3,5,7
                    while (temp > 0) {
                        uniqueNumbers.add(temp % 10);//remainder
                        temp = temp / 10;
                        //System.out.println("temp: " + temp);
                    }
                }
                //System.out.println("lastNumber: " + lastNumber);
                if (N != 0) {
                    writer.write("Case #" + i + ": " + lastNumber);
                } else {
                    writer.write("Case #" + i + ": INSOMNIA");
                }
                writer.newLine();
                //write to output.txt Case #i: lastNumber found
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}