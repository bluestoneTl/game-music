
public class Note {
    //音符类
//图片资源
    static ImageIcon noteIcon = new ImageIcon("src/image/note.png");
   //定义横坐标
    public int x;
    //定义纵坐标
    public int y；
    //Title: Note构造器
    *Description: 设置图片、尺存
    public Note() {
        this.setIcon(noteIcon);
        this.setSize(120, 18);
        this.setVisible(false);
    }

}
}
