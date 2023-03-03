package nus.iss.pafassessment.utils;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;



import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import nus.iss.pafassessment.models.Account;

public class Utils {
    public static Account fromSQL(SqlRowSet rs){
        Account acc = new Account();
        acc.setFromAccountId(rs.getString("account_id"));
        acc.setToAccountId(rs.getString("account_id"));
        acc.setName(rs.getString("name"));
        acc.setBalance(rs.getFloat("balance"));
        return acc;
    }

    public static Account fromSQLshort(SqlRowSet rs){
        Account acc = new Account();
        acc.setFromAccountId(rs.getString("account_id"));
        acc.setToAccountId(rs.getString("account_id"));
        acc.setName(rs.getString("name"));
        acc.setBalance(rs.getFloat("balance"));
        return acc;
    }

    public static JsonObject toJson(Account acc){

		return Json.createObjectBuilder()
			.add("transactionId", acc.getTransactionId())
			.add("date", new Date().getTime())
			.add("from_account", acc.getFromAccountId())
            .add("to_account", acc.getToAccountId())
			.build();
    }
}
