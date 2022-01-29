package com.company;

import com.company.data.DataBucket;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;

/**
 * @author Noah Mannhart
 * @version 1.0
 * For reading the data into our Lists and our Chart we need a FileManager which converts the data into the right type
 */

public class FileManager {
    public static final Scanner in = new Scanner(System.in);
    public static File home = FileSystemView.getFileSystemView().getHomeDirectory();
    public static File path = new File(home, "IdeaProjects\\MarketSimulation\\StockData\\GME.csv");


    //Method for reading the data
    public static boolean readFile() throws IOException {
        List<Double> DATA_BUCKET = DataBucket.DATA_BUCKET;
        List<String> DATE_BUCKET = DataBucket.DATE_BUCKET;

        //Asks User for path to data file
        System.out.printf("Current path: %s\n", path);
        System.out.println("> Should the current path be used? [Y/n]: ");
        String yesOrNo = in.nextLine().toLowerCase();

        //If user input is n, user gets asked for new input
        if (yesOrNo.equals("n")) {
            System.out.println("> Please enter the path under which the data is located: ");
            String path = in.nextLine();

            while (!isValidPath(path)) {
                System.out.println("> Please specify a correct path under which the data is located: ");
                path = in.nextLine();
            }
        }

        final var reader = Files.newBufferedReader(Path.of(String.valueOf(path)));

        String line;
        final var dataRows = new ArrayList<String>();
        boolean readData = false;
        int dataLineIndex = 0;

        //File gets read
        while ((line = reader.readLine()) != null) {
            line = line.trim();

            //Checks if the line is a title
            if (line.equals("###Data")) {
                readData = true;
                continue;
            }

            //Checks if line is already data
            if (readData) {
                if (dataLineIndex == 0) {
                    if (!line.equals("Date,Open,High,Low,Close,Adj Close,Volume")) {
                        throw new RuntimeException("Invalid format");
                    }

                    dataLineIndex++;
                    continue;
                }

                //Checks if it's at the end of the Data part
                if (line.startsWith("******")) {
                    readData = false;
                    continue;
                }

                //Adds converted string to the DATA_BUCKET as a double
                dataRows.add(line);
                DATA_BUCKET.add(parseData(line));
                DATE_BUCKET.add(parseDate(line));
            }
        }

        return true;
    }

    //Method to parse data from file into List
    public static double parseData(String data) {
        final var parts = data.split(",");

        //Date,Open,High,Low,Close,ADJ CLOSE,Volume
        LocalDate date = null;
        Double open = null;
        Double high = null;
        Double low = null;
        Double close = null;
        Double adjClose = null;
        Double volume = null;

        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                date = LocalDate.parse(parts[i], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            }
            if (i == 1) {
                open = Double.parseDouble(parts[i]);
            }
            if (i == 2) {
                high = Double.parseDouble(parts[i]);
            }
            if (i == 3) {
                low = Double.parseDouble(parts[i]);
            }
            if (i == 4) {
                close = Double.parseDouble(parts[i]);
            }
            if (i == 5) {
                adjClose = Double.parseDouble(parts[i]);
            }
            if (i == 6) {
                volume = Double.parseDouble(parts[i]);
            }
        }

        return adjClose;
    }

    //Method to parse date into Date List
    public static String parseDate(String input) {
        final var parts = input.split(",");

        //DATE,Open,High,Low,Close,Adj Close,Volume
        String date = null;

        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                //date = LocalDate.parse(parts[i], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                date = parts[i].toString();
            }
        }

        return date;
    }

    //Checks if path is Valid
    public static boolean isValidPath(String path) {
        FileManager.path = new File(path);

        return FileManager.path.exists();
    }

    public static String getFileName() {
        Path path1 = Paths.get(path.toString());
        Path fileName = path1.getFileName();

        return fileName.toString();
    }
}
