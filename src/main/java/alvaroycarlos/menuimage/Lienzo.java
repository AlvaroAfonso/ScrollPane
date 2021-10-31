/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alvaroycarlos.menuimage;

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
    
    //private Mat actImg = null;
    //private Mat prevImg = null;
    private Mat imgMat = null;
    private Mat umbMat = null;
    private BufferedImage actImg = null;
    private BufferedImage umbralImg = null;
    private int chosen = 0;
    private boolean redimension;
    
    private final int widhtRescaled = 950;
    private final int heigthRescaled = 694;
    
    public Lienzo(){
        this.setPreferredSize(new Dimension(widhtRescaled, heigthRescaled));
    }
    
   @Override
    public void paintComponent(Graphics g){
        if(actImg == null) return;
        super.paintComponent(g);
        
        //la formula introducida en los campos de posicion sirve para que en caso de que la imagen no ocupe
        //todo el espacio, aparezca centrada
        if(chosen == 1){
            g.drawImage(umbralImg, (int)((widhtRescaled - umbralImg.getWidth())/2), (int)((heigthRescaled - umbralImg.getHeight())/2), null);
        }else{
            g.drawImage(actImg, (int)((widhtRescaled - actImg.getWidth())/2), (int)((heigthRescaled - actImg.getHeight())/2), null);
        } 
    }
    
    public void setChosen(int chosen){
        this.chosen = chosen;
        this.repaint();
    }
    
    public BufferedImage getActImg(){
        return actImg;
    }
    
    public BufferedImage getUmbImg(){
        return umbralImg;
    }
    
    public Mat getUmbMat(){
        return umbMat;
    }
    
    public int getChosen(){
        return chosen;
    }
    
    public void loadImg(File file, boolean option){
        imgMat = Imgcodecs.imread(file.getAbsolutePath());
        actImg  = (BufferedImage) HighGui.toBufferedImage(imgMat);
        
        //indica si la imagen necesita
        redimension = option;
        if(redimension == true){
            actImg = this.resizeImg(actImg, widhtRescaled, heigthRescaled);
        }
        umbralImg = null;
        
        chosen = 0;
        this.repaint();
    }
    
    public boolean correctSize(File file){
        
        Mat checkFile = Imgcodecs.imread(file.getAbsolutePath());
        BufferedImage checkBufImg = (BufferedImage) HighGui.toBufferedImage(checkFile);
        
        try{
            if(checkBufImg.getHeight() < heigthRescaled && checkBufImg.getWidth() < widhtRescaled){
                return true;
            }
        }catch(Exception ex){
            
        }
        
        return false;
    }
    
    private BufferedImage resizeImg(BufferedImage original, int targetWidth, int targetHeight) {
        
        Image resultingImage = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        
        return outputImage;
    }
    
    public void umbralizar(int umbral){
        
        umbMat = this.umbralizar(imgMat, umbral);
        
        if(redimension == true){
            //imagen reescalada
            umbralImg = this.resizeImg((BufferedImage) HighGui.toBufferedImage(umbMat), widhtRescaled, heigthRescaled);
            
        }else{
            umbralImg = (BufferedImage) HighGui.toBufferedImage(umbMat);
        }
        chosen = 1;
        this.repaint();
    }
   
    private Mat umbralizar(Mat imagen_original, Integer umbral) {
        // crear dos imágenes en niveles de gris con el mismo
        // tamaño que la original
        Mat imagenGris = new Mat(imagen_original.rows(),imagen_original.cols(),CvType.CV_8U);
        
        Mat imagenUmbralizada = new Mat(imagen_original.rows(),imagen_original.cols(),CvType.CV_8U);
        
        // convierte a niveles de grises la imagen original
        Imgproc.cvtColor(imagen_original,imagenGris,Imgproc.COLOR_BGR2GRAY);
        
        // umbraliza la imagen:
        // - píxeles con nivel de gris > umbral se ponen a 1
        // - píxeles con nivel de gris <= umbra se ponen a 0
        Imgproc.threshold(imagenGris,imagenUmbralizada,umbral,255,Imgproc.THRESH_BINARY);
        
        // se devuelve la imagen umbralizada
        return imagenUmbralizada;
    }
    
}
