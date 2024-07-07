package com.mycompany.soc.network.project;

import com.mycompany.soc.network.project.jdbc.DatabaseStatement;
import com.mycompany.soc.network.project.jdbc.DatabasePreparedStatement;


public class Runner {
    public static void main(String[] args) {
        DatabaseStatement.selectUserType();
        DatabasePreparedStatement.createUserType("customer", "customer");
    }
}
