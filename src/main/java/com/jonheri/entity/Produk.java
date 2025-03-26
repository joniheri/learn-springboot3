package com.jonheri.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Produk {

    @Id
    private String id;

    private String nama;
    private String deskripsi;
    private String gambar;

    private BigDecimal harga;
    private Double stok;

    @ManyToOne
    @JoinColumn
    private Kategori kategori;
}