package lk.ijse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity(name = "branch")
public class Branch {
    @Id
    private String id;
    @Column(nullable = false,length = 100)
    private String name;
    @Column(nullable = false,length = 100)
    private String city;
    @Column(nullable = false,length = 200)
    private String address;
    @Column(nullable = false,length = 200)
    private String postalCode;

    public Branch() {
    }

    public Branch(String id, String name, String city, String address, String postalCode) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
