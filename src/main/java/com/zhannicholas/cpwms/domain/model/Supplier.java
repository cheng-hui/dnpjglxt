package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cpims_supplier", schema = "dbo", catalog = "cpims_db")
public class Supplier {
    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierPerson='" + supplierPerson + '\'' +
                ", supplierTel='" + supplierTel + '\'' +
                ", supplierEmail='" + supplierEmail + '\'' +
                ", supplierAddress='" + supplierAddress + '\'';
    }

    private int supplierId;
    private String supplierName;
    private String supplierPerson;
    private String supplierTel;
    private String supplierEmail;
    private String supplierAddress;
    private List<RecordIn> recordIns;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIER_ID", nullable = false)
    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME", nullable = false, length = 30)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "SUPPLIER_PERSON", nullable = false, length = 10)
    public String getSupplierPerson() {
        return supplierPerson;
    }

    public void setSupplierPerson(String supplierPerson) {
        this.supplierPerson = supplierPerson;
    }

    @Basic
    @Column(name = "SUPPLIER_TEL", nullable = false, length = 20)
    public String getSupplierTel() {
        return supplierTel;
    }

    public void setSupplierTel(String supplierTel) {
        this.supplierTel = supplierTel;
    }

    @Basic
    @Column(name = "SUPPLIER_EMAIL", nullable = false, length = 20)
    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    @Basic
    @Column(name = "SUPPLIER_ADDRESS", nullable = false, length = 30)
    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return supplierId == supplier.supplierId &&
                Objects.equals(supplierName, supplier.supplierName) &&
                Objects.equals(supplierPerson, supplier.supplierPerson) &&
                Objects.equals(supplierTel, supplier.supplierTel) &&
                Objects.equals(supplierEmail, supplier.supplierEmail) &&
                Objects.equals(supplierAddress, supplier.supplierAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, supplierName, supplierPerson, supplierTel, supplierEmail, supplierAddress);
    }

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    public List<RecordIn> getRecordIns() {
        return recordIns;
    }

    public void setRecordIns(List<RecordIn> recordIns) {
        this.recordIns = recordIns;
    }
}
