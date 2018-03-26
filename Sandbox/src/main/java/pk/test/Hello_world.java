package pk.test;

public class Hello_world {

	public static void main(String[] args) {
		
		System.out.println("Hello, world!");
		hello("user");

		pk.test.Square s = new pk.test.Square(6);
		System.out.println(s.area());

		pk.test.Rectangle r = new pk.test.Rectangle(4,5);
		System.out.println(r.area());

		pk.test.Point p1 = new pk.test.Point(0,0);
		pk.test.Point p2 = new pk.test.Point(3,4);
		System.out.println(p1.distance(p1,p2));





	}


	public static void hello(String somebody) {

		System.out.println("Hello, " + somebody + "!");
	}




}