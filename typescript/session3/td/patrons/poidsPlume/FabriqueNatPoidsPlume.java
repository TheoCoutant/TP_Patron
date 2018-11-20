package session3.demo.patrons.poidsPlume;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import session1.td.FabriqueNaturels;
import session1.td.Nat;
import session1.td.Zero;

public class FabriqueNatPoidsPlume implements FabriqueNaturels<Nat> {

	private Map<String, SoftReference<Nat>> table = new HashMap<>();

	private ReferenceQueue<Nat> file = new ReferenceQueue<>();

	private FabriqueNaturels<Nat> fab = new Zero();

	private void nettoyer() {
		Reference<? extends Nat> r = file.poll();
		if (r != null) {
			table.remove(r.get().toString());
		}
	}

	private Nat recherche(String repDecimale) {
		nettoyer();
		SoftReference<Nat> refN = table.get(repDecimale);
		if (refN == null) {
			return null;
		}
		if (refN.get() == null) {
			return null;
		}
		return refN.get();
	}

	private void ajouterATable(String rep, Nat n) {
		table.put(rep, new SoftReference<>(n, file));
	}

	@Override
	public Nat creerNatAvecValeur(int x) {
		return this.creerNatAvecRepresentation(Integer.toString(x));
	}

	@Override
	public Nat creerNatAvecRepresentation(String repDecimale) {
		Nat n = recherche(repDecimale);
		if (n != null) {
			return n;
		}
		n = fab.creerNatAvecRepresentation(repDecimale);
		ajouterATable(repDecimale, n);
		return n;
	}

	@Override
	public Nat creerZero() {
		return this.creerNatAvecRepresentation("0");
	}

	@Override
	public Nat creerSuccesseur(Nat predecesseur) {
		return this.creerNatAvecValeur(predecesseur.val() + 1);
	}

	public static void main(String[] args) {

		FabriqueNaturels<Nat> fab = new FabriqueNatPoidsPlume();
		testerPetitsEntiers(fab);
	}

	public static void testerPetitsEntiers(FabriqueNaturels<Nat> fab) {
		Nat zero = fab.creerZero();
		System.out.println("0 ? " + zero);
		Nat un = fab.creerSuccesseur(zero);
		System.out.println("1 ? " + un);
		Nat deux = fab.creerSuccesseur(un);
		System.out.println("2 ? " + deux);

		Nat x = fab.creerNatAvecRepresentation("75");
		System.out.println("150 ? " + x.somme(x));
		System.out.println("150 ? " + x.produit(deux));
		System.out.println("150 ? " + deux.produit(x));
		System.out.println("5625 ? " + x.produit(x));

		x = fab.creerNatAvecValeur(85);
		System.out.println("170 ? " + x.somme(x));

		Nat y = fab.creerNatAvecRepresentation("45");
		System.out.println("130 ? " + x.somme(y));

		y = fab.creerNatAvecRepresentation("55");
		System.out.println("140 ? " + y.somme(x));

	}

}
