public abstract class Shapes {
    private String matchingDirection;
    private String shape;
    private int score;
    private boolean math;

    
    public boolean isMath() {
        return math;
    }
    public void setMath(boolean math) {
        this.math = math;
    }
    public String getMatchingDirection() {
        return matchingDirection;
    }
    public void setMatchingDirection(String matchingDirection) {
        this.matchingDirection = matchingDirection;
    }
    public String getShape() {
        return shape;
    }
    public void setShape(String shape) {
        this.shape = shape;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
}

class D extends Shapes{

    D(String shape){
        super.setShape(shape);
        super.setScore(30);
        super.setMatchingDirection("Diagonal");
        super.setMath(false);
    }
}

class T extends Shapes{
    T(String shape){
        super.setShape(shape);
        super.setScore(15);
        super.setMatchingDirection("Vertical");
        super.setMath(false);
    }
}

class S extends Shapes{
    S(String shape){
        super.setShape(shape);
        super.setScore(15);
        super.setMatchingDirection("Horizontal");
        super.setMath(false);
    }
}


class W extends Shapes{
    W(String shape){
        super.setShape(shape);
        super.setScore(10);
        super.setMatchingDirection("Anywhere");
        super.setMath(true);
    }
}


class Slash extends Shapes{
    Slash(String shape){
        super.setShape(shape);
        super.setScore(20);
        super.setMatchingDirection("Right Diagonal");
        super.setMath(true);

    }
}


class Plus extends Shapes{
    Plus(String shape){
        super.setShape(shape);
        super.setScore(20);
        super.setMatchingDirection("Horizontal and Vertical");
        super.setMath(true);
    }
}

class Minus extends Shapes{
    Minus(String shape){
        super.setShape(shape);
        super.setScore(20);
        super.setMatchingDirection("Horizontal");
        super.setMath(true);
        }
}


class ReverseSlash extends Shapes{
    ReverseSlash(String shape){
        super.setShape(shape);
        super.setScore(20);
        super.setMatchingDirection("Left Diagonal");
        super.setMath(true);
    }
}

class Line extends Shapes{
    Line(String shape){
        super.setShape(shape);
        super.setScore(20);
        super.setMatchingDirection("Vertical");
        super.setMath(true);
    }
}

// New Shapes can be added with four attributes.

