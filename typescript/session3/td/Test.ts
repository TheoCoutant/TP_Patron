import { LastOccurence } from './LastOccurence';
import { Mot } from './Mot';
import { Vide } from './Vide'
import { assembleurString } from '../../bibliotheque/assembleurChaine';
import { CharAt } from './CharAt';
import { Count } from './Count';

var nbIteration = 10000;
var motA = Vide.SINGLETON.consRec('A');
var motB = Vide.SINGLETON.consRec('B');
testRecursif(motA, nbIteration);
testIteratif(motA, nbIteration);
var motUnion = createMotUnion(motA, motB); //Mot ABABB
console.log("Filtrage Recursif > chaine.representation() : ABABB ? " + motUnion.representation());
testVisiteurCharAt(motUnion);
testVisiteurLastOccurence(motUnion);
testVisiteurCount(motUnion);

function testRecursif(motA : Mot, nbIteration : number) {
    var mot = Vide.SINGLETON;
    var stringBuilder = assembleurString("");

    for (let i = 0; i < nbIteration; i++) {
        mot = mot.unionRec(motA);
    }
    
    while(!mot.estVide()) {
        try {
            stringBuilder.ajouterCaractereDebut(mot.lettre());
            mot = mot.reste();
        } catch (e) {
            console.log("Erreur lors de la recursivite");
            break;
        }
    }

    if (stringBuilder.chaine().length == nbIteration) {
        console.log("Test recursif : valide");
    } else {
        throw Error("Test recursif : fail");
    }
}

function testIteratif(motA : Mot, nbIteration : number) {
    var mot = Vide.SINGLETON;
    var stringBuilder = assembleurString("");

    for (let i = 0; i < nbIteration; i++) {
        mot = mot.unionIter(motA);
    }
    
    while(!mot.estVide()) {
        try {
            stringBuilder.ajouterCaractereDebut(mot.lettre());
            mot = mot.reste();
        } catch (e) {
            console.log("Erreur lors de la recursivite");
            break;
        }
    }

    if (stringBuilder.chaine().length == nbIteration) {
        console.log("Test iteratif : valide");
    } else {
        throw Error("Test iteratif : fail");
    }
}

function createMotUnion(motA : Mot, motB : Mot) {
    var motUnion = motA.unionIter(motB);
    motUnion = motUnion.unionIter(motA);
    motUnion = motUnion.unionIter(motB);
    motUnion = motUnion.unionIter(motB);
    return motUnion;
}

function testVisiteurCharAt(mot : Mot) {
    var charAt = mot.acceptRecursif(new CharAt(3));
    console.log("Recurisif > ABABB.charAt(3): B ? " + charAt);
    charAt = mot.accept(new CharAt(3));
    console.log("Iteratif > ABABB.charAt(3): B ? " + charAt);
}

function testVisiteurLastOccurence(mot : Mot) {
    var lastOccurence = mot.acceptRecursif(new LastOccurence("B"));
    console.log("Recursif (Chaine char inversée) > BBABA.lastOccurence('B') : 3 ? " + lastOccurence);
    lastOccurence = mot.accept(new LastOccurence("B"));
    console.log("Iteratif > ABABB.lastOccurence('B') : 4 ? " + lastOccurence);
}

function testVisiteurCount(mot : Mot) {
    var count = mot.acceptRecursif(new Count("B"));
    console.log("Recursif > ABABB.count('B') : 3 ? " + count);
    count = mot.accept(new Count("B"));
    console.log("Iteratif > ABABB.count('B') : 3 ? " + count);
}