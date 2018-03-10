package com.fiftoine.utils;

import com.fiftoine.model.DrawingInfos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility class to save and load drawing infos to file
 */
public class FileUtils {

    /**
     * Save drawing infos to a file
     *
     * @param drawingInfos Drawing infos to save
     * @param fileName     file to save infos to
     */
    public static void saveDrawingInfosToFile(DrawingInfos drawingInfos, String fileName) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            oos.writeObject(drawingInfos);
            System.out.println("Done write!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Load drawing infos from a file
     *
     * @param fileName file to load drawing infos from
     * @return drawing infos included on the file
     */
    public static DrawingInfos loadDrawingInfosFromFile(String fileName) {
        try (ObjectInputStream oos =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            DrawingInfos drawingInfos = (DrawingInfos) oos.readObject();
            System.out.println("Done read!");
            return drawingInfos;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
