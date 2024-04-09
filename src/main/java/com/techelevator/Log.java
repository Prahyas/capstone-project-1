package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    public static PrintWriter output;

    public static void log(String message) {
        File logFolder = new File("logs");
        if(!logFolder.exists()) {
            logFolder.mkdir();
        }
        LocalDate date = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        String mmDDyyyyHHmmSSa = now.format(format);
        try {
            if(output == null) {
                output = new PrintWriter(new FileOutputStream("logs/" + date + "_Log.txt"));
                output.println(mmDDyyyyHHmmSSa + " " + message);
                output.flush();
            } else {
                output.println(mmDDyyyyHHmmSSa + " " + message);
                output.flush();
            }
        } catch (IOException io) {
            throw new LogException(io.getMessage());
        }
    }
}
