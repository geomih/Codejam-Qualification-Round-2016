
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geomih on 9/4/2016.
 */
public class Main2 {
    public static void main(String[] args) {
        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get("input.txt"), StandardCharsets.UTF_8)) {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("output.txt"), StandardCharsets.UTF_8);

            String l;
            List<String> allLines = new ArrayList<>();//all the lines from the file
            while ((l = reader.readLine()) != null) {
                allLines.add(l);
            }
            int numOfLines = Integer.parseInt(allLines.get(0));
            int lineCounter = 0;
            //System.out.println("numberLines: " + numOfLines);
            allLines.remove(0);
            int numOfFlips;
            //System.out.println("list: " + allLines);
            //allLines.stream().forEach(System.out::println);
            //System.out.println(flip(allLines.get(4)));
            for (String line : allLines) {
                numOfFlips = 0;
                lineCounter++;
                while (line.contains("-")) {
                    if (line.startsWith("-") && line.endsWith("-")) { //1# case: -*-, -, ---
                        line = flip(line); //from now on it ends with +
                        numOfFlips++;
                    }
                    if (line.indexOf("-") > 0) { //2# case: ++++-*  (result: -----)
                        String temp = line.substring(line.indexOf("-"));
                        line = flip(line.substring(0, line.indexOf("-")));
                        line = line + temp;
                        numOfFlips++;
                    }
                    if (line.startsWith("-") && line.contains("+")) { //3# case: ---+*, -+-+ (res:++++*,++-+ )
                        String temp = line.substring(line.indexOf("+"));
                        line = flip(line.substring(0, line.indexOf("+")));
                        line = line + temp;
                        numOfFlips++;
                    }
                }

                //System.out.println("case: " + lineCounter + " result: " + line + " flips: "+ numOfFlips );
                writer.write("Case #" + lineCounter + ": " + numOfFlips);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String flip(String s) {
        StringBuilder newS = new StringBuilder();
        String c;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            c = s.substring(i, i + 1);
            //System.out.println(c);
            if (c.equals("-")) {
                c = "+";
            } else {
                c = "-";
            }
            newS.append(c);
        }
        return newS.toString();
    }
}