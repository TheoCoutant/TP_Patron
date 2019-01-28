namespace varianceTP1 {
    class A { }

    class B extends A {
        f(): void { }
    }

    function produireErreurParCovariance(): void {
       var b : Array<B> = new Array<B>();
       var a : Array<A> = b;
       a.push(new A());
       b[0].f();
        
    }

    function produireErreurParContravariance(): void {
        var a : Array<A> = new Array<A>();
        a.push(new A());
        var b : Array<B> = <Array<B>>a;
        b[0].f();
    }

    var a = new Array<A>();
    var b = new Array<B>();
    try {
        produireErreurParContravariance();
    } catch (e) {
        console.log(e);
    }
    console.log("********************************************************");
    
    try {
        produireErreurParCovariance();
    } catch (e) {
        console.log(e);
    }

}