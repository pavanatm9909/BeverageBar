package edu.banditutorials.beveragebar;

import static edu.banditutorials.beveragebar.UtilityCheckFiles.getFileLines;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class to match a custom class with their author.
 *
 * @author Denise Case
 */
public class UtilityAuthors {

    // private data
    private static final String relativePathToPackage = "/src/main/java/edu/banditutorial/beveragebar";
    private static final String nonCustomClassFileString = "SOURCE_NON_CUSTOMCLASS_FILES.txt";

    public static void main(String[] args) {
        try {
            var authorsMap = getAuthors();
            var customClassMap = getCustomClasses();

            System.out.println("===============================");
            System.out.println("Given Custom Class, Find Author");
            System.out.println("===============================");

            authorsMap.entrySet().forEach((var entry) -> {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            });

            System.out.println("====================================");
            System.out.println("Given Author Name, Find Custom Class");
            System.out.println("====================================");

            customClassMap.entrySet().forEach((var entry) -> {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            });

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static Map<String, String> getAuthors() throws Exception {

        // find the files that should be excluded
        ArrayList<String> ignoreList = getNonCustomFiles();

        // process all found files, outputing custom class code
        File fileFolder = new File(getCustomClassPackagePathString());
        String[] filesArray = fileFolder.list();
        Arrays.sort(filesArray);

        // keep keys in order with TreeMap and be thread safe for deployment
        var map = Collections.synchronizedMap(new TreeMap<String, String>());

        // process the list and load the map
        for (String file : filesArray) {
            if (!ignoreList.contains(file)) {
                int fileLength = file.length();
                int lengthExtension = ".java".length();
                int fileNameLength = fileLength - lengthExtension;
                var justName = file.substring(0, fileNameLength);
                String author = getAuthorFromStringFile(file);
                map.put(justName, author);
            }
        }
        return map;
    }

    public static Map<String, String> getCustomClasses() throws Exception {

        // find the files that should be excluded
        ArrayList<String> ignoreList = getNonCustomFiles();

        // process all found files, outputing custom animal code
        File fileFolder = new File(getCustomClassPackagePathString());
        String[] filesArray = fileFolder.list();
        Arrays.sort(filesArray);

        // keep keys in order with TreeMap and be thread safe for deployment
         Map<String, String> map = Collections.synchronizedMap(new TreeMap<>());

        // process the list and load the map
        for (String file : filesArray) {
            if (!ignoreList.contains(file)) {
                int fileLength = file.length();
                int lengthExtension = ".java".length();
                var fileNameLength = fileLength - lengthExtension;
                var justName = file.substring(0, fileNameLength);
                String author = getAuthorFromStringFile(file);
                map.put(author, justName);
            }
        }
        return map;
    }

    /**
     * Get a list of expected files in the root project directory.
     *
     * @return String[] of expected file names
     */
    private static ArrayList<String> getNonCustomFiles() {
        return getFileLines(nonCustomClassFileString);
    }

    /**
     * Get our project package path as a String.
     *
     * @return project package String
     */
    private static String getCustomClassPackagePathString() {
        Path projectPath = Paths.get("").toAbsolutePath();
        String projectPathString = projectPath.normalize().toString() + relativePathToPackage;
        return projectPathString;
    }

    private static String getAuthorFromStringFile(String justFileName) throws Exception {
        var path = getCustomClassPackagePathString();
        var pathAndFile = path + "/" + justFileName;
        List<String> lst = readFileToListStrings(pathAndFile);
        Optional<String> authorLine = getAuthorLine(lst);
        String author = getAuthorFromOptionalString(authorLine);
        return author;
    }

    public static List<String> readFileToListStrings(String path) throws Exception {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    private static Optional<String> getAuthorLine(List<String> listOfStrings) {
        Optional<String> authorLine = listOfStrings.stream().filter(i -> i.contains("@author")).findFirst();
        return authorLine;
    }

    private static String getAuthorFromOptionalString(Optional<String> authorLine) {
        String result = (authorLine == null) ? "NO AUTHOR" : authorLine.get().strip().replace("@author", "").replaceAll("\\*", "").strip();
        return result;
    }

}
