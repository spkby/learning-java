package itclass.app_1205;

import java.io.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class ProcessRunner {

    public static void main(String[] args) throws IOException {

        String line = "";
        String inStr = "";

        Scanner sc = new Scanner(System.in);
        ProcessBuilder builder = new ProcessBuilder("cmd.exe");
        builder.redirectErrorStream(true);

        Process process = builder.start();

        OutputStream output = process.getOutputStream();
        InputStream input = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));


        while(true){
            inStr = sc.nextLine();

            if(inStr.trim().equalsIgnoreCase("exit")) {
                break;
            }

            inStr += "\n";
            writer.write(inStr);
            writer.flush();


        do {
            line = reader.readLine();
            System.out.println("> " + line);

        } while (line != null);

        System.out.println("--------------");

        }
        writer.close();
        reader.close();
    }
}
