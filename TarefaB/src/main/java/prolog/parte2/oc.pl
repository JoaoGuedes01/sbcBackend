% traveling salesman problem (TSP): minimize distance to visit all cities
:-[auxiliar,hill, bd].

% representation: S is a list of cities
% (first and last cities need to be the same)

% evaluation function: (sum of distances for all pairs)
eval([_],0).
eval([City1,City2|R],DS):- 
	travelTempo(City1,City2,D,L),
	eval([City2|R],DR),
	DS is 0.8 * L + 0.2*(20-D).

% change: switch 2 adjacent cities at random position:	
change(S1,S2):-
	length(S1,L),
	random_between(1,L,P1),
        change(S1,P1,L,S2).
% change travelling path, auxiliary function:
change(S1,P1,L,S2):- % general case, normal switch
	P1>2, P1<L, % normal switch
	nth1(P1,S1,City1),
	P2 is P1-1,
	nth1(P2,S1,City2),
	replace_list(S1,P1,City2,S11),
	replace_list(S11,P2,City1,S2).
change(S1,1,L,S2):- % P1 is 1
	P2 is L-1, % last non repeated city
        nth1(1,S1,City1),
	nth1(P2,S1,City2),
	replace_list(S1,1,City2,S11),
	replace_list(S11,P2,City1,S12),
	replace_list(S12,L,City2,S2). % last
change(S1,2,L,S2):- % P1 is 2
	P2 is 1, % first element
        nth1(2,S1,City1),
	nth1(P2,S1,City2),
	replace_list(S1,2,City2,S11),
	replace_list(S11,P2,City1,S12),
	replace_list(S12,L,City1,S2). % last
change(S1,L,L,S2):- % P1 is L
	P2 is L-1, % last non repeated city
        nth1(L,S1,City1),
	nth1(P2,S1,City2),
	replace_list(S1,L,City2,S11),
	replace_list(S11,P2,City1,S12),
	replace_list(S12,1,City2,S2). % last

% initial solution: lets start with a bad solution:
initial(['R','cliente4','cliente3','cliente2','cliente5','cliente1','R']).

:- set_random(seed(12345)). % set initial random seed

% S is the solution
q1:- % standard hill climbing
	initial(S0), % initial solution
	% 10000 iterations, report every 2000 iterations
	time(hill_climbing(S0,[10000,2000,0,max],S)),
	eval(S,Dist),
        write('sol:'),write(S),nl,write('0.8*lucro+0.2*(20-tempo):'),write(Dist),nl.

q2:- % stochastic hill climbing Prob=0.2 
	initial(S0), % initial solution
	% 10000 iterations, report every 2000 iterations
	time(hill_climbing(S0,[10000,2000,0.2,min],S)),
	eval(S,Dist),
        write('sol:'),write(S),nl,write('dist:'),write(Dist),nl.
