package content;

public class Pages<Left, Right> {
    private final Left left;
    private final Right right;

    public Pages(Left left, Right right){
        this.left = left;
        this.right = right;
    }

    public Left getLeft(){
        return left;
    }

    public Right getRight(){
        return right;
    }

    

}
