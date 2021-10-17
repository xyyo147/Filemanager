package sample;
//import com.sun.awt.*;
import javax.swing.*;
import java.awt.*;


public class myJFrame extends JFrame {
    private float alpha;

    public myJFrame(String backPath, float alpha) {
        super();
        myContentPane rp = new myContentPane(backPath);
        rp.setOpaque(false);
        this.setContentPane(rp);
        this.setUndecorated(true);
        this.setSize(rp.img.getIconWidth(), rp.img.getIconHeight());
//        AWTUtilities.setWindowOpaque(this, false);
        this.alpha = alpha;

    }



    private class myContentPane extends JPanel {
        public ImageIcon img;

        public myContentPane(String backPath) {
            super();
            img = new ImageIcon(TEST.class.getResource(backPath));
        }
        @Override
        protected void paintComponent(Graphics g){
            AlphaComposite ac=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
            Composite old=((Graphics2D)g).getComposite();
            ((Graphics2D)g).setComposite(ac);
            if(img!=null){
                g.drawImage(img.getImage(),0,0,getWidth(),getHeight(),this);
            }
            ((Graphics2D)g).setComposite(old);
            super.paintComponent(g);
        }
    }
}