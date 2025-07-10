package java_concepts;

//It is not necessary for an inner class to be static
//Please note different objection creation of inner class on line 19

public class InnerClassEx {
	public static void main(String[] args) {
		M m = new M();
		M.A a = m.new A();
		a.print();
	}
}

class M {

	public void method() {
		System.out.println("check");
	}

	class A {
		public void print() {
			M m = new M();
			m.method();
		}
	}

}
