package com.lambdaschool.piggybank.controllers;


import com.lambdaschool.piggybank.models.Coin;
import com.lambdaschool.piggybank.repositories.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CoinController {

    @Autowired
    private CoinRepository coinRepository;

//    /total
    @GetMapping(value = "/total", produces = "application/json")
    public ResponseEntity<?> findTotal(){
        List<Coin> coins = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(coins::add);

        double totalValue = 0.0;

        for(Coin c : coins){
            if(c.getQuantity() > 1){
                System.out.println(c.getQuantity() + " " + c.getNameplural());
            } else {
                System.out.println(c.getQuantity() + " " + c.getName());
            }
            totalValue += c.getTotalValue();
        }
        String finalString = "The piggy bank holds: $" + totalValue + "0";

        System.out.println(finalString);
        return new ResponseEntity<>(finalString, HttpStatus.OK);
    }

//    /remove/{amount}
    @GetMapping(value = "/remove/{amount}", produces = "application/json")
    public ResponseEntity<?> removeAmount(@PathVariable double amount){
        List<Coin> coinList = new ArrayList<>();
        coinRepository.findAll().iterator().forEachRemaining(coinList::add);

        System.out.println("Remove: $"+ amount);

//        INCOMPLETE
        return new ResponseEntity<>(coinList, HttpStatus.OK );

    }



//    Make it run!
//    private List<Coin> filterCoins(List<Coin> coinList, CheckCoins tester){
//        List<Coin> rtnList = new ArrayList<>();
//        for(Coin c : coinList){
//            if (tester.test(c)){
//                rtnList.add(c);
//            }
//        }
//        return rtnList;
//    }

}
