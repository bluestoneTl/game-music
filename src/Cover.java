import javax.swing.*;


@SuppressWarnings("serial")
public class Cover extends JLabel{
    /* 封面图片对象 */
    ImageIcon coverPic;
    /* 音频文件路径 */
    String audioFilePath;
    /* 对应文本文件路径 */
    String scoreFilePath;
    /* 构造函数 */
    @SuppressWarnings("serial")
    public Cover(String name) {
        coverPic = new ImageIcon("src/resource/cover/" + name + ".png");
        audioFilePath = "src/mp3/" + name + ".mp3";
        scoreFilePath = "src/txt/" + name + ".txt";
        this.setIcon(coverPic);
        this.setSize(150, 150);
        this.setVisible(false);
    }
}
