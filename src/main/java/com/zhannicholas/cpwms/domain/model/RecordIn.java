package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_in", schema = "dbo", catalog = "cpims_db")
public class RecordIn {
    private int id;
    private int number;
    private Date time;
    private String person;
    private Supplier supplier;
    private Parts parts;
    private Respository repository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECORD_ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "RECORD_NUMBER", nullable = false)
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "RECORD_TIME", nullable = false)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "RECORD_PERSON", nullable = false, length = 10)
    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordIn recordIn = (RecordIn) o;
        return id == recordIn.id &&
                number == recordIn.number &&
                Objects.equals(time, recordIn.time) &&
                Objects.equals(person, recordIn.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, time, person);
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_SUPPLIERID", referencedColumnName = "SUPPLIER_ID", nullable = false)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_PARTSID", referencedColumnName = "PARTS_ID", nullable = false)
    public Parts getParts() {
        return parts;
    }

    public void setParts(Parts parts) {
        this.parts = parts;
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_REPOSITORYID", referencedColumnName = "REPO_ID", nullable = false)
    public Respository getRepository() {
        return repository;
    }

    public void setRepository(Respository repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "RecordIn{" +
                "recordId=" + id +
                ", recordNumber=" + number +
                ", recordTime=" + time +
                ", recordPerson='" + person + '\'' +
                ", supplier=" + supplier +
                ", parts=" + parts +
                ", repository=" + repository +
                '}';
    }
}
