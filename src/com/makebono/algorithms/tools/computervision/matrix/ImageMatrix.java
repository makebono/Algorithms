package com.makebono.algorithms.tools.computervision.matrix;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.makebono.algorithms.tools.computervision.Display;

/** 
 * @ClassName: Matrix 
 * @Description: Matrix of rgb value
 * @author makebono
 * @date 2017年11月27日 上午10:46:09 
 *  
 */
public class ImageMatrix {
    private BufferedImage image;
    private int[][][] matrix;

    public ImageMatrix() {
        this.image = null;
        this.matrix = null;
    }

    public ImageMatrix(final String location) throws IOException {
        this.image = ImageIO.read(new File(location));
        this.matrix = new int[image.getHeight()][image.getWidth()][3];
        this.matrix = this.generateMatrix();
    }

    public ImageMatrix(final int[][][] matrix) {
        this.matrix = matrix;
        this.image = new BufferedImage(this.matrix[0].length, this.matrix.length, BufferedImage.TYPE_INT_RGB);
        this.image = this.rebuildImage();
    }

    public ImageMatrix(final int m, final int n) {
        this.matrix = new int[m][n][3];
        this.image = new BufferedImage(this.matrix[0].length, this.matrix.length, BufferedImage.TYPE_INT_RGB);
    }

    private int[][][] generateMatrix() {
        int cursor = 0;
        // int[] test = this.image.getRaster().getDataBuffer().getOffsets();
        for (int i = 0; i < this.image.getHeight(); i++) {
            for (int o = 0; o < this.image.getWidth(); o++) {
                // I prefered the channel ordered as R G B, as in Matlab.
                for (int c = 2; c != -1; c--)
                    this.matrix[i][o][c] = this.image.getRaster().getDataBuffer().getElem(cursor++);
            }
        }
        return this.matrix;
    }

    public BufferedImage rebuildImage() {
        for (int i = 0; i < image.getHeight(); i++) {
            for (int o = 0; o < image.getWidth(); o++) {
                final Color newColor = new Color(this.matrix[i][o][0], this.matrix[i][o][1], this.matrix[i][o][2]);
                this.image.setRGB(o, i, newColor.getRGB());
            }
        }

        return this.image;
    }

    public BufferedImage rgb2gray() {
        final BufferedImage image = new BufferedImage(this.matrix[0].length, this.matrix.length,
                BufferedImage.TYPE_BYTE_GRAY);

        for (int i = 0; i < image.getHeight(); i++) {
            for (int o = 0; o < image.getWidth(); o++) {
                final Color newColor = new Color(this.matrix[i][o][0], this.matrix[i][o][1], this.matrix[i][o][2]);
                image.setRGB(o, i, newColor.getRGB());
            }
        }

        return image;
    }

    public int[][][] matrix() {
        return this.matrix;
    }

    public BufferedImage image() {
        return this.image;
    }

    public void set(final int m, final int n, final int c, final int data) {
        this.matrix[m][n][c] = data;
        // this.rebuildImage();
    }

    public void imshow() throws IOException {
        Display.imshow(this.image, "Image");
    }
}
