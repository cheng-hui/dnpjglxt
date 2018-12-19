package com.zhannicholas.cpwms.domain.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "cpims_record_out", schema = "dbo", catalog = "cpims_db")
public class RecordOut {
    private int id;
    private int number;
    private Date time;
    private String person;
    private Customer customer;
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
        RecordOut recordOut = (RecordOut) o;
        return id == recordOut.id &&
                number == recordOut.number &&
                Objects.equals(time, recordOut.time) &&
                Objects.equals(time, recordOut.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, time, time);
    }

    @ManyToOne
    @JoinColumn(name = "RECORD_CUSTOMERID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        return "RecordOut{" +
                "recordId=" + id +
                ", recordNumber=" + number +
                ", recordTime=" + time +
                ", recordPerson='" + time + '\'' +
                ", customer=" + customer +
                ", parts=" + parts +
                ", repository=" + repository +
                '}';
    }
}
