% Figure 15.9  An interpreter for rules with certainties.
:-  op( 800, fx, if).
:-  op( 700, xfx, then).
:-  op( 300, xfy, or).
:-  op( 200, xfy, and).

% Rule interpreter with certainties

% democ( Proposition, Certainty)

democ( P, Cert)  :-
   fact( P: Cert).

democ( Cond1 and Cond2, Cert)  :-
   democ( Cond1, Cert1),
   democ( Cond2, Cert2),
   Cert is min( Cert1, Cert2).

democ( Cond1 or Cond2, Cert)  :-
   democ( Cond1, Cert1),
   democ( Cond2, Cert2),
   Cert is max( Cert1, Cert2).

democ( P, Cert)  :-
   if Cond then P : C1,
   democ( Cond, C2),
   Cert is C1 * C2.
