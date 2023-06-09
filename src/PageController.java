import java.awt.*;
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
    public static int keyCode1 = KeyEvent.VK_F;         /* 四个音轨的按键 */
    public static int keyCode2 = KeyEvent.VK_G;
    public static int keyCode3 = KeyEvent.VK_H;
    public static int keyCode4 = KeyEvent.VK_J;
    public static int keyCode_sus = KeyEvent.VK_ESCAPE;        /* 暂停键ESC */
    /*生成一个 select 对象*/
    static Select select=new Select();
    /*生成一个 setting 对象*/
    static Setting setting=new Setting();
    /*生成一个help对象*/
    static Help help=new Help();
    /*默认构造函数*/
    public PageController() {
        win.setSize(1280, 800);
        win.setResizable(false);
        win.setVisible(true);
        win.setLayout(null);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        Toolkit tool=win.getToolkit();
        Image icon = tool.getImage("src/resource/image/logo.png");
        win.setIconImage(icon);
    }

    public void run() {
        while (true) {
            page = 0;
            ret = false;
            Menu.setfoundation();
            Menu.setbutton();
            Menu.setbackground();
            Menu.addfunction();
            Menu.moveimage();
            while (true) {
                if (page != 0) {
                    break;
                }
                System.out.print(""); //问题，只有加了这个才正常执行
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
