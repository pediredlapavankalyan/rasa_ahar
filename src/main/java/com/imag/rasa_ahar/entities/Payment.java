package com.imag.rasa_ahar.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int order_id;
    @Column
    private String paymentMode;
    @Column
    private double amount;
    @Column
    private String status;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "order_id", referencedColumnName = "id",insertable = false,updatable = false)
    private Orders order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && order_id == payment.order_id && Double.compare(amount, payment.amount) == 0 && Objects.equals(paymentMode, payment.paymentMode) && Objects.equals(status, payment.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order_id, paymentMode, amount, status);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", paymentMode='" + paymentMode + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                '}';
    }
}
