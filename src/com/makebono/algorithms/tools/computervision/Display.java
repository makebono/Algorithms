/**   
 * @Title: Imshow.java 
 * @Package com.makebono.algorithms.tools.computervision 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author tangxj
 * @date 2017年11月27日 上午11:41:54  
 */
package com.makebono.algorithms.tools.computervision;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * @ClassName: Display 
 * @Description: Display image
 * @author makebono
 * @date 2017年11月27日 上午11:41:54 
 *  
 */
public class Display {
    public static void imshow(final BufferedImage newImg, final String title) throws IOException {
        final BufferedImage img = newImg;
        final JFrame window = new JFrame();

        window.setSize(img.getWidth() + 100, img.getHeight() + 100);
        window.setTitle(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Container pane = window.getContentPane();
        final Panel panel = new Panel(img);
        pane.add(panel);

        window.setVisible(true);
    }
}

class Panel extends JPanel {

    private static final long serialVersionUID = 1L;
    BufferedImage img;

    public Panel(final BufferedImage img) {
        this.img = img;
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.img, 25, 25, null);
    }
}
