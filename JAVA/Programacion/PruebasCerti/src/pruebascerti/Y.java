package pruebascerti;
class X {

    X() {
    }
    //Este es el error
//    private void one() {
//    }
    
    //Respuesta
    protected void one() {
    }
}

public class Y extends X {

    public Y() {
    }

    private void two() {
        one();
    }

    public static void main(String[] args) {
        new Y().two();
    }

}
