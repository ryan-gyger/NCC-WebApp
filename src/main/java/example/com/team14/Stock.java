package example.com.team14;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Date;

@Table(name = "Stocks")
public class Stock extends Model {
    @Column(name = "symbol")
    public String symbol;
    @Column(name = "desc")
    public String desc;
    @Column(name = "lat")
    public double cordLat;
    @Column(name = "long")
    public double cordLong;
    @Column(name = "photo")
    public String photo;
    @Column(name = "price")
    public double price;
    @Column(name = "dateTime")
    public Date dateTime;

    public Stock() {
        super();
    }

    public Stock(String symbol, String desc, Long cordLat, Long cordLong, String photo, Long price, Date dateTime) {
        super();
        this.symbol = symbol;
        this.desc = desc;
        this.cordLat = cordLat;
        this.cordLong = cordLong;
        this.photo = photo;
        this.price = price;
        this.dateTime = dateTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getCordLat() {
        return cordLat;
    }

    public void setCordLat(double cordLat) {
        this.cordLat = cordLat;
    }

    public double getCordLong() {
        return cordLong;
    }

    public void setCordLong(double cordLong) {
        this.cordLong = cordLong;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + getId() +
                ", symbol='" + symbol + '\'' +
                ", desc='" + desc + '\'' +
                ", cordLat=" + cordLat +
                ", cordLong=" + cordLong +
                ", photo='" + photo + '\'' +
                ", price=" + price +
                ", dateTime=" + dateTime +
                '}';
    }
}
