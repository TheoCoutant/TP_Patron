package session3.demo.fonctions.induction.mot;


public class Base implements Mot {

	private String base;

	public Base(String base) {
		if(!Mot.predicatBase.test(base))
			throw new IllegalArgumentException();
		this.base = base;
	}

	@Override
	public boolean estBasique() {
		return true;
	}

	@Override
	public boolean estConcatenation() {
		return false;
	}

	@Override
	public String base() {
		return base;
	}

	@Override
	public Mot gauche() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mot droite() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mot creerBase(String s) {
		return new Base(s);
	}

	@Override
	public Mot creerConcatenation(Mot gauche, Mot droit) {
		return new Concatenation(gauche, droit);
	}

	@Override
	public String conclusion() {
		return this.base();
	}
	@Override
	public String toString() {
		return this.conclusion();
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Mot))
			return false;
		Mot x = (Mot)obj;
		return this.estEgal(x);
	}
}
