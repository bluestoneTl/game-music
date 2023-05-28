import javazoom.jl.decoder.JavaLayerException;

import java.awt.event.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;
//import javazoom.jl.player.Player;



public class InGame {


    // 轨道1 2 3 4 的坐标
    int[] xi = { 0, 409, 530, 651, 772 };
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
    Player_me gamePlayer;      // gamePlayer : 音乐播放器
    Timer timer;            // timer : 定时器，完成实现逐帧绘制
    int perfectSoundIndex = 0;      // perfectSoundIndex : perfect音效数组下标，下同
    int goodSoundIndex = 0;        // goodSoundIndex : good音效数组下标
    HitListener hitListener = new HitListener();        // hitListener : 监视器
    ReturnListener returnListener = new ReturnListener();       // returnListener : 监视器
    
    //
    Result result = new Result();       // result : 结果类



// ==================== 分割线 ====================
// ==================== 下面是方法 ======   ==============
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
        noteIndex = 0;          // note组件数组下标
        next = 0;               // next : 谱面内容下标
        speedLimit = 0;         // speedLimit : 读入周期限制，即每次读入谱面间隔的图像绘制次数
        speed = 0;              // speed : 记录当前绘制次数，到达读入周期限制归零
        combo = 0;              // 当前combo数
        maxCombo = 0;           // maxCombo : 最大combo数（最大连击数）
        perfectAmount = 0;      // perfectAmount : perfect总数
        goodAmount = 0;         // goodAmount : good总数
        missAmount = 0;         // missAmount : miss总数
        perfectSoundIndex = 0;  // perfectSoundIndex : perfect音效数组下标，下同
        goodSoundIndex = 0;     // goodSoundIndex : good音效数组下标

        GameRes.load(notes);
        try {
            FileReader in = new FileReader(scoreFilePath);      // scoreFilePath文件读入，缺少对异常的处理 
            BufferedReader reader = new BufferedReader(in);     
            speedLimit = Integer.valueOf(reader.readLine()).intValue();     // note生成间隔，以刷新次数为单位
            noteRecord = reader.readLine();
            reader.close();
            in.close();

            gamePlayer = new Player_me(new FileInputStream(new File(audioFilePath)));      // 用音频文件初始化gamePlayer

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
        PageController.win.addKeyListener(hitListener);     // 给PageController.win添加监听器
        Thread music = new Thread() {            // 创建music线程，用于播放音乐
            public void run() {
                try {
                    gamePlayer.play();      // gamePlayer播放音乐
                } catch (Exception e) {
                }
            }
        };
        music.start();

        timer = new Timer(true);        // 创建timer定时器
        timer.scheduleAtFixedRate(new Drop(), 0, 10);       // 延迟0ms，每10ms执行一次Drop任务
    }

    /** 
    * @Title: suspend 
    * @Description: 游戏暂停 
    * @return void  
    * @throws 
    */
    public void suspend() {
        timer.cancel();     // 取消定时器
        gamePlayer.pause();     // 暂停播放音乐
        PageController.win.removeKeyListener(hitListener);          // 移除hitListener监听器
        PageController.win.addMouseListener(returnListener);        // 添加returnListener监听器
    }

    /** 
    * @Title: continueGame 
    * @Description: 游戏继续 
    * @return void  
    * @throws 
    */
    public void continueGame() {
        timer = new Timer(true);        // 创建timer定时器
        timer.scheduleAtFixedRate(new Drop(), 0, 10);       // 延迟0ms，每10ms执行一次Drop任务
        try {
            gamePlayer.resume();      // 继续播放音乐
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        PageController.win.addKeyListener(hitListener);     // 给PageController.win添加监听器
        PageController.win.removeMouseListener(returnListener);        // 移除returnListener监听器
    }

    /** 
    * @Title: end
    * @Description: 游戏结束的操作
    * @return void  
    * @throws 
    */
    public void end() {
        timer.cancel();     // 取消定时器
        gamePlayer.close();     // 关闭音乐播放器
        PageController.win.removeKeyListener(hitListener);          // 移除hitListener监听器
        PageController.win.addMouseListener(returnListener);        // 添加returnListener监听器
        try {
            GameRes.resultbgm.play();       // 播放结果界面背景音乐
        } catch (Exception e) {
        }
        result.resultPage(perfectAmount, goodAmount, missAmount, maxCombo);     // 展示结束页面
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
                /*
                 * 文本表示的含义：
                 * 0：空白
                 * 1 2 3 4：音符
                 * 5：结束
                 * A：1+2
                 * B：2+3
                 * C：3+4
                 * D：1+3
                 * E：2+4
                 * F：1+4
                 */
                char ci = noteRecord.charAt(next);      // ci : 当前字符
                if (ci=='0') {
                    // 空节拍
                }
                else if (ci=='1' || ci=='2' || ci=='3' || ci=='4') {
                    int x = xi[ci-'0'];     // x : 音符x坐标
                    track(x, 0);            // 绘制音符
                }
                else if (ci=='5') {
                    end();      // 游戏结束
                    return;
                }
                else if (ci=='A') {
                    track(xi[1], 0);
                    track(xi[2], 0);
                }
                else if (ci=='B') {
                    track(xi[2], 0);
                    track(xi[3], 0);
                }
                else if (ci=='C') {
                    track(xi[3], 0);
                    track(xi[4], 0);
                }
                else if (ci=='D') {
                    track(xi[1], 0);
                    track(xi[3], 0);
                }
                else if (ci=='E') {
                    track(xi[2], 0);
                    track(xi[4], 0);
                }
                else if (ci=='F') {
                    track(xi[1], 0);
                    track(xi[4], 0);
                }
                next++;
            }
            speed++;
            if (speed == speedLimit) {
                speed = 0;
            }
            // 遍历note组件数组，若note组件可见，则向下移动，这也是用于更新动画
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
    * @Title: track
    * @Description: 加载轨道
    * @param x 轨道x坐标
    * @param y 轨道y坐标    
    * @return void  
    * @throws 
    */
    public void track(int x, int y) {
        notes[noteIndex].x = x;
        notes[noteIndex].y = y;
        notes[noteIndex].setLocation(notes[noteIndex].x, notes[noteIndex].y);
        notes[noteIndex].setVisible(true);
        noteIndex++;
        if (noteIndex == 20) {
            noteIndex = 0;
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
        * Description: 响应按键事件，判定note打击情况
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
            } else if (keyCode == PageController.keyCode_sus) {
                Suspend susp = new Suspend();
                suspend();
                susp.Suspendinit();
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
            // 判定note打击情况
            // perfect打击
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
            } 
            // good打击
            else if ((noteY < perfectUp && noteY >= goodUp) || (noteY > perfectDown && noteY <= goodDown)) {
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
            } 
            // miss打击
            else if ((noteY < goodUp && noteY >= missUp) || (noteY > goodDown)) {
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
