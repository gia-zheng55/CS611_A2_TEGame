public class Card {

    private final String type;
    private final String number;
    private boolean visible;

    public Card(String type, String number, boolean visible) {
        this.type = type;
        this.number = number;
        this.visible = visible;
    }

    public String getNumber(){
        return number;
    }

    public String getType(){
        return type;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean vis){
        visible = vis;
    }

    public String toString(){
        return number + "-" + type;
    }
}
