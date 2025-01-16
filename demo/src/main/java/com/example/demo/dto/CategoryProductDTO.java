package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryProductDTO {
    private Long id;              // Identifiant de la catégorie
    private String name;          // Nom de la catégorie
    private String description;   // Description de la catégorie
    private List<ProductDTO> products;  // Liste des produits dans cette catégorie
}