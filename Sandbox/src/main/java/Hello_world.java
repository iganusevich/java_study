public class Hello_world {

	public static void main(String[] args) {
		
		System.out.println("Hello, world!");
		hello("user");

		Square s = new Square(6);
		System.out.println(s.area());

		Rectangle r = new Rectangle(4,5);
		System.out.println(r.area());

		Point p1 = new Point(0,0);
		Point p2 = new Point(3,4);
		System.out.println(p1.distance(p1,p2));





	}


	public static void hello(String somebody) {

		System.out.println("Hello, " + somebody + "!");
	}




}