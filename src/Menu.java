
import javax.swing.*;
import javax.swing.*;
import java.awt.*;

import java.awt.*;
import java.awt.event.*;

public class Menu {

    public static int which = -1;
    public static  JLabel background;
    public static JLabel b1;
    public static JLabel b2;
    public static JLabel b3;
    public static JLabel b4;
    public static void setfoundation(){
        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();
    }
    public static void setbackground(){
        background = new JLabel(new ImageIcon("src/resource/image/menubg.png"));

        background.setBounds(0, 0, PageController.win.getWidth(), PageController.win.getHeight());
        PageController.win.add(background);
        PageController.win.repaint();
    }

    public static void setbutton(){
        b1=new JLabel(new ImageIcon("src/resource/image/b1.png"));
        b1.setBounds(900, 80, 250, 250);
        PageController.win.add(b1);
        PageController.win.repaint();

        b2=new JLabel(new ImageIcon("src/resource/image/b2.png"));
        b2.setBounds(900, 250, 250, 200);
        PageController.win.add(b2);
        PageController.win.repaint();

        b3=new JLabel(new ImageIcon("src/resource/image/b3.png"));
        b3.setBounds(900, 400, 250, 200);
        PageController.win.add(b3);
        PageController.win.repaint();

        b4=new JLabel(new ImageIcon("src/resource/image/b4.png"));
        b4.setBounds(900, 550, 250, 200);
        PageController.win.add(b4);
        PageController.win.repaint();

    }

    public static void addfunction(){
        b1.addMouseListener(new
                                    MouseAdapter() {

                                        @Override public void mouseClicked(MouseEvent e) {
                                            PageController.win.getContentPane().removeAll();
                                            PageController.win.getContentPane().repaint();
                                            /*淇锛岄厤鍚坧agecontroller瀹炵幇椤甸潰鍒囨崲*/
                                            PageController.page=1;  //1琛ㄧずselect鐣岄潰
                                        }

                                        @Override public void mouseEntered(MouseEvent e) {

                                        }

                                        @Override public void mouseExited(MouseEvent e) {

                                        } });

        b2.addMouseListener(new
                                    MouseAdapter() {

                                        @Override public void mouseClicked(MouseEvent e) {
                                            PageController.win.getContentPane().removeAll();
                                            PageController.win.getContentPane().repaint();
                                            PageController.page=2;   //setting鐣岄潰
                                        }

                                        @Override public void mouseEntered(MouseEvent e) {

                                        }

                                        @Override public void mouseExited(MouseEvent e) {

                                        } });

        b3.addMouseListener(new
                                    MouseAdapter() {

                                        @Override public void mouseClicked(MouseEvent e) {
                                            PageController.win.getContentPane().removeAll();
                                            PageController.win.getContentPane().repaint();
                                            PageController.page=3;    //help鐣岄潰
                                        }

                                        @Override public void mouseEntered(MouseEvent e) {

                                        }

                                        @Override public void mouseExited(MouseEvent e) {

                                        } });

        b4.addMouseListener(new
                                    MouseAdapter() {

                                        @Override public void mouseClicked(MouseEvent e) {
                                            System.exit(0);
                                        }

                                        @Override public void mouseEntered(MouseEvent e) {

                                        }

                                        @Override public void mouseExited(MouseEvent e) {

                                        } });
    }



    public static void moveimage(){}
}