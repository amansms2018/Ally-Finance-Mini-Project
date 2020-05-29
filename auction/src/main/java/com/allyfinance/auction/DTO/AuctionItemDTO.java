package com.allyfinance.auction.DTO;

import com.allyfinance.auction.model.Bid;
import com.allyfinance.auction.model.Item;
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
public class AuctionItemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actionItemId;
    private double reservedPrice;

//    @JsonManagedReference
////One to One?
//     @ManyToOne
    @JoinColumn(name = "itemId", nullable=false)
    private Item item;
//
//    @JsonManagedReference
//    @OneToMany(mappedBy = "auctionItem", orphanRemoval = true, targetEntity = Bid.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BidDTO> bidDTOs = new ArrayList<>();

//    public void addBidder(BidDTO bidDTO) {
//           this.bidDTOs.add(bidDTO);
//        bidDTO.setAuctionItem(this);
//    }

}
