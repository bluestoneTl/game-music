import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.sound.sampled.*;

public class Setting {

    private static final float DEFAULT_VOLUME = 0.5f; // 默认音量
    private int volume_level=2;
    private float volume; // 当前音量

    JLabel sbt1= new JLabel(new ImageIcon("src/resource/image/button.png"));
    JLabel sbt2= new JLabel(new ImageIcon("src/resource/image/button.png"));



    JLabel set = new JLabel(new ImageIcon("src/resource/image/settings.png"));

    JLabel bt = new JLabel(new ImageIcon("src/resource/image/bt.png"));


    JLabel k1 = new JLabel("" + (char) PageController.keyCode1, JLabel.CENTER);
    JLabel k2 = new JLabel("" + (char) PageController.keyCode2, JLabel.CENTER);
    JLabel k3 = new JLabel("" + (char) PageController.keyCode3, JLabel.CENTER);
    JLabel k4 = new JLabel("" + (char) PageController.keyCode4, JLabel.CENTER);

    int index = 1;

    HitListener hitListener = new HitListener();


    ReturnListener returnListener = new ReturnListener();

    SoundListrner1 soundListener1=new  SoundListrner1();
    SoundListrner2 soundListener2=new  SoundListrner2();


    public void setInit() {
        index = 1;

        PageController.win.getContentPane().removeAll();
        PageController.win.getContentPane().repaint();

        sbt1.setBounds(100, 100, 150, 100);
        sbt2.setBounds(100, 200, 150, 100);
        sbt1.addMouseListener(soundListener1);
        sbt2.addMouseListener(soundListener2);

        PageController.win.add(sbt1);
        PageController.win.add(sbt2);
        PageController.win.getContentPane().repaint();
        k1.setFont(new Font("寰蒋闆呴粦", Font.PLAIN, 60));
        k2.setFont(new Font("寰蒋闆呴粦", Font.PLAIN, 60));
        k3.setFont(new Font("寰蒋闆呴粦", Font.PLAIN, 60));
        k4.setFont(new Font("寰蒋闆呴粦", Font.PLAIN, 60));

        k1.setVisible(false);
        k2.setVisible(false);
        k3.setVisible(false);
        k4.setVisible(false);

        PageController.win.add(k1);
        PageController.win.add(k2);
        PageController.win.add(k3);
        PageController.win.add(k4);

        k1.setBounds(629, 604, 80, 80);
        k2.setBounds(727, 604, 80, 80);
        k3.setBounds(825, 604, 80, 80);
        k4.setBounds(923, 604, 80, 80);

        k1.setVisible(true);
        k2.setVisible(true);
        k3.setVisible(true);
        k4.setVisible(true);

        bt.setVisible(false);
        bt.addMouseListener(returnListener);
        PageController.win.add(bt);
        bt.setBounds(80, 670, 130, 39);
        bt.setVisible(true);

        set.setVisible(false);
        PageController.win.add(set);
        set.setBounds(0, 0, 1280, 800);
        set.setVisible(true);

        PageController.win.addKeyListener(hitListener);
    }

    class SoundListrner1 implements   MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(volume<1.0f) {
                volume+=0.1f;
            }

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

    class SoundListrner2 implements   MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            if(volume>0.0f) {
                volume-=0.1f;
            }

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

    class HitListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub

        }


        @Override
        public void keyPressed(KeyEvent e) {
            if (index == 1) {
                PageController.keyCode1 = e.getKeyCode();
                k1.setText("" + (char) PageController.keyCode1);
                index = 2;
            }else if (index == 2) {
                PageController.keyCode2 = e.getKeyCode();
                k2.setText("" + (char) PageController.keyCode2);
                index = 3;
            }else if (index == 3) {
                PageController.keyCode3 = e.getKeyCode();
                k3.setText("" + (char) PageController.keyCode3);
                index = 4;
            }else if (index == 4) {
                PageController.keyCode4 = e.getKeyCode();
                k4.setText("" + (char) PageController.keyCode4);
                index = 1;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub

        }

    }


    class ReturnListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.ret = true;//返回主页面
            PageController.win.removeKeyListener(hitListener);
            bt.removeMouseListener(returnListener);//移除返回按钮监听器
            sbt1.removeMouseListener(soundListener1);//移除音量+按钮监听器
            sbt2.removeMouseListener(soundListener2);//移除音量-按钮监听器

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

    public void adjustVolume() {
        try {
            Mixer.Info[] mixerInfos = AudioSystem.getMixerInfo();
            Mixer mixer = AudioSystem.getMixer(mixerInfos[0]);
            Line.Info lineInfo = new Line.Info(SourceDataLine.class);
            Line line = mixer.getLine(lineInfo);
            line.open();

            FloatControl volumeControl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(volume);

            line.close();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
