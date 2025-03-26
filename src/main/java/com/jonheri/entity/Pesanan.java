package com.jonheri.entity;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Pesanan implements Serializable {

    @Id
    private String id;

    private String nomor;

    @Temporal(TemporalType.DATE)
    private Date tanggal;

    private String alamatPengiriman;
    private BigDecimal jumlah;
    private BigDecimal ongkir;
    private BigDecimal total;
    private String status;

    @ManyToOne
    @JoinColumn
    private Pengguna pengguna;
}
