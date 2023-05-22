import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Select {
    /*生成一个 Ingame 对象*/
   InGame inGame = new InGame();

    /*生成一个背景图片*/
    JLabel b = new JLabel(new ImageIcon("src/resource/image/select.png"));

    /*页面加载*/
    public void selectInit() {
        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();
        Cover cover1 = new Cover("START-DASH!!");
        PageController.win.getContentPane().add(cover1);
        cover1.setLocation(100, 120);
        cover1.setVisible(true);
        cover1.addMouseListener(new selectListener());

        b.setVisible(false);
        PageController.win.add(b);
        b.setBounds(0, 0, 1280, 800);
        b.setVisible(true);
    }

    /*鼠标检测器*/
    class selectListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.win.getContentPane().removeAll();
            PageController.win.getContentPane().repaint();
            inGame.inGameInit(((Cover) e.getComponent()).audioFilePath, ((Cover) e.getComponent()).scoreFilePath);
            inGame.start();
        }
        @Override
        public void mousePressed(MouseEvent e) {
        }
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
}
