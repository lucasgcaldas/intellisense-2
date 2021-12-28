import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        String fileName = "src/main/resources/marketing_campaign.csv";

        CSVParser csvParser = new CSVParserBuilder().withSeparator(' ').build(); // custom separator
        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(fileName))
                .withCSVParser(csvParser)   // custom CSV parser
                .withSkipLines(1)           // skip the first line, header info
                .build()) {
            List<String[]> r = reader.readAll();

            String[] lines = new String[r.size()];

            for (int i = 0; i < r.size(); i++) {
                lines[i] = Arrays.toString(r.get(i));
            }

            double count = 0;
            int children = 0;
            int teen = 0;
            for (String s : lines) {
                String[] result = s.split("\t");
                if ((result[1].equals("1958")
                        || result[1].equals("1959")
                        || result[1].equals("1960")
                        || result[1].equals("1961")
                        || result[1].equals("1962")
                        || result[1].equals("1963")
                        || result[1].equals("1964")
                        || result[1].equals("1965")
                        || result[1].equals("1966")
                ) && result[2].equals("Master") && result[3].equals("Married")) {
                    count++;
                    System.out.println(s);
                    if (result[5].equals("1")) {
                        children++;
                    } else if (result[5].equals("2")) {
                        children = children + 2;
                    }
                    if (result[6].equals("1")) {
                        teen++;
                    } else if (result[6].equals("2")) {
                        teen = teen + 2;
                    }
                }
            }
            System.out.println();
            System.out.println("The percentage of consumers found in relation to the total is: "
                    + count / lines.length + " %");
            System.out.println();
            System.out.println("Have " + children + " children at home");
            System.out.println("Have " + teen + " teenagers at home");

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

    }
}
