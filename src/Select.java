import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class Select {
    /*生成一个 Ingame 对象*/
    public static InGame inGame = new InGame();

    /*生成一个背景图片*/
    JLabel b = new JLabel(new ImageIcon("src/resource/image/select.png"));

    /*返回按钮*/
    JLabel bt = new JLabel(new ImageIcon("src/resource/image/bt.png"));


    /*返回按钮监听器*/
    ReturnListener returnListener = new ReturnListener();
    /*页面加载*/
    public void selectInit() {
        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();

        Cover cover1 = new Cover("START-DASH!!");
        PageController.win.getContentPane().add(cover1);
        cover1.setLocation(100, 120);
        cover1.setVisible(true);
        cover1.addMouseListener(new selectListener());

        Cover cover2 = new Cover("Tik-Tok");
        PageController.win.getContentPane().add(cover2);
        cover2.setLocation(100, 320);
        cover2.setVisible(true);
        cover2.addMouseListener(new selectListener());

        Cover cover3 = new Cover("One-Last-Kiss");
        PageController.win.getContentPane().add(cover3);
        cover3.setLocation(100, 520);
        cover3.setVisible(true);
        cover3.addMouseListener(new selectListener());




        /*给返回按钮添加鼠标监听器，设置大小和位置*/
        bt.setVisible(false);
        bt.addMouseListener(returnListener);
        PageController.win.add(bt);
        bt.setBounds(145, 650, 184, 168);
        bt.setVisible(true);


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

    class ReturnListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.ret = true;//返回主页面
            bt.removeMouseListener(returnListener);//移除返回按钮监听器
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
