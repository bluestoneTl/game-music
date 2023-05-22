import java.awt.event.KeyEvent;
import javax.swing.*;

public class PageController {
    /*   win :主窗口   */
    public static JFrame win=new JFrame();
    /*   page:页面标志位   */
    public static int page=0;
    /*   ret:返回标志位   */
    public static boolean ret= false;
    /*   按键   */
    public static int keyCode1 = KeyEvent.VK_F;
    public static int keyCode2 = KeyEvent.VK_G;
    public static int keyCode3 = KeyEvent.VK_H;
    public static int keyCode4 = KeyEvent.VK_J;
    /*生成一个 select 对象*/
    static Select select=new Select();
    /*生成一个 setting 对象*/
    Setting setting=new Setting();
    /*生成一个help对象*/
    Help help=new Help();
    /*默认构造函数*/
    public PageController() {
        win.setSize(1280, 835);
        win.setResizable(false);
        win.setVisible(true);
        win.setLayout(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void run() {
        while (true) {
            page = 0;
            ret = false;
            Menu.setfoundation();
            Menu.setbutton();
            Menu.setbackground();
            Menu.moveimage();
            while (true) {
                if (page != 0) {
                    break;
                }
            }
            if (page == 1) {
                select.selectInit();
                while (true) {
                    System.out.print("");
                    if (ret) {
                        break;
                    }
                }
            }
            if (page == 2) {
                setting.setInit();
                while (true) {
                    System.out.print("");
                    if (ret) {
                        break;
                    }
                }
            }
            if (page == 3) {
                help.helpInit();
                while (true) {
                    System.out.print("");
                    if (ret) {
                        break;
                    }
                }
            }
        }
    }
}
