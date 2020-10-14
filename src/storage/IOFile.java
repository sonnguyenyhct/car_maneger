package storage;

import java.io.*;
import java.util.ArrayList;

public class IOFile {
    public static void writeFile(String nameFile,ArrayList list) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(nameFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList readFile(String nameFile){
        try {
            FileInputStream fileInputStream = new FileInputStream(nameFile);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList productArrayList = (ArrayList) objectInputStream.readObject();
            return productArrayList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
