package session3.demo.patrons.agregationDelegation;

public class CanalOutProtocole1 implements Canal {

	@Override
	public void emettre(String msg) {
		System.out.println("protocole 1 : " + msg);
	}

}
