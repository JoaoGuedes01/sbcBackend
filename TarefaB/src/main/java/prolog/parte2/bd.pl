percursoTempo('R','cliente1',5,1).
percursoTempo('cliente1','R',5,1).
percursoTempo('R','cliente4',7,1.4).
percursoTempo('cliente4','R',7,1.4).
percursoTempo('cliente1','cliente4',5,1).
percursoTempo('cliente4','cliente1',5,1).
percursoTempo('cliente1','cliente2',5,1).
percursoTempo('cliente2','cliente1',5,1).
percursoTempo('cliente1','cliente5',5,1).
percursoTempo('cliente5','cliente1',5,1).
percursoTempo('cliente2','cliente4',2,0.4).
percursoTempo('cliente4','cliente2',2,0.4).
percursoTempo('cliente2','cliente5',2,0.4).
percursoTempo('cliente5','cliente2',2,0.4).
percursoTempo('cliente2','cliente3',3,0.6).
percursoTempo('cliente3','cliente2',3,0.6).
percursoTempo('cliente3','cliente4',4,0.8).
percursoTempo('cliente4','cliente3',4,0.8).
percursoTempo('cliente5','cliente3',5,1).
percursoTempo('cliente3','cliente5',5,1).


travelTempo(X,Y,D,L):-percursoTempo(Y,X,D,L);percursoTempo(X,Y,D,L). % true if percursoTempo or symmetrical