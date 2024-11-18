package com.producthub.DTO;

import java.util.List;

public class CategoryDTO {

    private Long id;
    private String name;
    private String description;
    private boolean active = true;
    private List<Long> productIds;

    public CategoryDTO() {}

    public CategoryDTO(Long id, String name, String description, boolean active, List<Long> productIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Long> getProductIds() {
        return productIds;
    }
    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }
}
