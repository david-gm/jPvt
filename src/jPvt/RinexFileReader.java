package jPvt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class RinexFileReader {

    private final String file_name;

    /**
     *
     * @param rinex_file_name
     */
    public RinexFileReader(String rinex_file_name) {
        this.file_name = rinex_file_name;
    }

    public boolean readFile() {
        try {
            File file = new File(this.file_name);
            FileReader file_reader = new FileReader(file);
            BufferedReader reader = new BufferedReader(file_reader);

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
