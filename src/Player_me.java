// 继承自javazoom.jl.player.Player，实现暂停和恢复功能
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Player_me extends Player {
    private boolean paused;             // 暂停标志位
    private long pausePosition;         // 暂停位置

    public Player_me(InputStream stream) throws JavaLayerException {
        super(stream);
        paused = false;
        pausePosition = 0;
    }

    // 暂停
    public void pause() {
        if (isPlaying()) {
            pausePosition = getPosition();
            close();
            paused = true;
        }
    }

    // 恢复
    public void resume() throws JavaLayerException {
        if (paused) {
            InputStream inputStream = new ByteArrayInputStream(super.getAudio().getBuffer());
            super.open(inputStream);
            super.play(pausePosition, Integer.MAX_VALUE);
            paused = false;
        }
    }

    // 刷新所有状态，从头开始播放
    public void restart() throws JavaLayerException {
        stop();
        InputStream inputStream = new ByteArrayInputStream(super.getAudio().getBuffer());
        super.open(inputStream);
        super.play();
        paused = false;
        pausePosition = 0;
    }
}
