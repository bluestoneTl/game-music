import javax.swing.*;
public class Note2 extends JLabel {
    //音符类
//图片资源
    static ImageIcon noteIcon = new ImageIcon("src/resource/image/note.png");
    //定义横坐标
    public int x;
    //定义纵坐标
    public int y;
    //构造函数
    public Note2() {
        this.setIcon(noteIcon);
        this.setSize(120, 18);
        this.setVisible(false);
    }

}