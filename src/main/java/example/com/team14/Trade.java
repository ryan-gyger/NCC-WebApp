package example.com.team14;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.sql.Date;
import java.util.List;

@Table(name = "Trades")
public class Trade extends Model {
    @Column(name = "stock")
    public Stock stock;
    @Column(name = "price")
    public double price;
    @Column(name = "quantity")
    public int quantity;
    @Column(name = "dateTime")
    public Date dateTime;

    public Trade() {
        super();
    }

    public Trade(Stock stock, double price, int quantity, Date dateTime) {
        super();
        this.stock = stock;
        this.price = price;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }


    public static List<Trade> getAll() {
        return new Select()
                .from(Trade.class)
                .execute();
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }


    @Override
    public String toString() {
        return "Trade{" +
                "id=" + getId() +
                ", stock=" + stock +
                ", price=" + price +
                ", quantity=" + quantity +
                ", dateTime=" + dateTime +
                '}';
    }
}