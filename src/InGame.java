import java.awt.event.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
import javazoom.jl.player.Player;



public class InGame {
    // 轨道1 2 3 4 的坐标
    int x1 = 409;
    int x2 = 530;
    int x3 = 651;
    int x4 = 772;

    // 判定线坐标x
    int missUp = 574;       // miss判定上限坐标
    int goodUp = 614;       // good判定上限坐标
    int perfectUp = 644;    // perfect判定上限坐标
    int perfectDown = 724;  // perfect判定下限坐标
    int goodDown = 754;     // good判定下限坐标

    // 音符方块
    Note[] notes = new Note[20];       // note（节奏图标）组件数组
    int noteIndex = 0;      // note组件数组下标
    String noteRecord;      // noteRecord : 记录从文本文件读入的谱面
    int next = 0;           // next : 谱面内容下标
    int speedLimit = 0;     // speedLimit : 读入周期限制，即每次读入谱面间隔的图像绘制次数
    int speed = 0;          // speed : 记录当前绘制次数，到达读入周期限制归零
    int combo = 0;          // 当前combo数
    int maxCombo = 0;       // maxCombo : 最大combo数（最大连击数）
    int perfectAmount = 0;  // perfectAmount : perfect总数
    int goodAmount = 0;     // goodAmount : good总数
    int missAmount = 0;     // missAmount : miss总数
    
    // 
    Player gamePlayer;      // gamePlayer : 音乐播放器
    Timer timer;            // timer : 定时器，完成实现逐帧绘制
    int perfectSoundIndex = 0;      // perfectSoundIndex : perfect音效数组下标，下同
    int goodSoundIndex = 0;
    HitListener hitListener = new HitListener();        // hitListener : 监视器
    ReturnListener returnListener = new ReturnListener();       // returnListener : 监视器
    
    // 
    Result result = new Result();       // result : 结果类



// ==================== 分割线 ====================
// ==================== 分割线 ====================
// ==================== 分割线 ====================



    /** 
    * @Title: inGameInit 
    * @Description: 设置文件路径
    * @param @param audioFilePath 音频文件
    * @param @param scoreFilePath 文本文件
    * @return void  
    * @throws 
    */
    public void inGameInit(String audioFilePath, String scoreFilePath) {
        noteIndex = 0;
        next = 0;
        speedLimit = 0;
        speed = 0;
        combo = 0;
        maxCombo = 0;
        perfectAmount = 0;
        goodAmount = 0;
        missAmount = 0;
        perfectSoundIndex = 0;
        goodSoundIndex = 0;

        GameRes.load(notes);
        try {
            FileReader in = new FileReader(scoreFilePath);
            BufferedReader reader = new BufferedReader(in);
            speedLimit = Integer.valueOf(reader.readLine()).intValue();// note生成间隔，以刷新次数为单位
            noteRecord = reader.readLine();
            reader.close();
            in.close();

            gamePlayer = new Player(new FileInputStream(new File(audioFilePath)));

        } catch (Exception e) {
        }
    }

    /** 
    * @Title: start 
    * @Description: 加载资源，开始播放音乐、绘制图像
    * @param  
    * @return void  
    * @throws 
    */
    public void start() {
        PageController.win.addKeyListener(hitListener);
        Thread music = new Thread() {
            public void run() {
                try {
                    gamePlayer.play();
                } catch (Exception e) {
                }
            }
        };
        music.start();

        timer = new Timer(true);
        timer.scheduleAtFixedRate(new Drop(), 0, 10);// 第三个参数为画面刷新间隔，以毫秒为单位
    }

    /** 
    * @ClassName: Drop 
    * @Description: 定时器执行的任务，逐帧绘制
    * @author Zhang Yuxuan
    * @date 2019年6月5日 下午6:38:06 
    *  
    */
    class Drop extends TimerTask {
        /*
        * Title: run
        *Description: TimerTask任务内容
        * @see java.util.TimerTask#run() 
        */
        @SuppressWarnings("deprecation")
        @Override
        public void run() {
            if (speed == 0) {
                switch (noteRecord.charAt(next)) {
                case '0': {
                    next++;
                    break;
                }
                case '1': {
                    notes[noteIndex].x = x1;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case '2': {
                    notes[noteIndex].x = x2;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case '3': {
                    notes[noteIndex].x = x3;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case '4': {
                    notes[noteIndex].x = x4;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case '5': {
                    timer.cancel();
                    gamePlayer.close();
                    PageController.win.removeKeyListener(hitListener);
                    PageController.win.addMouseListener(returnListener);
                    try {
                        GameRes.resultbgm.play();
                    } catch (Exception e) {
                    }
                    result.resultPage(perfectAmount, goodAmount, missAmount, maxCombo);
                    return;
                }
                case 'A': {
                    notes[noteIndex].x = x1;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x2;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case 'B': {
                    notes[noteIndex].x = x2;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x3;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case 'C': {
                    notes[noteIndex].x = x3;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x4;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case 'D': {
                    notes[noteIndex].x = x1;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x3;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case 'E': {
                    notes[noteIndex].x = x2;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x4;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                case 'F': {
                    notes[noteIndex].x = x1;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    notes[noteIndex].x = x4;
                    notes[noteIndex].y = 0;
                    notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
                    notes[noteIndex].setVisible(true);
                    noteIndex++;
                    if (noteIndex == 20) {
                        noteIndex = 0;
                    }
                    next++;
                    break;
                }
                }
            }
            speed++;
            if (speed == speedLimit) {
                speed = 0;
            }
            for (int i = 0; i < 20; i++) {
                if (notes[i].isVisible()) {
                    notes[i].y += 4;
                    notes[i].setLocation(notes[i].x, notes[i].y);
                    if (notes[i].y == 800) {
                        notes[i].setVisible(false);
                        GameRes.pecfectIcon.setVisible(false);
                        GameRes.goodIcon.setVisible(false);
                        GameRes.missIcon.setVisible(true);
                        ComboFigure.comboFigure1[combo % 10].setVisible(false);
                        ComboFigure.comboFigure10[combo / 10 % 10].setVisible(false);
                        ComboFigure.comboFigure100[combo / 100 % 10].setVisible(false);
                        ComboFigure.comboFigure1[0].setVisible(true);
                        ComboFigure.comboFigure10[0].setVisible(true);
                        ComboFigure.comboFigure100[0].setVisible(true);
                        combo = 0;
                        missAmount++;
                    }
                }
            }
        }
    }

    /** 
    * @ClassName: HitListener 
    * @Description: 键盘监听器
    * @author Zhang Yuxuan
    * @date 2019年6月5日 下午6:40:39 
    *  
    */
    class HitListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // TODO Auto-generated method stub
            
        }

        /*
        * Title: keyPressed
        *Description: 响应按键事件，判定note打击情况
        * @param e 
        * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent) 
        */
        @SuppressWarnings("deprecation")
        @Override
        public void keyPressed(KeyEvent e) {
            int noteIndex = -1;
            int noteY = -1;
            int noteX = 0;
            int keyCode = e.getKeyCode();
            if (keyCode == PageController.keyCode1) {
                noteX = x1;
            } else if (keyCode == PageController.keyCode2) {
                noteX = x2;
            } else if (keyCode == PageController.keyCode3) {
                noteX = x3;
            } else if (keyCode == PageController.keyCode4) {
                noteX = x4;
            }
            for (int i = 0; i < 20; i++) {
                if (notes[i].x == noteX && notes[i].isVisible() && notes[i].y > noteY) {
                    noteY = notes[i].y;
                    noteIndex = i;
                }
            }
            if (noteIndex == -1) {
                return;
            }
            if (noteY >= perfectUp && noteY <= perfectDown) {
                notes[noteIndex].setVisible(false);
                GameRes.pecfectIcon.setVisible(true);
                GameRes.goodIcon.setVisible(false);
                GameRes.missIcon.setVisible(false);
                ComboFigure.comboFigure1[combo % 10].setVisible(false);
                ComboFigure.comboFigure10[combo / 10 % 10].setVisible(false);
                ComboFigure.comboFigure100[combo / 100 % 10].setVisible(false);
                combo++;
                ComboFigure.comboFigure1[combo % 10].setVisible(true);
                ComboFigure.comboFigure10[combo / 10 % 10].setVisible(true);
                ComboFigure.comboFigure100[combo / 100 % 10].setVisible(true);
                GameRes.perfectSound[perfectSoundIndex].play();
                perfectSoundIndex++;
                if (perfectSoundIndex == 3) {
                    perfectSoundIndex = 0;
                }
                if (combo > maxCombo) {
                    maxCombo = combo;
                }
                perfectAmount++;
            } else if ((noteY < perfectUp && noteY >= goodUp) || (noteY > perfectDown && noteY <= goodDown)) {
                notes[noteIndex].setVisible(false);
                GameRes.pecfectIcon.setVisible(false);
                GameRes.goodIcon.setVisible(true);
                GameRes.missIcon.setVisible(false);
                ComboFigure.comboFigure1[combo % 10].setVisible(false);
                ComboFigure.comboFigure10[combo / 10 % 10].setVisible(false);
                ComboFigure.comboFigure100[combo / 100 % 10].setVisible(false);
                combo++;
                ComboFigure.comboFigure1[combo % 10].setVisible(true);
                ComboFigure.comboFigure10[combo / 10 % 10].setVisible(true);
                ComboFigure.comboFigure100[combo / 100 % 10].setVisible(true);
                GameRes.goodSound[goodSoundIndex].play();
                goodSoundIndex++;
                if (goodSoundIndex == 3) {
                    goodSoundIndex = 0;
                }
                if (combo > maxCombo) {
                    maxCombo = combo;
                }
                goodAmount++;
            } else if ((noteY < goodUp && noteY >= missUp) || (noteY > goodDown)) {
                notes[noteIndex].setVisible(false);
                GameRes.pecfectIcon.setVisible(false);
                GameRes.goodIcon.setVisible(false);
                GameRes.missIcon.setVisible(true);
                ComboFigure.comboFigure1[combo % 10].setVisible(false);
                ComboFigure.comboFigure10[combo / 10 % 10].setVisible(false);
                ComboFigure.comboFigure100[combo / 100 % 10].setVisible(false);
                ComboFigure.comboFigure1[0].setVisible(true);
                ComboFigure.comboFigure10[0].setVisible(true);
                ComboFigure.comboFigure100[0].setVisible(true);
                combo = 0;
                missAmount++;
            }
        }

    }
    
    /** 
    * @ClassName: ReturnListener 
    * @Description: 鼠标监视器
    * @author Zhang Yuxuan
    * @date 2019年6月8日 下午8:17:27 
    *  
    */
    class ReturnListener implements MouseListener {

        /*
        * Title: mouseClicked
        *Description: 点击返回
        * @param e 
        * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent) 
        */
        @Override
        public void mouseClicked(MouseEvent e) {
            PageController.ret = true;
            PageController.win.removeMouseListener(returnListener);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub
            
        }

    }

}
