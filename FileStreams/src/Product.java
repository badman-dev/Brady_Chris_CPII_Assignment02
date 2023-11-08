public class Product {
    private String ID = "";
    private String name = "";
    private String description = "";
    private double cost = 0;

    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public Product(String ID, String name, double cost) {
        this.ID = ID;
        this.name = name;
        this.cost = cost;
    }

    public void formatFields(int idLimit, int nameLimit, int descLimit) {
        if (ID.length() > idLimit) {
            int difference = idLimit - ID.length();
            for (int i = 0; i < difference; i++) {
                ID += " ";
            }
        }
        if (name.length() > nameLimit) {
            int difference = nameLimit - name.length();
            for (int i = 0; i < difference; i++) {
                name += " ";
            }
        }
        if (description.length() > descLimit) {
            int difference = descLimit - description.length();
            for (int i = 0; i < difference; i++) {
                description += " ";
            }
        }
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toCSVDataRecord() {
        return ID + ", " + name + ", " + description + ", " + cost;
    }
}
