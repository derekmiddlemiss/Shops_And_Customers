package shops_and_customers;

import java.util.HashMap;

public abstract class Transactee {

    int transacteeID;
    TransacteeType type;
    String name;
    ProductList productList;
    HashMap< AccountType, Account > accounts;

    public Transactee(
            int transacteeID,
            TransacteeType type,
            String name
    ) {
        this.transacteeID = transacteeID;
        this.type = type;
        this.name = name;
        this.productList = new ProductList();
        this.accounts = new HashMap<>();
    }

    public abstract void receiveProduct( Product product, AccountType accountType );
    public abstract Product vendProduct( Product product, AccountType accountType );


}
