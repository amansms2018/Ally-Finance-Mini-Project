package com.allyfinance.auction.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String itemId;
    String description;

    @JsonManagedReference
    @OneToMany(mappedBy = "item", orphanRemoval = true, targetEntity = AuctionItem.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuctionItem> auctionItems = new ArrayList<>();
    public void addAutionItem(AuctionItem auctionItem) {
        this.auctionItems.add(auctionItem);
        auctionItem.setItem(this);
    }

}
