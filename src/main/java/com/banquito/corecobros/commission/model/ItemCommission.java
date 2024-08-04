package com.banquito.corecobros.commission.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ITEM_COMMISSION")
public class ItemCommission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_COMMISSION_ID", nullable = false)
    private Long id;

    @Column(name = "COMMISSION_ID", nullable = false)
    private Long commissionId;

    @Column(name = "UNIQUE_ID", length = 10, nullable = false, unique = true)
    private String uniqueId;

    @Column(name = "ITEM_UNIQUE_ID", length = 10, nullable = false)
    private String itemUniqueId;

    @Column(name = "ORDER_UNIQUE_ID", length = 10, nullable = false)
    private String orderUniqueId;

    @Column(name = "ITEM_TYPE", length = 3, nullable = false)
    private String itemType;

    @Column(name = "COMMISSION_VALUE", precision = 17, scale = 2, nullable = false)
    private BigDecimal commissionValue;

    @ManyToOne
    @JoinColumn(name = "COMMISSION_ID", referencedColumnName = "COMMISSION_ID", insertable = false, updatable = false)
    @JsonBackReference
    private Commission commission;

    public ItemCommission(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemCommission other = (ItemCommission) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
