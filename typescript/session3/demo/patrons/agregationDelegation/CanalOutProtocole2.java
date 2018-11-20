package session3.demo.patrons.agregationDelegation;

public class CanalOutProtocole2 implements Canal {

	@Override
	public void emettre(String msg) {
		System.out.println("protocole 2 : " + msg);
	}

}
