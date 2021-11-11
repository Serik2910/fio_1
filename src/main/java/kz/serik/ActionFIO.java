package kz.serik;

import java.io.*;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ActionFIO {
    public static void main(String[] args) {
        String input = "Hello  Java! Hello,JavaScript! JavaSE 8.";
        Pattern pattern = Pattern.compile("\\s*[\\s|,|\\.|!|?]\\s*");
        String[] words = pattern.split(input);
        for(String word:words)
            System.out.println(word);
        String input2 = "Hello  Java!! Hello,JavaScript! JavaSE 8. wesd\r\n Redcx";
        Pattern pattern2 = Pattern.compile("\\s*[?|\\.|!|\\n|\\r]++\\s*");
        String[] sentences = pattern2.split(input2);
        for(String sentence:sentences)
            System.out.println(sentence);
//        ActionFIO app = new ActionFIO();
//        System.out.println(app.returnPath("original/book.txt"));
//        System.out.println(app.returnPathJavaIOFile("src/main/resources/original/book.txt"));
//        app.createProperties("appManual.properties");
//        app.printAll("app.properties");
//        app.printAllDirect("app.properties");
    }
    private void printAll(String filename) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find " + filename);
                return;
            }

            prop.load(input);

            // Java 8 , print key and values
            prop.forEach((key, value) -> System.out.println("Key : " + key + ", Value : " + value));

            // Get all keys
            prop.keySet().forEach(x -> System.out.println(x));

            Set<Object> objects = prop.keySet();

            /*Enumeration e = prop.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String) e.nextElement();
                String value = prop.getProperty(key);
                System.out.println("Key : " + key + ", Value : " + value);
            }*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void printAllDirect(String filename){
        String filePath = getClass().getClassLoader().getResource(filename).getPath();
        String filePathNoFile = getClass().getClassLoader().getResource("").getPath();
        String filePath1 = filePathNoFile+filename;
        try (InputStream input = new FileInputStream(filePath)) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("path.from"));
            System.out.println(prop.getProperty("path.to"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void createProperties(String filename){
        String filePath = getClass().getClassLoader().getResource("").getPath();
        filePath += filename;
        try (OutputStream output = new FileOutputStream(filePath)) {

            Properties prop = new Properties();
            prop.setProperty("db.url", "localhost");
            prop.setProperty("db.user", "mkyong");
            prop.setProperty("db.password", "password");

            prop.store(output, "new properties");
            System.out.println(prop);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String returnPath(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        String absolutePath = file.getAbsolutePath();
        return  absolutePath;
    }

    private String returnPathJavaIOFile(String fileName){
        File file = new File(fileName);
        String absPath = file.getAbsolutePath();
        return absPath;
    }
}
