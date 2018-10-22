package com.jpvt.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RinexFileReader {

    private final String file_name;
    private boolean end_of_header = false;

    private RINEX_TYPE rinex_type;

    public RINEX_TYPE getRinexType() {
        return rinex_type;
    }

    public enum RINEX_TYPE {
        NAV,
        OBS
    };

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

            List<String> lines;
            lines = reader.lines().collect(Collectors.toList());
            if (!this.parse(lines)) {
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean parse(List<String> lines) {

        List<String> header = new ArrayList<>();
        List<String> content = new ArrayList<>();

        lines.forEach((String l) -> {
            int index = l.indexOf("END OF HEADER");
            if (this.end_of_header) {
                content.add(l);
            } else if (!this.end_of_header && index == -1) {
                header.add(l);
            } else {
                header.add(l);
                this.end_of_header = true;
            }
        });

        if (!this.parseHeader(header)) {
            return false;
        }

        if (!this.parseContent(content)) {
            return false;
        }
        return true;
    }

    private boolean parseHeader(List<String> header) {

        float version = 0;
        for (String l : header) {
            if (l.contains("RINEX VERSION")) {
                version = Float.parseFloat(l.substring(0, 9));
                switch (l.charAt(20)) {
                    case 'O':
                        this.rinex_type = RINEX_TYPE.OBS;
                        break;
                    case 'N':
                        this.rinex_type = RINEX_TYPE.NAV;
                        break;
                    default:
                        throw new RuntimeException("Wrong RINEX format");
                }
            }
        }
        System.out.println(String.format(new Locale("en"),
                "%.2f, %s",
                version, rinex_type.toString()));

        return true;
    }

    private boolean parseContent(List<String> content) {
        for (String l : content) {
            System.out.println(l);
        }
        return true;
    }
}
