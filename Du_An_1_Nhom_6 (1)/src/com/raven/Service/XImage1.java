package com.raven.Service;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


public class XImage1 {
    public static Image getAppIcon(){
        URL url = XImage1.class.getResource("src\\com\\raven\\icon\\1.png");
        return new ImageIcon(url).getImage();
    }
    
    public static boolean save(File src){
        File dst = new File("src\\com\\raven\\icon",src.getName());
        if(!dst.getParentFile().exists()){
            dst.getParentFile().mkdirs();    // Tạo thu muc
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static ImageIcon read(String fileName){
        File path = new File("src\\com\\raven\\icon",fileName);
        return new ImageIcon(new ImageIcon(path.getAbsolutePath()).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
    }
    
}