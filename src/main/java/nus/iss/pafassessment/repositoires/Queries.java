package nus.iss.pafassessment.repositoires;

public class Queries {
    public static final String SQL_UPDATE_WITHDRAW = """
        UPDATE accounts 
        SET balance = balance - ?
        WHERE account_id = ?;
            """;
    public static final String SQL_UPDATE_DEPOSIT = """
        UPDATE accounts 
        SET balance = balance + ?
        WHERE account_id = ?;
            """;

    public static final String SQL_GET_BY_ACC_ID = """
        SELECT * FROM accounts WHERE account_id=?;
            """;

    public static final String SQL_GET_ALL_ACC_ID = """
        SELECT * FROM accounts;
            """;
}
