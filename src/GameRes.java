import javax.swing.*;
import java.applet.*;
import java.io.*;
import java.net.*;

public class GameRes {
    //资源加载模块
    //郭西达测试
    //perfect组件
    public static JLabel pecfectIcon = new JLabel(new ImageIcon("src/resource/image/perfect.png"));
    //good组件
    public static JLabel goodIcon = new JLabel(new ImageIcon("src/resource/image/good.png"));
    //miss 组件
    public static JLabel missIcon = new JLabel(new ImageIcon("src/resource/image/miss.png"));
    //rail : 轨道图片组件
    static JLabel rail =new JLabel(new ImageIcon("src/resource/image/rail.png"));
    // background : 背景图片组件
    static JLabel background = new JLabel(new ImageIcon("src/resource/image/livebg.png"));
     //perfectSound : perfect音效，用数组避免间隔过短无法完全播放
    public static AudioClip[] perfectSound = new AudioClip[3];
   // goodSound : good音效，用数组避免间隔过短无法完全播放
    public static AudioClip[] goodSound = new AudioClip[3];
    //结算界面bgm
    public static AudioClip resultbgm;
   

   
    public static void load(Note[] notes) {
        try {
            URL pecfectUrl = new File("src/resource/music/perfecthit.wav").toURI().toURL();
            URL goodUrl = new File("src/resource/music/goodhit.wav").toURI().toURL();
            perfectSound[0] = Applet.newAudioClip(pecfectUrl);
            perfectSound[1] = Applet.newAudioClip(pecfectUrl);
            perfectSound[2] = Applet.newAudioClip(pecfectUrl);
            goodSound[0] = Applet.newAudioClip(goodUrl);
            goodSound[1] = Applet.newAudioClip(goodUrl);
            goodSound[2] = Applet.newAudioClip(goodUrl);
            resultbgm = Applet.newAudioClip(new File("src/resource/music/resultbgm.wav").toURI().toURL());
        } catch (Exception e) {
        }

        for (int i = 0; i < 20; i++) {
            notes[i] = new Note();
            PageController.win.getContentPane().add(notes[i]);
        }

        pecfectIcon.setVisible(false);
        goodIcon.setVisible(false);
        missIcon.setVisible(false);
        PageController.win.getContentPane().add(pecfectIcon);
        PageController.win.getContentPane().add(goodIcon);
        PageController.win.getContentPane().add(missIcon);
        pecfectIcon.setBounds(950, 300, 300, 75);
        goodIcon.setBounds(950, 300, 300, 75);
        missIcon.setBounds(950, 300, 300, 75);

        ComboFigure.addFigure();

        rail.setVisible(false);
        background.setVisible(false);
        PageController.win.getContentPane().add(rail);
        PageController.win.getContentPane().add(background);
        rail.setBounds(330, 0, 616, 800);
        background.setBounds(0, 0, 1280, 800);
        background.setVisible(true);
        rail.setVisible(true);
    }
}
