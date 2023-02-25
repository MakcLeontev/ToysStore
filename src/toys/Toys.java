package toys;

public class Toys {
    private long toysId;
    private String nameToys;
    private int quantity;
    private int dropFrequency;

    public Toys(long toysId, String nameToys, int quantity, int dropFrequency) {
        this.toysId = toysId;
        this.nameToys = nameToys;
        this.quantity = quantity;
        this.dropFrequency = dropFrequency;
    }

    public long getToysId() {
        return toysId;
    }

    public void setToysId(long toysId) {
        this.toysId = toysId;
    }

    public String getNameToys() {
        return nameToys;
    }

    public void setNameToys(String nameToys) {
        this.nameToys = nameToys;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDropFrequency() {
        return dropFrequency;
    }

    public void setDropFrequency(int dropFrequency) {
        this.dropFrequency = dropFrequency;
    }

    @Override
    public String toString() {
        return "Toys{" +
                "toysId=" + toysId +
                ", nameToys='" + nameToys + '\'' +
                ", quantity=" + quantity +
                ", dropFrequency=" + dropFrequency +
                '}';
    }
}
