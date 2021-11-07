/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alvaroycarlos.scrollpane;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JPanel;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author alvaroafonsolopez
 */
public class Lienzo extends JPanel{
    
    private Mat imgMat = null;
    
    public Lienzo(){
        this.setPreferredSize(new Dimension (489, 392));
    }
    
   @Override
    public void paintComponent(Graphics g){
        if(imgMat == null) return;
        
        super.paintComponent(g);
        
        try{
            g.drawImage(matToImage(), 0, 0, null);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    
    }
    
    public Mat getImgMat(){
        return imgMat;
    }
    
    public int getImgWidth(){
        return imgMat.width();
    }
    
    public int getImgHeight(){
        return imgMat.height();
    }
    
    private BufferedImage matToImage() throws Exception{
        return (BufferedImage) HighGui.toBufferedImage(imgMat);
    }
    
    public void loadImg(File file){
        
        imgMat = Imgcodecs.imread(file.getAbsolutePath());
        
        this.setPreferredSize(new Dimension(imgMat.width(), imgMat.height()));
        
        this.repaint();
    }
    
}
