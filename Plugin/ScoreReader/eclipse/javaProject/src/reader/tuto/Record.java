package reader.tuto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by favre on 11/09/2014.
 */
public class Record {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty  name;
    private final SimpleStringProperty  lastName;
    private final SimpleStringProperty  email;

    public Record(int id, String name, String lastName, String email)
    {
        this.id         = new SimpleIntegerProperty(id);
        this.name       = new SimpleStringProperty(name);
        this.lastName   = new SimpleStringProperty(lastName);
        this.email      = new SimpleStringProperty(email);
    }

    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getLastName() {
        return this.lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
