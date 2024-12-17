package guliveer.lab12.util;

import guliveer.lab12.model.User;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void saveToFile(User user, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("[General]\n");
            writer.write("\tName: " + user.getFirstName() + "\n");
            writer.write("\tSurname: " + user.getLastName() + "\n");
            writer.write("\tCompany: " + user.getCompany() + "\n");
            writer.write("\tPosition: " + user.getPosition() + "\n");

            writer.write("[Address]\n");
            writer.write("\tStreet: " + user.getStreet() + "\n");
            writer.write("\tCity: " + user.getCity() + "\n");
            writer.write("\tZip code: " + user.getZipCode() + "\n");
            writer.write("\tRegion: " + user.getRegion() + "\n");
            writer.write("\tCountry: " + user.getCountry() + "\n");

            writer.write("[Contact]\n");
            writer.write("\tPhone number: " + user.getPhoneNumber() + "\n");
            writer.write("\tEmail: " + user.getEmail() + "\n");
            writer.write("\tWebsite: " + user.getWebsite() + "\n");

            writer.write("[Notes]\n\t" + user.getNotes() + "\n");
            writer.write("\n---\n\n");
        }
    }
}