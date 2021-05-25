:- dynamic(encomenda/5).

%percurso(Origem, Destino, Tempo)
percurso('R','cliente1',5).
percurso('R','cliente4',7).
percurso('cliente1','cliente4',5).
percurso('cliente1','cliente2',5).
percurso('cliente1','cliente5',5).
percurso('cliente2','cliente4',2).
percurso('cliente2','cliente5',2).
percurso('cliente2','cliente3',3).
percurso('cliente3','cliente4',4).
percurso('cliente5','cliente3',5).


%gasto('Destino',Gasto)
gasto('cliente1',2).
gasto('cliente2',5).
gasto('cliente3',4).
gasto('cliente4',3).
gasto('cliente5',4).



