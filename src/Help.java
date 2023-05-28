import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Help {


    JLabel h = new JLabel(new ImageIcon("src/resource/image/help.png"));


    public static JLabel bt = new JLabel();


    ReturnListener returnListener = new ReturnListener();


    public void helpInit() {
        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();

        bt.setVisible(false);

        bt=new JLabel(new ImageIcon(""));
        PageController.win.repaint();
        PageController.win.add(bt);
        bt.setBounds(1057, 530, 184, 68);
        bt.setVisible(true);
        bt.addMouseListener(returnListener);
        h.setVisible(false);
        PageController.win.add(h);
        h.setBounds(0, 0, 1280, 800);
        h.setVisible(true);

    }


    class ReturnListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.win.getContentPane().removeAll(); // 清除当前页面的组件
            PageController.page = 0; // 切换到菜单页面的页面编号
            PageController.ret = true;



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
