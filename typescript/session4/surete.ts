namespace variance {
    class A { }

    class B extends A {
        f(): void { }
    }

    function produireErreurParCovariance(b : Array<B>): Array<A> {
        return b[0].f();
    }

    function produireErreurParContravariance(a : Array<A>): Array<B> {
      return <Array<B>> a;
    }

    var a : Array<A> = new Array<A>(new A());
    var b : Array<B> = new Array<B>(new B());
    try {
        produireErreurParContravariance(a);
    } catch (e) {
        console.log(e);
    }
    console.log("********************************************************");
    try {
        produireErreurParCovariance(b);
    } catch (e) {
        console.log(e);
    }

}