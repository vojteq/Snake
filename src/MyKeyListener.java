import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListener implements KeyListener {
    Map map;
    MyKeyListener(Map map) {
        this.map = map;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ((key == KeyEvent.VK_LEFT) && map.snake.direction != Direction.RIGHT)
            map.snake.direction = Direction.LEFT;

        if ((key == KeyEvent.VK_RIGHT) && map.snake.direction != Direction.LEFT)
            map.snake.direction = Direction.RIGHT;

        if ((key == KeyEvent.VK_UP) && map.snake.direction != Direction.DOWN)
            map.snake.direction = Direction.UP;

        if ((key == KeyEvent.VK_DOWN) && map.snake.direction != Direction.UP)
            map.snake.direction = Direction.DOWN;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

