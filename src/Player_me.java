// 继承自javazoom.jl.player.Player，实现暂停和恢复功能
import java.io.FileInputStream;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Player_me {
    private boolean paused;             // 暂停标志位
    private Integer pausePosition;         // 暂停位置
    private InputStream target;
    private Player player;

    public Player_me(InputStream stream) throws JavaLayerException {
        target = stream;
        player = new Player(target);
        paused = false;
        pausePosition = 0;
    }

    // 播放
    public void play() throws JavaLayerException {
        player.play();
        pausePosition = 0;
    }
    // 播放
    public void play(Integer frames) throws JavaLayerException {
        player.play(frames);
    }

    // 暂停
    public void pause() {
        if (!paused) {
            pausePosition = player.getPosition();
            player.close();
            paused = true;
        }
    }

    // 恢复
    public void resume() throws JavaLayerException {
        if (paused) {
            player = new Player(target);
            player.play(pausePosition);
            paused = false;
        }
    }

    // 关闭
    public void close() {
        paused = false;
        pausePosition = 0;
        player.close();
    }

}
