package com.sbc.rest.service;

import org.springframework.web.bind.annotation.*;
import org.jpl7.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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

    @GetMapping("/res")
    public String[] getResposta(){
        //Query de Consult
        Query consult = new Query("consult('C://Users/joaog/Desktop/sbcBackend/src/main/java/prolog/res.pl').");
        System.out.println(consult.hasSolution());
        //Query da resposta
        Query query = new Query("resposta(P).");
        System.out.println(query.hasSolution());
        //Guardar a resposta em memoria
        Map<String, Term>[] res = query.allSolutions();
        //Limpar os factos
        Query query2 = new Query("retractall(fact(X)).");
        System.out.println(query2.hasSolution());
        System.out.println("Factos limpos");
        //Dividir a string por \n
        String lines[] = String.valueOf(res[0].get("P")).split("\\r?\\n");
        //Retornar o array
        return lines;
    }
}
