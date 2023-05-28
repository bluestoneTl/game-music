
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;


public class Suspend {
    static JLabel jlabel=new JLabel(new ImageIcon("src/resource/image/bt.png"));  /*暂停背景图片*/

    static JLabel back=new JLabel(new ImageIcon("src/resource/image/fig1.png"));   /*返回键图片*/

    static JLabel cont=new JLabel(new ImageIcon("src/resource/image/bt.png"));   /*继续游戏键图片*/



    public void Suspendinit(){
         // PageController.win.getContentPane().removeAll();
        //PageController.win.getContentPane().repaint();


        back.setVisible(false);
        back.setVisible(true);
        back.addMouseListener(returnListener);
        //PageController.win.add(back);
        PageController.win.getLayeredPane().add(back, new Integer(Integer.MAX_VALUE));
        back.setBounds(80, 670, 130, 39);

        cont.setVisible(false);
        cont.setVisible(true);
        cont.addMouseListener(continueListener);
    //    PageController.win.add(cont);
        PageController.win.getLayeredPane().add(cont, new Integer(Integer.MAX_VALUE));
        cont.setBounds(500, 670, 130, 39);

        jlabel.setVisible(false);
        jlabel.setVisible(true);
        //    PageController.win.add(jlabel);
        PageController.win.getLayeredPane().add(jlabel, new Integer(Integer.MAX_VALUE));
        jlabel.setBounds(250, 250, 130, 39);
        //PageController.win.getLayeredPane().remove(jlabel);
    }
    HitListener hitListener = new HitListener();
    ReturnListener returnListener = new ReturnListener();
    ContinueListener continueListener = new ContinueListener();

    /*键盘检测器*/
    class HitListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent e) {
        }
        /* 监视esc键被按下,继续游戏 */
        @Override
        public void keyPressed(KeyEvent e) {
            /** if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
             cont.setVisible(true);
             back.setVisible(true);
             jlabel.setVisible(true);}
             暂停线程添加 **/

            if (e.getKeyCode()==KeyEvent.VK_C){
                /* 线程继续添加 */
                Select.inGame.continueGame();
                PageController.win.removeKeyListener(hitListener);
                back.removeMouseListener(returnListener);
                back.setVisible(false);cont.setVisible(false);jlabel.setVisible(false);
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    /*鼠标检测器*/
    class ReturnListener implements MouseListener {
        /* 点击返回 */
        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.ret = true;
            PageController.win.removeKeyListener(hitListener);
            back.removeMouseListener(returnListener);
            PageController.win.getLayeredPane().remove(jlabel);
            PageController.win.getLayeredPane().remove(cont);
            PageController.win.getLayeredPane().remove(back);
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

    class ContinueListener implements MouseListener {
        /* 点击继续 */
        @Override
        public void mouseClicked(MouseEvent e) {

        }
        @Override
        public void mousePressed(MouseEvent e) {
            /* 线程继续添加 */
            Select.inGame.continueGame();
            PageController.win.removeKeyListener(hitListener);
            back.removeMouseListener(returnListener);
            PageController.win.getLayeredPane().remove(jlabel);
            PageController.win.getLayeredPane().remove(cont);
            PageController.win.getLayeredPane().remove(back);
            PageController.win.repaint();
            back.setVisible(false);
            cont.setVisible(false);
            jlabel.setVisible(false);
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