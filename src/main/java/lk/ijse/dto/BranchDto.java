package lk.ijse.dto;

public class BranchDto {
    private String branchId;
    private String name;
    private String city;
    private String address;
    private String postelCode;

    public BranchDto(String branchId, String name, String city, String address, String postelCode) {
        this.branchId = branchId;
        this.name = name;
        this.city = city;
        this.address = address;
        this.postelCode = postelCode;
    }

    public BranchDto() {
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
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

    public String getPostelCode() {
        return postelCode;
    }

    public void setPostelCode(String postelCode) {
        this.postelCode = postelCode;
    }

    @Override
    public String toString() {
        return "BranchDto{" +
                "branchId='" + branchId + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postelCode='" + postelCode + '\'' +
                '}';
    }
}
