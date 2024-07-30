/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebascerti;

/**
 *
 * @author Alien 2
 */
class Base {
    public static  final String FOO = "foo";
    public static void main(String[] args) {
        Base b = new Base();
        Sub s  = new Sub();
        
        System.out.println(Base.FOO);
        System.out.println(Sub.FOO);
        System.out.println(b.FOO);
        System.out.println(s.FOO);
        System.out.println(((Base)s).FOO);
        System.out.println("-------------------------");
        System.out.print(Base.FOO);
        System.out.print(Sub.FOO);
        System.out.print(b.FOO);
        System.out.print(s.FOO);
        System.out.print(((Base)s).FOO);
    }
}

class Sub extends Base{
    public static  final String FOO = "bar";
}
