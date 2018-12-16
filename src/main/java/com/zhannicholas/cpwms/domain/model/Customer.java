package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cpims_customer", schema = "dbo", catalog = "cpims_db")
public class Customer {
    private int customerId;
    private String customerName;
    private String customerPerson;
    private String customerTel;
    private String customerEmail;
    private String customerAddress;
    private List<RecordOut> recordOutList;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "CUSTOMER_NAME", nullable = false, length = 30)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Basic
    @Column(name = "CUSTOMER_PERSON", nullable = false, length = 10)
    public String getCustomerPerson() {
        return customerPerson;
    }

    public void setCustomerPerson(String customerPerson) {
        this.customerPerson = customerPerson;
    }

    @Basic
    @Column(name = "CUSTOMER_TEL", nullable = false, length = 20)
    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    @Basic
    @Column(name = "CUSTOMER_EMAIL", nullable = false, length = 20)
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Basic
    @Column(name = "CUSTOMER_ADDRESS", nullable = false, length = 30)
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId == customer.customerId &&
                Objects.equals(customerName, customer.customerName) &&
                Objects.equals(customerPerson, customer.customerPerson) &&
                Objects.equals(customerTel, customer.customerTel) &&
                Objects.equals(customerEmail, customer.customerEmail) &&
                Objects.equals(customerAddress, customer.customerAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, customerName, customerPerson, customerTel, customerEmail, customerAddress);
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    public List<RecordOut> getRecordOutList() {
        return recordOutList;
    }

    public void setRecordOutList(List<RecordOut> recordOutList) {
        this.recordOutList = recordOutList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerPerson='" + customerPerson + '\'' +
                ", customerTel='" + customerTel + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                '}';
    }
}
