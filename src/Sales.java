import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sales {
    private Transaction[] transactions;
    private int numTransactions;

    public Sales(int maxTransactions) {
        transactions = new Transaction[maxTransactions];
        numTransactions = 0;
    }

    public void addTransaction(String itemName, int quantity, double price, double taxRate) {
        Transaction transaction = new Transaction(itemName, quantity, price, taxRate);
        transactions[numTransactions] = transaction;
        numTransactions++;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < numTransactions; i++) {
            double itemPrice = transactions[i].getPrice() * transactions[i].getQuantity();
            double itemTax = itemPrice * transactions[i].getTaxRate();
            totalPrice += itemPrice + itemTax;
        }
        return totalPrice;
    }

    public void generateReport() {
        JFrame frame = new JFrame("Sales Report");
        JPanel panel = new JPanel();
        panel.setSize(500, 500);

        String[] columnNames = { "ItemSold", "QuantityOfItemSold", "TotalMoney" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < numTransactions; i++) {
            Object[] rowData = { transactions[i].getItemName(), transactions[i].getQuantity(),
                    transactions[i].getPrice() * transactions[i].getQuantity() };
            model.addRow(rowData);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private class Transaction {
        private String itemName;
        private int quantity;
        private double price;
        private double taxRate;

        public Transaction(String itemName, int quantity, double price, double taxRate) {
            this.itemName = itemName;
            this.quantity = quantity;
            this.price = price;
            this.taxRate = taxRate;
        }

        public String getItemName() {
            return itemName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public double getTaxRate() {
            return taxRate;
        }
    }
}
