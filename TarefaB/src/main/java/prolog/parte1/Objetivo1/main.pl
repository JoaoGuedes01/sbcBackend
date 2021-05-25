:- [search].
    :- dynamic(goal/1).
    :- [bd].

    encomendar(Destino,Encomenda,Id,Preco):-

    assert(encomenda(Destino,Encomenda,'N/E',Id,Preco)).

    entregar(Destino,Encomenda,Id,Preco):-
    retract(encomenda(Destino,Encomenda,'N/E',Id,Preco)),
    assert(encomenda(Destino,Encomenda,'E',Id,Preco)).


        definirgoal(Destino):-
    assert(goal(Destino)).


    initial('R').

    % travel(Marco1,Marco2,mins):
travel(X,Y,MN):-(percurso(X,Y,MN);percurso(Y,X,MN)). % true if road or symmetrical

    % state transition rule s/2: s(Marco1,Marco2)
s(N1,N2):- travel(N1,N2,_). % link s(O,D,Dist) with s(O,D)

% evaluation function: (sum of distances for all pairs)
eval([_],0).
eval([Marco1,Marco2|R],DS):- 
	travel(Marco1,Marco2,D),
	eval([Marco2|R],DR),
	DS is D+DR.

soma(A,B):-
    B is A + 1.

% execute and show a search method result:
run(Method):- search(Method,Par,S),
	      write('method:'),write(Method),nl,writepar(Par),nl,
          write('solution:'),write(S),nl,
	      length(S,N),N1 is N-1,write('solution steps:'),write(N1),nl,
          eval(S,D),write('Time:'),soma(D,Res), write(Res),nl,
          write("Lucro:" ),last(S,Last),gasto(Last,Lucro),write(Lucro).
% write parameter (if any):
writepar(X):- integer(X),write('par:'),write(X). % write X
writepar(_). % do not write X

% execute 3 example searches:
df:- run(depthfirst).
id:- run(iterativedeepening).
bf:- run(breadthfirst).