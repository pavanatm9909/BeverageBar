package edu.banditutorials.beveragebar;

import static edu.banditutorials.beveragebar.UtilityCheckFiles.getFileLines;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Build our switch statement so we can see all the animals.
 *
 * @author Denise Case
 */
public class UtilityBuildSwitch {

    private static final String relativePathToPackage = "/src/main/java/edu/banditutorials/beveragebar";
    private static final String nonAnimalsFileString = "SOURCE_NON_CUSTOMCLASS_FILES.txt";

    public static Map<Integer, String> getAllCustomClassMap() {

        // find the files that should be excluded
        ArrayList<String> ignoreList = getNonCustomClassFiles();

        // process all found files, outputing custom class code
        File fileFolder = new File(getCustomClassPackagePathString());
        String[] filesArray = fileFolder.list();
        Arrays.sort(filesArray);

        // create local variables for n and our map (a data structure)
        var n = 1;
        // keep keys in order with TreeMap and be thread safe for deployment
        var customclassMap = Collections.synchronizedMap(new TreeMap<Integer, String>());

        // process the list and load the map
        for (String file : filesArray) {
            if (!ignoreList.contains(file)) {
                int fileLength = file.length();
                int lengthExtension = ".java".length();
                int fileNameLength = fileLength - lengthExtension;
                var justName = file.substring(0, fileNameLength);
                customclassMap.put(n++, justName);
            }
        }
        return customclassMap;
    }

    public static void main(String args[]) throws IOException {

        // find the files that should be excluded
        ArrayList<String> ignoreList = getNonCustomClassFiles();
        System.out.println("Ignore these:");
        ignoreList.forEach(f -> System.out.println(f));

        // process all found files, outputing custom animal code
        System.out.println("From all these in the package:");
        File fileFolder = new File(getCustomClassPackagePathString());
        String filesArray[] = fileFolder.list();
        for (String s : filesArray) {
            System.out.println(s);
        }

        System.out.println("===============================");
        System.out.println("Generate Custom Class Switch");
        System.out.println("===============================");
        int n = 1;
        for (String file : filesArray) {
            if (!ignoreList.contains(file)) {
                int fileLength = file.length();
                int lengthExtension = ".java".length();
                int fileNameLength = fileLength - lengthExtension;
                var justName = file.substring(0, fileNameLength);

                // we want to output something like this:
                //case 1 -> {
                //           var b = new Coffee("Coffee");
                //           var n = b.getName();
                //           var d = b.getDescription();
                //           System.out.println("I'm " + n + ", a " + d + ".");
                //        }
                // use Java 15 Text Blocks
                System.out.println("case " + n++ + " -> {");
                System.out.println("var b = new " + justName + "(\"" + justName + "\");");
                String textBlock = """
                           var n = b.getName();
                           var d = b.getDescription();
                           System.out.println("I'm " + n + ", a " + d + "."); 
                           """;
                System.out.print(textBlock);
                System.out.println("}");
            }
        }

        System.out.println("===============================");
        System.out.println("Generate Custom Class Menu");
        System.out.println("===============================");
        n = 1;
        for (String file : filesArray) {
            if (!ignoreList.contains(file)) {
                int fileLength = file.length();
                int lengthExtension = ".java".length();
                int fileNameLength = fileLength - lengthExtension;
                var justName = file.substring(0, fileNameLength);

                // output something like this 
                //System.out.println("1. Coffee");
                //System.out.println("2. Tea");
                //System.out.println("3. Milk");
                // write code below.....
                var numberAndClassName = String.format("%2d.%-20s", n, justName);
                var quote = "\"";
                var sOpen = "System.out.print(";
                var sOpenLN = "System.out.println(";
                var sClose = ");";
                var strOut = sOpen + quote + numberAndClassName + quote + sClose;
                var strOutLN = sOpenLN + quote + numberAndClassName + quote + sClose;
                var statement = (n % 4 == 0) ? strOutLN : strOut;
                System.out.println(statement);
                n++;
            }
        }
        System.out.println("===============================");
        System.out.println("Update NUMBER_CUSTOM_TYPES = " + --n);
        System.out.println("===============================");
    }

    /**
     * Get a list of expected files in the root project directory.
     *
     * @return String[] of expected file names
     */
    private static ArrayList<String> getNonCustomClassFiles() {
        return getFileLines(nonAnimalsFileString);
    }

    /**
     * Get our project package path as a String.
     *
     * @return project package String
     */
    private static String getCustomClassPackagePathString() {
        Path projectPath = Paths.get("").toAbsolutePath();
        String projectPathString = projectPath.normalize().toString() + relativePathToPackage;
        System.out.println(projectPathString);
        return projectPathString;
    }

}
