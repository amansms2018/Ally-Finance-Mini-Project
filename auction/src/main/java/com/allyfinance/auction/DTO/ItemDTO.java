package com.allyfinance.auction.DTO;


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
//@Entity
public class ItemDTO {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String itemId;
    String description;

//    @JsonManagedReference
//    @OneToOne(mappedBy = "item", orphanRemoval = true, targetEntity = AuctionItemDTO.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AuctionItemDTO> auctionItems = new ArrayList<>();

//    public void addAutionItem(AuctionItemDTO auctionItemDTO) {
//        this.auctionItems.add(auctionItemDTO);
//        auctionItemDTO.setItem(this);
//    }

}
