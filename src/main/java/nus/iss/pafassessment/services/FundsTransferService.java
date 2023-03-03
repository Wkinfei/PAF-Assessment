package nus.iss.pafassessment.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nus.iss.pafassessment.exceptions.TransferException;
import nus.iss.pafassessment.models.Account;
import nus.iss.pafassessment.repositoires.AccountRepository;

@Service
public class FundsTransferService {
    @Autowired
    AccountRepository transferRepo;

    @Transactional(rollbackFor = TransferException.class)
    public void updateAcc(Account acc) throws TransferException{
        String tId= UUID.randomUUID().toString()
            .substring(0, 8);
        acc.setTransactionId(tId);

        int withdraw = transferRepo.updateWithdraw(acc);

        int deposit = transferRepo.updateDeposit(acc);

        if(withdraw <=0 || deposit <=0){
            throw new TransferException();
        }
        
        
    };
}
