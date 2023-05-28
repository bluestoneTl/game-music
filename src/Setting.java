import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;

public class Setting {

    private static final float DEFAULT_VOLUME = 0.5f; // 榛樿闊抽噺
    private int volume_level=2;
    private float volume; // 褰撳墠闊抽噺

    JLabel sbt1= new JLabel(new ImageIcon("src/resource/image/button.png"));
    JLabel sbt2= new JLabel(new ImageIcon("src/resource/image/button.png"));



    JLabel set = new JLabel(new ImageIcon("src/resource/image/settings.png"));

    JLabel bt = new JLabel(new ImageIcon("src/resource/image/bt.png"));


    JLabel k1 = new JLabel("" + (char) PageController.keyCode1, JLabel.CENTER);
    JLabel k2 = new JLabel("" + (char) PageController.keyCode2, JLabel.CENTER);
    JLabel k3 = new JLabel("" + (char) PageController.keyCode3, JLabel.CENTER);
    JLabel k4 = new JLabel("" + (char) PageController.keyCode4, JLabel.CENTER);

    JLabel arrows1 =new JLabel(new ImageIcon("src/resource/image/arrows.png"));


    JLabel arrows2 =new JLabel(new ImageIcon("src/resource/image/arrows.png"));
    JLabel arrows3 =new JLabel(new ImageIcon("src/resource/image/arrows.png"));
    JLabel arrows4 =new JLabel(new ImageIcon("src/resource/image/arrows.png"));

    JLabel rail1 =new JLabel(new ImageIcon("src/resource/image/rail1.png"));
    JLabel rail2 =new JLabel(new ImageIcon("src/resource/image/rail1.png"));
    JLabel rail3 =new JLabel(new ImageIcon("src/resource/image/rail1.png"));
    JLabel rail4 =new JLabel(new ImageIcon("src/resource/image/rail1.png"));




    int index = 1;

    HitListener hitListener = new HitListener();

    RailListener1 railListener1 = new  RailListener1();
    RailListener2 railListener2= new  RailListener2();
    RailListener3 railListener3 = new  RailListener3();
    RailListener4 railListener4 = new  RailListener4();

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
        k1.setFont(new Font("瀵邦喛钂嬮梿鍛寸拨", Font.PLAIN, 60));
        k2.setFont(new Font("瀵邦喛钂嬮梿鍛寸拨", Font.PLAIN, 60));
        k3.setFont(new Font("瀵邦喛钂嬮梿鍛寸拨", Font.PLAIN, 60));
        k4.setFont(new Font("瀵邦喛钂嬮梿鍛寸拨", Font.PLAIN, 60));

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




        arrows1.setBounds(629, 684, 80, 80);
        arrows2.setBounds(727, 684, 80, 80);
        arrows3.setBounds(825, 684, 80, 80);
        arrows4.setBounds(923, 684, 80, 80);

        arrows1.setVisible(true);
        arrows2.setVisible(false);
        arrows3.setVisible(false);
        arrows4.setVisible(false);

        PageController.win.add(arrows1);
        PageController.win.add(arrows2);
        PageController.win.add(arrows3);
        PageController.win.add(arrows4);




        rail1.setBounds(629, 0,80, 700);
        rail2.setBounds(727, 0, 80, 700);
        rail3.setBounds(825, 0, 80, 700);
        rail4.setBounds(923, 0, 80, 700);

        rail1.addMouseListener(railListener1 );
        rail2.addMouseListener(railListener2);
        rail3.addMouseListener(railListener3 );
        rail4.addMouseListener(railListener4 );

        rail1.setVisible(true);
        rail2.setVisible(true);
        rail3.setVisible(true);
        rail4.setVisible(true);


        PageController.win.add(rail1);
        PageController.win.add(rail2);
        PageController.win.add(rail3);
        PageController.win.add(rail4);


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
                arrows1.setVisible(true);
                arrows2.setVisible(false);
                arrows3.setVisible(false);
                arrows4.setVisible(false);
                index = 2;
            }else if (index == 2) {
                PageController.keyCode2 = e.getKeyCode();
                k2.setText("" + (char) PageController.keyCode2);
                arrows1.setVisible(false);
                arrows2.setVisible(true);
                arrows3.setVisible(false);
                arrows4.setVisible(false);
                index = 3;
            }else if (index == 3) {
                PageController.keyCode3 = e.getKeyCode();
                k3.setText("" + (char) PageController.keyCode3);
                arrows1.setVisible(false);
                arrows2.setVisible(false);
                arrows3.setVisible(true);
                arrows4.setVisible(false);
                index = 4;
            }else if (index == 4) {
                PageController.keyCode4 = e.getKeyCode();
                k4.setText("" + (char) PageController.keyCode4);
                arrows1.setVisible(false);
                arrows2.setVisible(false);
                arrows3.setVisible(false);
                arrows4.setVisible(true);
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
            PageController.ret = true;//杩斿洖涓婚〉闈�
            PageController.win.removeKeyListener(hitListener);
            bt.removeMouseListener(returnListener);//绉婚櫎杩斿洖鎸夐挳鐩戝惉鍣�
            sbt1.removeMouseListener(soundListener1);//绉婚櫎闊抽噺+鎸夐挳鐩戝惉鍣�
            sbt2.removeMouseListener(soundListener2);//绉婚櫎闊抽噺-鎸夐挳鐩戝惉鍣�

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

    class   RailListener1 implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            index=1;
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

    class   RailListener2 implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            index=2;
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

    class   RailListener3 implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            index=3;

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

    class   RailListener4 implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            index=4;
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
