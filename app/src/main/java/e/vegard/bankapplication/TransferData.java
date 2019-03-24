package e.vegard.bankapplication;

public class TransferData {
    private int id;      // id unique number for transaction
    private String name; // name of the person who
    private String date; // date of the transfer
    private int amount;  // amount requested;
    private int balance; // balance on the

    //constructor adding the info
    public TransferData(int Oid, String Oname, String Odate, int Oamount, int Obalance) {
        id = Oid;
        name = Oname;
        date = Odate;
        amount = Oamount;
        balance = Obalance;
    }

    // returns the name of transfer
    public String getName() {
        return name;
    }

    // returns the date of the transfer
    public String getDate() {
        return date;
    }

    // returns the id of the transfer
    public int getId() {
        return id;
    }

    // returns the amount of the transfer
    public int getAmount() {
        return amount;
    }

    // returns the balance of the account
    public int getBalance() {return balance;}

}
