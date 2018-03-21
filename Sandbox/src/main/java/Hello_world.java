public class Hello_world {

	public static void main(String[] args) {
		
		System.out.println("Hello, world!");
		hello("user");
		double l = 5;
		System.out.println(area(l));
		double a = 3;
		double b = 5;

		System.out.println(area(a,b));




	}


	public static void hello(String somebody) {

		System.out.println("Hello, " + somebody + "!");
	}

	public static double area(double len) {
		return len*len;
	}

	public static double area(double a, double b){
		return a*b;
	}
}