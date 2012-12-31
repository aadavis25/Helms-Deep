import static java.lang.System.out;

public class SetDemo {
	 
	public static void main(String[] args){
		SetInherit inherit = new SetInherit();
		SetComposition comp = new SetComposition();
		
		out.println(inherit);
		inherit.add(1);
		out.println(inherit);
		inherit.add(1);
		out.println(inherit);
		inherit.add(2);
		out.println(inherit);
		inherit.add(3);
		out.println(inherit);
		inherit.add(3);
		out.println(inherit);
		inherit.add(4);
		out.println(inherit);
		
		out.println(comp);
		comp.add(1);
		out.println(comp);
		comp.add(1);
		out.println(comp);
		comp.add(2);
		out.println(comp);
		comp.add(3);
		out.println(comp);
		comp.add(3);
		out.println(comp);
		comp.add(4);
		out.println(comp);
	}
}
