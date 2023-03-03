package nus.iss.pafassessment.repositoires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import nus.iss.pafassessment.models.Account;

import static nus.iss.pafassessment.repositoires.Queries.*;
import static nus.iss.pafassessment.utils.Utils.*;

import java.util.LinkedList;
import java.util.List;


@Repository
public class AccountRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer updateWithdraw(Account acc){
        Integer updated = jdbcTemplate.update(
            SQL_UPDATE_WITHDRAW,acc.getAmount(),acc.getFromAccountId());

        return updated;
    }

    public Integer updateDeposit(Account acc){
        Integer updated = jdbcTemplate.update(
            SQL_UPDATE_DEPOSIT,acc.getAmount(),acc.getToAccountId()
            );

        return updated;
    }

    public Account getAccById(String aid){
     
            SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_BY_ACC_ID,aid);
            rs.next();
            Account acc = fromSQL(rs);
    
            // System.out.println("\n\n\n\n acc>>>>>"+acc);
    
            return acc;
        }

    public List<String> getAllids(){
        List<String> ids = new LinkedList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_ACC_ID);

        while(rs.next()){
            ids.add(rs.getString("account_id"));
        }
        // System.out.println("\n\n\n\n getAccId" +ids);
        return ids;
    }

  


}
