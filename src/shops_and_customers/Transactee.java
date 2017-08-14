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

    public int getTransacteeID(){
        return this.transacteeID;
    }

    public TransacteeType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public void addAccount( Account account ){
        this.accounts.put( account.getType(), account );
    }

    public Boolean hasAccountType( AccountType accountType ) {
        return ( this.accounts.containsKey( accountType ) );
    }

    public Double getBalance( AccountType accountType ) {
        return this.accounts.get( accountType ).getBalance();
    }

    public Boolean productInStore( Product product ) {
        return this.productList.productInStore( product );
    }

    public Boolean authorise( AccountType accountType, Double amount ){
        return this.accounts.get( accountType ).authorise( amount );
    }
}
