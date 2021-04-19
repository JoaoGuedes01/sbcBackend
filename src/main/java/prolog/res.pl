:- dynamic(fact/1).
:- [forward,proof,certainty,database,baseconhecimento].

escolher(X) :- assert(fact(X)).

res(Y):- fact(X), solucao(X,Y).
res(_).

resposta(P):- demo, findall(Y,res(Y),L1), list_to_set(L1,P).