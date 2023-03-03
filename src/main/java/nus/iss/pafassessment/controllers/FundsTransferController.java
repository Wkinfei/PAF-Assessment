package nus.iss.pafassessment.controllers;

import java.util.LinkedList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpSession;
import nus.iss.pafassessment.exceptions.TransferException;
import nus.iss.pafassessment.models.Account;
import nus.iss.pafassessment.repositoires.AccountRepository;
import nus.iss.pafassessment.services.FundsTransferService;
import nus.iss.pafassessment.services.TransferRedisService;

import static nus.iss.pafassessment.utils.Utils.*;

@Controller
@RequestMapping("/")
public class FundsTransferController {

    @Autowired
    AccountRepository accRepo;

    @Autowired
    FundsTransferService fService;

    @Autowired
    TransferRedisService transferRedisService;
    
    @GetMapping
    public String form(Model model,HttpSession session) {
        Object errors =  session.getAttribute("error");

        // System.out.println("\n\n\n\n\n>>>>getError"+errors);
    
        model.addAttribute("error", errors);
        model.addAttribute("account", new Account());
        return "form";
    }

    @PostMapping(path="/transfer",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String transfer(@ModelAttribute Account account, Model model,HttpSession session) throws TransferException{

        List<String> errors = new LinkedList<>();
        List<String> accounts = accRepo.getAllids();

        Account acc = accRepo.getAccById(account.getFromAccountId());
        Account acc1 = accRepo.getAccById(account.getToAccountId()); 
        
        //c0
        if(!accounts.contains(account.getFromAccountId())){
            errors.add("This FROM account does not exist");
        }

        if(!accounts.contains(account.getToAccountId())){
            errors.add("This TO account does not exist");
        }

        // //c1
        if(account.getFromAccountId().length() !=10){
            errors.add("Account ID must be 10 characters");
        }

        if(account.getToAccountId().length() !=10){
            errors.add("Account ID must be 10 characters");
        }

        // //c2
        if(account.getFromAccountId().contains(account.getToAccountId())){
            errors.add("From Account ID and To Account ID must be not be the same");
        }

        // //c3
        if(account.getAmount() <= 0){
            errors.add("Transfer amount must not be 0 or negative number");
        }

        //c4
        if(account.getAmount() <10){
            errors.add("Minimum transfer amount must be $10");
        }

        //c5 balance != amount
        if(account.getAmount() > acc.getBalance()){
            errors.add("Transfer amount should not exceed your account balance");
        }

        System.out.println("\n\n\n\n errors>>"+errors);

        if(!errors.isEmpty()){
            session.setAttribute("error", errors);
            return "redirect:/";
        }

        fService.updateAcc(account);

        //insert to redis
        JsonObject value = toJson(account);
        transferRedisService.insertTransactionRecord(account.getTransactionId(), value.toString());

        session.invalidate();
        model.addAttribute("account",account);
        model.addAttribute("acc",acc);
        model.addAttribute("acc1",acc1);
        return "transfer";
    }


}
