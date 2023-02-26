package service;

import toys.Toys;

import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    public void writePrize(Toys toys) throws IOException {
        try (FileWriter writer = new FileWriter("prize.csv", true)) {
            writer.write(toys.toString());
            writer.append('\n');

            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
