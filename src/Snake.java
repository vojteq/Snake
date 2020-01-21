import java.util.HashMap;
import java.util.LinkedList;

public class Snake {
    HashMap<Position, SnakeBodyElem> snakeBodyHashmap;
    LinkedList<SnakeBodyElem> snakeBodyList;
    protected Direction direction;
    private Map map;

    Snake(Position position, Map map){
        snakeBodyList = new LinkedList<>();
        snakeBodyHashmap = new HashMap<>();
        SnakeBodyElem snakeBodyElem = new SnakeBodyElem(position);
        snakeBodyList.add(snakeBodyElem);
        snakeBodyHashmap.put(position, snakeBodyElem);
        direction = Direction.LEFT;
        this.map = map;
    }

    private void eat() {        //jesli zje to zamiast nastepnego ruchu dopniemy nowy element jako glowe na nastepnym
        // polu
        SnakeBodyElem newHead = new SnakeBodyElem(nextHeadPosition());
        snakeBodyHashmap.put(newHead.position, newHead);
        snakeBodyList.addFirst(newHead);
        snakeBodyHashmap.put(newHead.position, newHead);
        map.putFruit();
    }

    private Position nextHeadPosition(){
        return snakeBodyList.get(0).position.addWithModulo(this.direction.toPosition(), map.size, map.size);
    }

    protected boolean move() {          //false oznacza smierc
        Position nextPosition = nextHeadPosition();
        Object object = map.objectAt(nextPosition);
        if (!map.onMap(nextPosition))
            return false;
        if (object instanceof SnakeBodyElem && object != snakeBodyList.getLast())
            return false;
        if (object instanceof Obstacle)
            return false;
        if (object instanceof Fruit)
            eat();
        else {
            Position newHeadPosition = nextHeadPosition();
            SnakeBodyElem moved = snakeBodyList.getLast();
            snakeBodyList.removeLast();
            snakeBodyHashmap.remove(moved.position);
            moved.position = newHeadPosition;
            snakeBodyList.addFirst(moved);
            snakeBodyHashmap.put(moved.position, moved);
        }
        return true;
    }
}
