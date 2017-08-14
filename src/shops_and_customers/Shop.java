package shops_and_customers;

public class Shop extends Transactee {

    private int shopID;

    public Shop(
            int transacteeID,
            int shopID,
            String name
    ) {
        super(
                transacteeID,
                TransacteeType.SHOP,
                name
        );
        this.shopID = shopID;
    }

    public void addAccount( Account account ){
        this.accounts.put( account.getType(), account );
    }

    public void receiveProduct( Product product, AccountType accountType ) {
        this.productList.storeProduct( product );
        this.accounts.get( AccountType.REFUNDS ).debit( product.getRetailPrice() );
    }

    public Product vendProduct( Product product, AccountType accountType ) {
        Product fetchProduct = this.productList.fetchProduct( product );
        this.accounts.get( AccountType.SALES ).credit( product.getRetailPrice() );
        return fetchProduct;
    }

    public void restockProduct( Product product ) {
        this.productList.storeProduct( product );
        this.accounts.get( AccountType.CURRENT ).debit( product.getWholesalePrice() );
    }

    public Double getTotal() {
        Double current = this.accounts.get( AccountType.CURRENT ).getBalance();
        Double sales = this.accounts.get( AccountType.SALES ).getBalance();
        Double refunds = this.accounts.get( AccountType.REFUNDS ).getBalance();
        return current + sales + refunds;
    }

}
