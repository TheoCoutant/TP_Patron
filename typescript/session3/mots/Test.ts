import { Mot } from './Mot'
import { MotVideRecursif } from './MotRecursif';
import { assembleurString } from '../../bibliotheque/assembleurChaine'
import { MotCaractereIteratif, MotVideIteratif } from './MotIteratif';
import { CharAt, Count, Length } from './Visiteurs';

var nbIteration = 10000;

var motA = MotVideRecursif.SINGLETON.creerMotPrecedeCaractereRecursif('A');
var motB = MotVideRecursif.SINGLETON.creerMotPrecedeCaractereRecursif('B');
var chaine = MotVideIteratif.SINGLETON;

testRecursifLong(nbIteration, motA);
testIteratifLong(nbIteration, motA);

chaine = chaine.creerMotConcatenationIteratif(motA);
chaine = chaine.creerMotConcatenationIteratif(motB);
chaine = chaine.creerMotConcatenationIteratif(motB);
chaine = chaine.creerMotConcatenationIteratif(motA);

var charAt = chaine.acceptRecursif(new CharAt(2));
console.log("(REC) ABBA.charAt(2): B ? " + charAt);
charAt = chaine.accept(new CharAt(2));
console.log("(ITE) ABBA.charAt(2): B ? " + charAt);

var lenght = chaine.acceptRecursif(new Length());
console.log("(REC) ABBA.lenght : 4 ? " + lenght);
lenght = chaine.accept(new Length());
console.log("(ITE) ABBA.lenght : 4 ? " + lenght);

var count = chaine.acceptRecursif(new Count('A'));
console.log("(REC) ABBA.count : 2 ? " + count);
count = chaine.accept(new Count('A'));
console.log("(ITE) ABBA.count : 2 ? " + count);

function testIteratifLong(nbIteration: number, motA: Mot) {
    var chaine = MotVideRecursif.SINGLETON;
    var strBuilder = assembleurString("");

     for(let i = 0; i < nbIteration; i++){
         chaine = chaine.creerMotConcatenationIteratif(motA);
     }

     while(!chaine.estVide()){
         try {
             strBuilder.ajouterCaractereDebut(chaine.caractere());
             chaine = chaine.reste();
         } catch (e) {
             console.log("Erreur lors de la recursivite longue");
             break;
         }
     }
     if(strBuilder.chaine().length == nbIteration)
         console.log("Succes iterativite longue");
}

function testRecursifLong(nbIteration: number, motA: Mot) {
    var chaine = MotVideRecursif.SINGLETON;
    var strBuilder = assembleurString("");

     for(let i = 0; i < nbIteration; i++){
         chaine = chaine.creerMotConcatenationRecursif(motA);
     }

     while(!chaine.estVide()){
         try {
             strBuilder.ajouterCaractereDebut(chaine.caractere());
             chaine = chaine.reste();
         } catch (e) {
             console.log("Erreur lors de la recursivite longue");
             break;
         }
     }
     if(strBuilder.chaine().length == nbIteration)
         console.log("Succes recursivite longue");
}