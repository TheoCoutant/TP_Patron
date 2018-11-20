package session3.demo.fonctions.induction.mot;


public class Concatenation implements Mot{

	private Mot gauche;
	private Mot droit;
	
	public Concatenation(Mot gauche, Mot droit) {
		this.gauche = gauche;
		this.droit = droit;
	}

	@Override
	public boolean estBasique() {
		return false;
	}

	@Override
	public boolean estConcatenation() {
		return true;
	}

	@Override
	public String base() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Mot gauche() {
		return this.gauche;
	}

	@Override
	public Mot droite() {
		return this.droit;
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
		return gauche().conclusion() + droite().conclusion();
	}
	@Override
	public String toString() {
		return "(" + this.gauche() + "." + this.droite() + ")";
	}
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Mot))
			return false;
		Mot x = (Mot)obj;
		return this.estEgal(x);
	}

}
