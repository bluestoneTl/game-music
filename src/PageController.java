

import java.awt.event.KeyEvent;

import javax.swing.*;


public class PageController {


    public static JFrame win = new JFrame();

    public static int page = 0;

    public static boolean ret =false;


    public static int keyCode1 = KeyEvent.VK_F;
    public static int keyCode2 = KeyEvent.VK_G;
    public static int keyCode3 = KeyEvent.VK_H;
    public static int keyCode4 = KeyEvent.VK_J;




    Select select = new Select();

    static Setting setting = new Setting();

    static  Help help=new Help();


    public PageController() {
        win.setSize(1280, 835);
        win.setResizable(false);
        win.setVisible(true);
        win.setLayout(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void run() {


        Menu.setfoundation();
        Menu.setbutton();
        Menu.setbackground();
        Menu.addfunction();
        Menu.moveimage();


        if (page == 1) {
                select.selectInit();

            }
            if (page == 2) {
                setting.setInit();

            }
            if (page == 3) {
                help.helpInit();

            }
    }
}
