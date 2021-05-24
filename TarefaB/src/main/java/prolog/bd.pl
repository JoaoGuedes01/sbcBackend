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
percurso('R','conf',5).
percurso('conf','expro',10).
percurso('expro','sa',12).
percurso('sa','av',8).

%gasto('Destino',Gasto)
gasto('cliente1',5).
gasto('cliente2',4).
gasto('cliente3',3).
gasto('cliente4',2).
gasto('cliente5',1).
gasto('conf',5).
gasto('expro',6).
gasto('sa',7).
gasto('av',8).


