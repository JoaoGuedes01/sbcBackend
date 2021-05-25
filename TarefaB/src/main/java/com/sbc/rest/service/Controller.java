package com.sbc.rest.service;


import org.springframework.web.bind.annotation.*;
import org.jpl7.*;


import java.lang.Integer;
import java.util.*;


@RestController
public class Controller {
    private int counter = 0;

    @GetMapping("/")
    public String landingPage(){
        return "Landing Page";
    }


    //Parte 1
    //Objetivo 1
    @GetMapping("/encomendas")
    public ArrayList<Map<String, String>> getEncomendas(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");

        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query query = new Query("encomenda(Destino, Encomenda, Estado, Id,Preco).");
        Map<String, Term>[] solutions = query.allSolutions();
        ArrayList<Map<String, String>> ArrayEncomendas = new ArrayList<Map<String, String>>();
        for(int i = 0; i<solutions.length;i++){
            System.out.println(solutions[i].get("Destino"));
            HashMap<String, String> json = new HashMap<>();
            json.put("Id",solutions[i].get("Id").toString());
            json.put("Destino",solutions[i].get("Destino").toString());
            json.put("Encomenda",solutions[i].get("Encomenda").toString());
            json.put("Preco",solutions[i].get("Preco").toString());
            json.put("Estado",solutions[i].get("Estado").toString());
            ArrayEncomendas.add(json);
        }
        return ArrayEncomendas;
    }

    @GetMapping("/postEncomendas")
    public String postEncomendas(@RequestParam String Destino, String Encomenda, int Preco){
        counter = counter + 1;

        /*if(!Destino.equals("cliente1") && !Destino.equals("cliente2") && !Destino.equals("cliente3") && !Destino.equals("cliente4") && !Destino.equals("cliente5")){
            return "Nao suportamos entregas no destino requisitado";
        }*/
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query query = new Query("encomendar('"+Destino+"','"+Encomenda+"','"+counter+"',"+Preco+").");
        System.out.println(query.hasSolution());
       // HashMap<String, String> json = new HashMap<>();
       // json.put("Destino",Destino.toString());
       // json.put("Encomenda",Encomenda.toString());
        return "ok";
    }

    @GetMapping("/entregarEncomenda")
    public String entregarEncomenda(@RequestParam String Id){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getencomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println(getencomenda.hasSolution());
        Map<String, Term> encomenda = getencomenda.oneSolution();
        Query entregar = new Query("entregar('"+encomenda.get("Destino")+"','"+encomenda.get("Item")+"','"+Id+"',"+encomenda.get("Preco")+").");
        System.out.println(entregar.hasSolution());
        // HashMap<String, String> json = new HashMap<>();
        // json.put("Destino",Destino.toString());
        // json.put("Encomenda",Encomenda.toString());
        return "ok";
    }

    @GetMapping("/getItenerarioBF")
    public HashMap<String, String> getItenerario(@RequestParam String Id){
        System.out.println("---------------------------------------------------------------------------------------------------");
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getEncomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda.hasSolution());
        Map<String, Term> encomenda = getEncomenda.oneSolution();
        System.out.println("Encomenda -> " + encomenda);
        if(encomenda == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        System.out.println("Hello");
        Query definirGoal = new Query("definirgoal('"+encomenda.get("Destino").toString()+"').");
        System.out.println("definir goal ->" + definirGoal.hasSolution());
        Query search = new Query("search(breadthfirst,Par,S).");
        System.out.println("search ->" + search.hasSolution());
        Map<String, Term> searchSolution = search.oneSolution();
        Query getTime = new Query("eval("+searchSolution.get("S")+",T).");
        System.out.println("get time -> " + getTime.hasSolution());
        Map<String, Term> timeSolution = getTime.oneSolution();
        Query getGasto = new Query("gasto("+encomenda.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto.hasSolution());
        Map<String, Term> gastoSolution = getGasto.oneSolution();
        int lucro = Integer.parseInt(encomenda.get("Preco").toString()) - Integer.parseInt(gastoSolution.get("G").toString());
        Query retractFacts = new Query("retractall(goal(X)).");
        System.out.println("retract facts -> " + retractFacts.hasSolution());

        HashMap<String, String> json = new HashMap<>();
        json.put("Item",encomenda.get("Item").toString());
        json.put("Destino",encomenda.get("Destino").toString());
        json.put("Solucao",searchSolution.get("S").toString());
        json.put("Time",timeSolution.get("T").toString());
        json.put("Preco",encomenda.get("Preco").toString());
        json.put("Gasto",getGasto.oneSolution().get("G").toString());
        json.put("Lucro",Integer.toString(lucro));
        return json;
    }

    @GetMapping("/getItenerarioDF")
    public HashMap<String, String> getItenerarioDF(@RequestParam String Id){
        System.out.println("---------------------------------------------------------------------------------------------------");
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getEncomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda.hasSolution());
        Map<String, Term> encomenda = getEncomenda.oneSolution();
        System.out.println("Encomenda -> " + encomenda);
        if(encomenda == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        System.out.println("Hello");
        Query definirGoal = new Query("definirgoal('"+encomenda.get("Destino").toString()+"').");
        System.out.println("definir goal ->" + definirGoal.hasSolution());
        Query search = new Query("search(depthfirst,Par,S).");
        System.out.println("search ->" + search.hasSolution());
        Map<String, Term> searchSolution = search.oneSolution();
        Query getTime = new Query("eval("+searchSolution.get("S")+",T).");
        System.out.println("get time -> " + getTime.hasSolution());
        Map<String, Term> timeSolution = getTime.oneSolution();
        Query getGasto = new Query("gasto("+encomenda.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto.hasSolution());
        Map<String, Term> gastoSolution = getGasto.oneSolution();
        int lucro = Integer.parseInt(encomenda.get("Preco").toString()) - Integer.parseInt(gastoSolution.get("G").toString());
        Query retractFacts = new Query("retractall(goal(X)).");
        System.out.println("retract facts -> " + retractFacts.hasSolution());

        HashMap<String, String> json = new HashMap<>();
        json.put("Item",encomenda.get("Item").toString());
        json.put("Destino",encomenda.get("Destino").toString());
        json.put("Solucao",searchSolution.get("S").toString());
        json.put("Time",timeSolution.get("T").toString());
        json.put("Preco",encomenda.get("Preco").toString());
        json.put("Gasto",getGasto.oneSolution().get("G").toString());
        json.put("Lucro",Integer.toString(lucro));
        return json;
    }

    @GetMapping("/getItenerarioID")
    public HashMap<String, String> getItenerarioID(@RequestParam String Id){
        System.out.println("---------------------------------------------------------------------------------------------------");
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo1/main.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getEncomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda.hasSolution());
        Map<String, Term> encomenda = getEncomenda.oneSolution();
        System.out.println("Encomenda -> " + encomenda);
        if(encomenda == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        System.out.println("Hello");
        Query definirGoal = new Query("definirgoal('"+encomenda.get("Destino").toString()+"').");
        System.out.println("definir goal ->" + definirGoal.hasSolution());
        Query search = new Query("search(iterativedeepening,Par,S).");
        System.out.println("search ->" + search.hasSolution());
        Map<String, Term> searchSolution = search.oneSolution();
        Query getTime = new Query("eval("+searchSolution.get("S")+",T).");
        System.out.println("get time -> " + getTime.hasSolution());
        Map<String, Term> timeSolution = getTime.oneSolution();
        Query getGasto = new Query("gasto("+encomenda.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto.hasSolution());
        Map<String, Term> gastoSolution = getGasto.oneSolution();
        int lucro = Integer.parseInt(encomenda.get("Preco").toString()) - Integer.parseInt(gastoSolution.get("G").toString());
        Query retractFacts = new Query("retractall(goal(X)).");
        System.out.println("retract facts -> " + retractFacts.hasSolution());

        HashMap<String, String> json = new HashMap<>();
        json.put("Item",encomenda.get("Item").toString());
        json.put("Destino",encomenda.get("Destino").toString());
        json.put("Solucao",searchSolution.get("S").toString());
        json.put("Time",timeSolution.get("T").toString());
        json.put("Preco",encomenda.get("Preco").toString());
        json.put("Gasto",getGasto.oneSolution().get("G").toString());
        json.put("Lucro",Integer.toString(lucro));
        return json;
    }

    //Objetivo 2

    @GetMapping("/encomendasO2")
    public ArrayList<Map<String, String>> getEncomendasO2(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo2/obj2.pl').");

        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query query = new Query("encomenda(Destino, Encomenda, Estado, Id,Preco).");
        Map<String, Term>[] solutions = query.allSolutions();
        ArrayList<Map<String, String>> ArrayEncomendas = new ArrayList<Map<String, String>>();
        for(int i = 0; i<solutions.length;i++){
            System.out.println(solutions[i].get("Destino"));
            HashMap<String, String> json = new HashMap<>();
            json.put("Id",solutions[i].get("Id").toString());
            json.put("Destino",solutions[i].get("Destino").toString());
            json.put("Encomenda",solutions[i].get("Encomenda").toString());
            json.put("Preco",solutions[i].get("Preco").toString());
            json.put("Estado",solutions[i].get("Estado").toString());
            ArrayEncomendas.add(json);
        }
        return ArrayEncomendas;
    }

    @GetMapping("/postEncomendasO2")
    public String postEncomendasO2(@RequestParam String Destino, String Encomenda, int Preco){
        counter = counter + 1;

        /*if(!Destino.equals("cliente1") && !Destino.equals("cliente2") && !Destino.equals("cliente3") && !Destino.equals("cliente4") && !Destino.equals("cliente5")){
            return "Nao suportamos entregas no destino requisitado";
        }*/
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo2/obj2.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query query = new Query("encomendar('"+Destino+"','"+Encomenda+"','"+counter+"',"+Preco+").");
        System.out.println(query.hasSolution());
        // HashMap<String, String> json = new HashMap<>();
        // json.put("Destino",Destino.toString());
        // json.put("Encomenda",Encomenda.toString());
        return "ok";
    }

    @GetMapping("/getItenerarioBFO2")
    public HashMap<String, String> getItenerarioBFO2(@RequestParam String Id, String Id2){
        System.out.println("---------------------------------------------------------------------------------------------------");
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo2/obj2.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getEncomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda.hasSolution());
        Map<String, Term> encomenda = getEncomenda.oneSolution();
        System.out.println("Encomenda -> " + encomenda);
        if(encomenda == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        Query getEncomenda2 = new Query("encomenda(Destino,Item,Estado,'"+Id2+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda2.hasSolution());
        Map<String, Term> encomenda2 = getEncomenda2.oneSolution();
        System.out.println("Encomenda -> " + encomenda2);
        if(encomenda2 == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        System.out.println("Hello");
        Query definirGoal = new Query("assert(dest1('"+encomenda.get("Destino")+"')),assert(dest2('"+encomenda2.get("Destino")+"')).");
        System.out.println("definir goal ->" + definirGoal.hasSolution());
        Query search = new Query("search(breadthfirst,Par,S).");
        System.out.println("search ->" + search.hasSolution());
        Map<String, Term> searchSolution = search.oneSolution();
        Query last = new Query("last("+searchSolution.get("S")+",LS).");
        System.out.println("Last ->" + last.hasSolution());
        Map<String, Term> lastSolution = last.oneSolution();
        Query getTime = new Query("eval("+lastSolution.get("LS")+",T).");
        System.out.println("get time -> " + getTime.hasSolution());
        Map<String, Term> timeSolution = getTime.oneSolution();
        Query getGasto = new Query("gasto("+encomenda.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto.hasSolution());
        Map<String, Term> gastoSolution = getGasto.oneSolution();
        Query getGasto2 = new Query("gasto("+encomenda2.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto2.hasSolution());
        Map<String, Term> gastoSolution2 = getGasto2.oneSolution();
        int lucro = Integer.parseInt(encomenda.get("Preco").toString()) + Integer.parseInt(encomenda2.get("Preco").toString()) - Integer.parseInt(gastoSolution.get("G").toString()) - Integer.parseInt(gastoSolution2.get("G").toString());
        Query retractFacts = new Query("retractall(dest1(X)), retractall(dest2(X)).");
        System.out.println("retract facts -> " + retractFacts.hasSolution());

        HashMap<String, String> json = new HashMap<>();
        json.put("Item",encomenda.get("Item").toString());
        json.put("Item2",encomenda2.get("Item").toString());
        json.put("Destino",encomenda.get("Destino").toString());
        json.put("Destino2",encomenda2.get("Destino").toString());
        json.put("Solucao",searchSolution.get("S").toString());
        json.put("Time",timeSolution.get("T").toString());
        json.put("Preco1",encomenda.get("Preco").toString());
        json.put("Preco2",encomenda2.get("Preco").toString());
        json.put("Gasto1",getGasto.oneSolution().get("G").toString());
        json.put("Gasto2",getGasto2.oneSolution().get("G").toString());
        json.put("Lucro",Integer.toString(lucro));
        return json;
    }

    @GetMapping("/getItenerarioIDO2")
    public HashMap<String, String> getItenerarioIDO2(@RequestParam String Id, String Id2){
        System.out.println("---------------------------------------------------------------------------------------------------");
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte1/Objetivo2/obj2.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query getEncomenda = new Query("encomenda(Destino,Item,Estado,'"+Id+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda.hasSolution());
        Map<String, Term> encomenda = getEncomenda.oneSolution();
        System.out.println("Encomenda -> " + encomenda);
        if(encomenda == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        Query getEncomenda2 = new Query("encomenda(Destino,Item,Estado,'"+Id2+"',Preco).");
        System.out.println("definir goal ->" + getEncomenda2.hasSolution());
        Map<String, Term> encomenda2 = getEncomenda2.oneSolution();
        System.out.println("Encomenda -> " + encomenda2);
        if(encomenda2 == null) {
            HashMap<String, String> json = new HashMap<>();
            json.put("res", "Nao ha encomendas com esse ID");
            return json;
        }
        System.out.println("Hello");
        Query definirGoal = new Query("assert(dest1('"+encomenda.get("Destino")+"')),assert(dest2('"+encomenda2.get("Destino")+"')).");
        System.out.println("definir goal ->" + definirGoal.hasSolution());
        Query search = new Query("search(iterativedeepening,Par,S).");
        System.out.println("search ->" + search.hasSolution());
        Map<String, Term> searchSolution = search.oneSolution();
        Query last = new Query("last("+searchSolution.get("S")+",LS).");
        System.out.println("Last ->" + last.hasSolution());
        Map<String, Term> lastSolution = last.oneSolution();
        Query getTime = new Query("eval("+lastSolution.get("LS")+",T).");
        System.out.println("get time -> " + getTime.hasSolution());
        Map<String, Term> timeSolution = getTime.oneSolution();
        Query getGasto = new Query("gasto("+encomenda.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto.hasSolution());
        Map<String, Term> gastoSolution = getGasto.oneSolution();
        Query getGasto2 = new Query("gasto("+encomenda2.get("Destino")+",G).");
        System.out.println("get gasto -> " + getGasto2.hasSolution());
        Map<String, Term> gastoSolution2 = getGasto2.oneSolution();
        int lucro = Integer.parseInt(encomenda.get("Preco").toString()) + Integer.parseInt(encomenda2.get("Preco").toString()) - Integer.parseInt(gastoSolution.get("G").toString()) - Integer.parseInt(gastoSolution2.get("G").toString());
        Query retractFacts = new Query("retractall(dest1(X)), retractall(dest2(X)).");
        System.out.println("retract facts -> " + retractFacts.hasSolution());

        HashMap<String, String> json = new HashMap<>();
        json.put("Item",encomenda.get("Item").toString());
        json.put("Item2",encomenda2.get("Item").toString());
        json.put("Destino",encomenda.get("Destino").toString());
        json.put("Destino2",encomenda2.get("Destino").toString());
        json.put("Solucao",searchSolution.get("S").toString());
        json.put("Time",timeSolution.get("T").toString());
        json.put("Preco1",encomenda.get("Preco").toString());
        json.put("Preco2",encomenda2.get("Preco").toString());
        json.put("Gasto1",getGasto.oneSolution().get("G").toString());
        json.put("Gasto2",getGasto2.oneSolution().get("G").toString());
        json.put("Lucro",Integer.toString(lucro));
        return json;
    }

    //Misc
    @GetMapping("/restart")
    public String restartApp(){
        Application.restart();
        return "ok";
    }

    //Parte 2
    @GetMapping("/ObjetivoAHC")
    public HashMap<String, String> objetivoAHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/oa.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("HillCLimbing",hcSol);
        json.put("Lucro",evalSol);
        return json;
    }

    @GetMapping("/ObjetivoASHC")
    public HashMap<String, String> objetivoASHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/oa.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0.2,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("StochasticHillCLimbing",hcSol);
        json.put("Lucro",evalSol);
        return json;
    }

    @GetMapping("/ObjetivoBHC")
    public HashMap<String, String> objetivoBHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/ob.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("HillCLimbing",hcSol);
        json.put("Tempo",evalSol);
        return json;
    }

    @GetMapping("/ObjetivoBSHC")
    public HashMap<String, String> objetivoBSHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/ob.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0.2,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("StochasticHillCLimbing",hcSol);
        json.put("Lucro",evalSol);
        return json;
    }

    @GetMapping("/ObjetivoCHC")
    public HashMap<String, String> objetivoCHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/oc.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("HillCLimbing",hcSol);
        json.put("0.8*lucro+0.2*(20-tempo)",evalSol);
        return json;
    }

    @GetMapping("/ObjetivoCSHC")
    public HashMap<String, String> objetivoCSHC(){
        String currentPath = System.getProperty("user.dir");
        currentPath = currentPath.replace("\\", "/");
        Query consult = new Query("consult('" + currentPath + "/src/main/java/prolog/parte2/oc.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        Query initial = new Query("initial(S0)");
        System.out.println(initial.hasSolution());
        String initialSol = initial.oneSolution().get("S0").toString();
        Query hcQuery = new Query("time(hill_climbing("+initialSol+",[10000,2000,0.2,min],S)).");
        System.out.println(hcQuery.hasSolution());
        String hcSol = hcQuery.oneSolution().get("S").toString();
        Query evalQuery = new Query("eval("+hcSol+",Dist).");
        System.out.println(evalQuery.hasSolution());
        String evalSol = evalQuery.oneSolution().get("Dist").toString();
        HashMap<String, String> json = new HashMap<>();
        json.put("Sol",initialSol);
        json.put("StochasticHillCLimbing",hcSol);
        json.put("Lucro",evalSol);
        return json;
    }
}
