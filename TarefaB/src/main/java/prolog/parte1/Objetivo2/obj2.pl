:-[search, bd].
:- dynamic goal/3.
% -- database:
%   state representation: S, where S is a list with the full path followed by the person 
initial(['R']). % initial city

goal(X):- dest1(D1),member(D1,X), dest2(D2), member(D2,X). % destination city

    encomendar(Destino,Encomenda,Id,Preco):-

    assert(encomenda(Destino,Encomenda,'N/E',Id,Preco)).

	entregar(Destino,Encomenda,Id,Preco):-
    retract(encomenda(Destino,Encomenda,'N/E',Id,Preco)),
    assert(encomenda(Destino,Encomenda,'E',Id,Preco)).


% travel(City1,City2,distance):
travel(X,Y,KM):-(percurso(X,Y,KM);percurso(Y,X,KM)). % true if road or symmetrical

% state transition rule s/2: s(City1,City2)
s(L1,L2):- last(L1,N1),travel(N1,N2,_),append(L1,[N2],L2). % link s(O,D,Dist) with s(O,D)

% evaluation function: (sum of distances for all pairs)
eval([_],0).
eval([City1,City2|R],DS):- 
	travel(City1,City2,D),
	eval([City2|R],DR),
	DS is D+DR.

% execute and show a search method result:
run(Method):- search(Method,Par,S),
	      write('method:'),write(Method),writepar(Par),nl,
              write('solution:'),write(S),nl,
	      length(S,N),N1 is N-1,write('solution steps:'),write(N1),nl,
              last(S,LS),
              eval(LS,D),write('distance:'),write(D).
% write parameter (if any):
writepar(X):- integer(X),write(' par:'),write(X). % write X
writepar(_). % do not write X

% execute 3 example searches:
q1:- run(depthfirst).
q2:- run(iterativedeepening).
q3:- run(breadthfirst).
