public enum Direction {
    UP, RIGHT, DOWN, LEFT;


    public Position toPosition() {
        switch (this) {
            case DOWN:
                return new Position(0,1);
            case RIGHT:
                return new Position(1,0);
            case UP:
                return new Position(0,-1);
            case LEFT:
                return new Position(-1,0);
            default:
                return null;
        }
    }
}
