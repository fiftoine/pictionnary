package com.fiftoine.utils;

import com.fiftoine.model.DrawingInfos;

import java.io.*;

/**
 * Utility class to save and load drawing infos to file
 */
public class FileUtils {

    /**
     * Save drawing infos to a file
     * @param drawingInfos Drawing infos to save
     * @param fileName file to save infos to
     */
    public static void saveDrawingInfosToFile(DrawingInfos drawingInfos, String fileName){
        System.out.println("Saving drawing infos "+drawingInfos);
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichier = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(drawingInfos);
            oos.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Load drawing infos from a file
     * @param fileName file to load drawing infos from
     * @return drawing infos included on the file
     */
    public static DrawingInfos loadDrawingInfosFromFile(String fileName) {
        System.out.println("Loading drawing infos ");

        ObjectInputStream ois = null;

        try {
            final FileInputStream fichier = new FileInputStream(fileName);
            ois = new ObjectInputStream(fichier);
            final DrawingInfos drawingInfos = (DrawingInfos) ois.readObject();
            System.out.println("Loaded drawing infos : "+drawingInfos);
            return drawingInfos;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }


}
