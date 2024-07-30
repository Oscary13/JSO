package pruebascerti;
class Super {
    private int a;
    protected Super(int a) {
        this.a = a;
    }
    public static void main(String[] args) {   
        Sub sub = new Sub();
    }
}
class Sub extends Super{
    public Sub (int a){
        super(a);
    }
    //Esta parte genera el error 
//    public Sub(){
//        this.a = 5;
//    }
    
    
    //Respuesta 1
//    public Sub(){
//        this(5);
//    }
    
    
    //Respuesta 2
    public Sub(){
        super(5);
    }
    
}