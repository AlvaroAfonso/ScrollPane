/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alvaroycarlos.menuimage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
//import org.opencv.core.Mat;

/**
 *
 * @author alvaroafonsolopez
 */
public class Lienzo extends JPanel{
    
    //private Mat actImg = null;
    //private Mat prevImg = null;
    
    private BufferedImage actImg = null;
    private BufferedImage umbralImg = null;
    private Color pixels[][];
    private int chosen = 0;
    
    public Lienzo(){
        this.setPreferredSize(new Dimension(950, 694));
    }
    
   @Override
    public void paintComponent(Graphics g){
        if(actImg == null) return;
        super.paintComponent(g);
        if(chosen == 1){
            g.drawImage(umbralImg, 0, 0, null);
        }else{
            g.drawImage(actImg, 0, 0, null);
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
    
    public int getChosen(){
        return chosen;
    }
    
    public void loadImg(File img, int option){
        try{
            actImg = ImageIO.read(img);
            if(option == 0){
                actImg = this.resizeImg(actImg, 950, 694);
            }
            umbralImg = null;
        }catch(Exception ex){
            
        }
        chosen = 0;
        this.repaint();
    }
    
    public boolean correctSize(File img){
        try{
            if(ImageIO.read(img).getHeight() < 768 && ImageIO.read(img).getWidth() < 1024){
                return true;
            }
        }catch(Exception ex){
            
        }
        
        return false;
    }
    
    private BufferedImage resizeImg(BufferedImage original, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = original.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }
    
    public void umbralizar(int umbral){
        
        pixels = new Color[actImg.getHeight()][actImg.getWidth()];
        
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[0].length; j++) {
                pixels[i][j]= new Color(actImg.getRGB(j, i));
                //System.out.println(cont +":"+"RedGreenBlue:"+ bf.getRGB(j, i));
            }
        }
        
        for (int i = 0; i < actImg.getHeight(); i++) {
            for (int j = 0; j < actImg.getWidth(); j++) {
                Color pix = pixels[i][j];
                int promedio =(pix.getBlue()+pix.getRed()+pix.getGreen())/3;
                if (promedio < umbral) 
                    pixels[i][j]=Color.BLACK;
                else
                    pixels[i][j] = Color.WHITE;
            }
        }
        umbralImg = new BufferedImage(actImg.getWidth(), actImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < actImg.getHeight(); i++) {
            for (int j = 0; j < actImg.getWidth(); j++) {
                umbralImg.setRGB(j, i, pixels[i][j].getRGB());
            }
        } 
        chosen = 1;
        this.repaint();
    }
   
    /*private Mat umbralizar(Mat imagen_original, Integer umbral) {
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
    }*/
    
}
