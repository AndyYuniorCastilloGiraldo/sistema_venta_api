package com.proyecto1.TiendaProyecto.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "producto")
public class Producto {

    @Column(name = "productoId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productoId;

    @Column(name = "nombreProducto", length = 100, nullable = false)
    private String nombreProducto;

    @Column(name = "descripcion", length = 255, nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Double precio;

    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoriaId", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "proveedorId", nullable = false)
    @JsonBackReference
    private Proveedor proveedor;

}
