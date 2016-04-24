
public class Simulation {
public static void main(String[] args) {
	StdDraw.setCanvasSize(720, 560);
	StdDraw.setXscale(0, 720);
	StdDraw.setYscale(0,560);
	stations();
	
}

public static void stations () {

	StdDraw.rectangle(35, 530, 30, 25);
	StdDraw.text(35, 530, "1");
	StdDraw.rectangle(680, 530, 30, 25);
	StdDraw.text(680, 530, "9");
	StdDraw.rectangle(35, 50, 30, 25);
	StdDraw.text(35, 50, "2");
	StdDraw.rectangle(680, 50, 30, 25);
	StdDraw.text(680, 50, "8");
	StdDraw.rectangle(380, 50, 30, 25);
	StdDraw.text(380, 50, "4");
	StdDraw.rectangle(380, 200, 30, 25);
	StdDraw.text(380, 200, "6");
	StdDraw.rectangle(175, 300, 30, 25);
	StdDraw.text(175, 300, "3");
	StdDraw.rectangle(500, 425, 30, 25);
	StdDraw.text(500, 425, "7");
	StdDraw.rectangle(300, 425, 30, 25);
	StdDraw.text(300, 425, "5");
	String str = "a4.png";

	StdDraw.picture(212, 530, str, 292, 45, 0);
	StdDraw.picture(504, 530, str, 292, 45, 0);
	StdDraw.picture(208, 50, str, 283, 45, 0);
	StdDraw.picture(531, 50, str, 238, 45, 0);
	StdDraw.picture(35, 388, str, 230, 45 , 90);
	StdDraw.picture(35, 190, str, 230, 45, 90);
	StdDraw.picture(680, 388, str, 230, 45 , 90);
	StdDraw.picture(680, 190, str, 230, 45, 90);
	StdDraw.picture(380, 124, str, 99,45, 90);
	StdDraw.picture(401, 425, str, 138,45, 0);
	StdDraw.picture(531, 250, str, 238, 45, 0);
}
}
