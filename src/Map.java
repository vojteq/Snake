import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.HashMap;
import java.util.Random;

public class Map {
    protected final int size = 50;
    protected Fruit fruit;
    HashMap<Position, Obstacle> obstacleHashMap;
    Random random = new Random();
    Snake snake;
    final int amountOfObstacles;
    final int sleep;

    Map(int amountOfObstacles, int sleep) {
        obstacleHashMap = new HashMap<>();
        fruit = new Fruit(new Position(Math.abs(random.nextInt()) % size,Math.abs(random.nextInt()) % size));
        snake = new Snake(new Position(size / 2, size / 2), this);
        this.amountOfObstacles = amountOfObstacles;
        this.sleep = sleep;
    }

    public Position getRandomPosition (){
        Position randomPosition;
        do {
            randomPosition = new Position(Math.abs(random.nextInt()) % size,Math.abs(random.nextInt()) % size);
        } while (objectAt(randomPosition) != null);
        return randomPosition;
    }

    Object objectAt(Position position) {
        if (position.equals(fruit.position))
            return fruit;
        if (obstacleHashMap.get(position) instanceof Obstacle)
            return obstacleHashMap.get(position);
        if (snake.snakeBodyHashmap.get(position) instanceof SnakeBodyElem)
            return snake.snakeBodyHashmap.get(position);
        return null;
    }

    private void putObstacles(int amount) {
        Position position;
        do {
            position = getRandomPosition();
            obstacleHashMap.put(position, new Obstacle(position));
            amount--;
        } while(amount > 0);
    }
    protected boolean onMap(Position position) {
        if (position.x < 0 || position.y < 0 || position.x >= size || position.y >= size)
            return false;
        return true;
    }

    protected void putFruit() {
        fruit = new Fruit(getRandomPosition());
    }

    void start() throws InterruptedException {
        Board board = new Board(this);

        board.addKeyListener(new MyKeyListener(this));
        Graphics graphics = new Graphics() {
            @Override
            public Graphics create() {
                return null;
            }

            @Override
            public void translate(int x, int y) {

            }

            @Override
            public Color getColor() {
                return null;
            }

            @Override
            public void setColor(Color c) {

            }

            @Override
            public void setPaintMode() {

            }

            @Override
            public void setXORMode(Color c1) {

            }

            @Override
            public Font getFont() {
                return null;
            }

            @Override
            public void setFont(Font font) {

            }

            @Override
            public FontMetrics getFontMetrics(Font f) {
                return null;
            }

            @Override
            public Rectangle getClipBounds() {
                return null;
            }

            @Override
            public void clipRect(int x, int y, int width, int height) {

            }

            @Override
            public void setClip(int x, int y, int width, int height) {

            }

            @Override
            public Shape getClip() {
                return null;
            }

            @Override
            public void setClip(Shape clip) {

            }

            @Override
            public void copyArea(int x, int y, int width, int height, int dx, int dy) {

            }

            @Override
            public void drawLine(int x1, int y1, int x2, int y2) {

            }

            @Override
            public void fillRect(int x, int y, int width, int height) {

            }

            @Override
            public void clearRect(int x, int y, int width, int height) {

            }

            @Override
            public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {

            }

            @Override
            public void drawOval(int x, int y, int width, int height) {

            }

            @Override
            public void fillOval(int x, int y, int width, int height) {

            }

            @Override
            public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {

            }

            @Override
            public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {

            }

            @Override
            public void drawString(String str, int x, int y) {

            }

            @Override
            public void drawString(AttributedCharacterIterator iterator, int x, int y) {

            }

            @Override
            public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
                return false;
            }

            @Override
            public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
                return false;
            }

            @Override
            public void dispose() {

            }
        };
        boolean alive = true;
        putObstacles(amountOfObstacles);
        JFrame jFrame = new JFrame("SNAKE");
        initializeBoard(jFrame, board, graphics);

        while (alive) {
            alive = snake.move();
            board.repaint();
            Thread.sleep(this.sleep);
        }
        System.out.println("zdobyles " + snake.snakeBodyList.size() + " punktow");
    }

    void initializeBoard(JFrame jFrame, Board board, Graphics graphics) {
        jFrame.setSize(board.size+15, board.size+40);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.add(board);
        board.paintComponent(graphics);
    }
}
