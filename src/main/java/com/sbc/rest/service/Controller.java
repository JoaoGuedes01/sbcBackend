package com.sbc.rest.service;

import org.springframework.web.bind.annotation.*;
import org.jpl7.*;

import java.util.*;


@RestController
public class Controller {

    @GetMapping("/")
    public String landing(){
        return "Landing page";
    }

    @GetMapping("/assert")
    public Map<String,String> adicionarFacto(@RequestParam String fact){
        System.out.println("facto -> "+fact);
        //Query de consulta
        Query consult = new Query("consult('C://Users/joaog/Desktop/sbcBackend/src/main/java/prolog/res.pl').");
        System.out.print("consult()-> ");
        System.out.println(consult.hasSolution());
        //Query de adicionar uma escolha
        Query query = new Query("escolher('"+fact+"').");
        System.out.print("escolher() -> ");
        System.out.println(query.hasSolution());
        HashMap<String, String> map = new HashMap<>();
        map.put("status", "200");
        map.put("msg", "Facto adicionado");
        return map;
    }

    @GetMapping("/test")
    public Term cona(){
        Query consult = new Query("consult('C://Users/joaog/Desktop/sbcBackend/src/main/java/prolog/res.pl').");
        System.out.println(consult.hasSolution());
        Query query = new Query("listing(solucao)");
        return query.goal();
    }

    @GetMapping("/res")
    public ArrayList<ArrayList<String>> getResposta(){
        //Query de Consult
        Query consult = new Query("consult('C://Users/joaog/Desktop/sbcBackend/src/main/java/prolog/res.pl').");
        System.out.println(consult.hasSolution());
        //Query da resposta
        Query query = new Query("resposta(P).");
        System.out.println(query.hasSolution());
        //Guardar a resposta em memoria
        Map<String, Term>[] res = query.allSolutions();
        String resString = res[0].get("P").toString();
        resString = resString.replace("[['","");
        resString = resString.replace("'], ['","");
        //Limpar os factos
        Query query2 = new Query("retractall(fact(X)).");
        System.out.println(query2.hasSolution());
        System.out.println("Factos limpos");
        //Dividir a string por \n
        String lines[] = resString.split("\\r?\\n");
        List<String> resArray = new ArrayList<String>(Arrays.asList(lines));
        resArray.remove(resArray.size()-1);
        resArray.removeAll(Arrays.asList("","                          ",null));
        System.out.println("Numero de respostas: "+ resArray.size()/17);
        ArrayList<ArrayList<String>> BigArr = new ArrayList<>();
        for(int i = 0; i < resArray.size()/17; i++){
            ArrayList<String> arr = new ArrayList<String>();
            System.out.println("Fazendo array " + i);
            int pos = 17*i;
            arr.add(resArray.get(pos+0));
            arr.add(resArray.get(pos+1));
            arr.add(resArray.get(pos+2));
            arr.add(resArray.get(pos+3));
            arr.add(resArray.get(pos+4));
            arr.add(resArray.get(pos+5));
            arr.add(resArray.get(pos+6));
            arr.add(resArray.get(pos+7));
            arr.add(resArray.get(pos+8));
            arr.add(resArray.get(pos+9));
            arr.add(resArray.get(pos+10));
            arr.add(resArray.get(pos+11));
            arr.add(resArray.get(pos+12));
            arr.add(resArray.get(pos+13));
            arr.add(resArray.get(pos+14));
            arr.add(resArray.get(pos+15));
            arr.add(resArray.get(pos+16));
            BigArr.add(arr);
        }
        //Retornar o array
        return BigArr;
    }
}
