package application;

import java.io.*;
import java.util.*;

public class RWFile{

    public static ArrayList<String> readFile(String fileName){

        ArrayList<String> liste = new ArrayList<String>();
        try{

            Scanner in = new Scanner(new FileReader(fileName));

            while(in.hasNextLine()){
                liste.add(in.nextLine());
            }

            in.close();
        
        }catch(FileNotFoundException e){
            System.out.println("readFile - fichier non trouvé : " + fileName);
        }
        return liste;
    }

    public static void writeFile(ArrayList<String> liste, String fileName){

        try{

            PrintWriter out = new PrintWriter(fileName);

            for(String line : liste){

                out.println(line);

            }

            out.close();

        }catch(FileNotFoundException e){

            System.out.println(e.getMessage());
            
        }
        
    }



}
