import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel {
    protected Map map;
//    int width = 500;
//    int height = 500;
    protected final int size = 700;
    protected final int step;

    public Board(Map map) {
        this.map = map;
        step = this.size / map.size;
    }

    @Override
    protected void paintComponent(Graphics g) {

        try {
            super.paintComponent(g);
        } catch (NullPointerException e) {}
        this.setSize(size,size);
        this.setBackground(Color.ORANGE);

        g.setColor(Color.RED);
        g.fillRect(map.fruit.position.x * step, map.fruit.position.y * step, step, step);
//        for(Obstacle obstacle : map.obstacleHashMap.values())
//            g.fillRect(obstacle.position.x * step, obstacle.position.y * step, step, step);
        g.setColor(Color.BLUE);
        for(SnakeBodyElem snakeBodyElem : map.snake.snakeBodyList)
            g.fillRect(snakeBodyElem.position.x * step, snakeBodyElem.position.y * step, step, step);
    }



}
