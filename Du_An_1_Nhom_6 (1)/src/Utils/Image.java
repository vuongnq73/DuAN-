/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;


/**
 *
 * @author admin
 */
public class Image {
    public static java.awt.Image getIcon(){
        URL url= Image.class.getResource("/icon/fpt.png");
        return new ImageIcon(url).getImage();
    }
    public static boolean save(File src){
        File dst= new File("src/main/Resources/icon",src.getName());
     if(!dst.getParentFile().exists()){
         dst.getParentFile().mkdirs();
     }
        try {
            Path from= Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to,StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
        }return false;
    }
  public  static ImageIcon read(String fileName){
      File path= new File("src/main/Resources/icon",fileName);
      return new ImageIcon(path.getAbsolutePath());
  }
  
}
