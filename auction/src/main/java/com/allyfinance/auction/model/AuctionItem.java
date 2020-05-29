package com.allyfinance.auction.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuctionItem {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @TableGenerator(name = "Address_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "Addr_Gen", initialValue = 1234, allocationSize = 100)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Address_Gen")
    private Long actionItemId;
    private double reservedPrice;
    private  double CurrentBid;
    private  String BidderName;

    @JsonManagedReference
//One to One?
     @OneToOne
    @JoinColumn(name = "itemId", nullable=false)
    private Item item;

    @JsonManagedReference
    @OneToMany(mappedBy = "auctionItem", orphanRemoval = true, targetEntity = Bid.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Bid> bids = new ArrayList<>();

    public void addBidder(Bid bid) {
              this.bids.add(bid);
              bid.setAuctionItem(this);
    }

    public Bid getHighestBid() {
       return bids.stream().max(Comparator.comparingDouble(Bid::getMaxAutoBidAmount)).get();
    }

    public Bid getSecondHighestBid() {
        Comparator<Bid> SortBidBYAutoBidAmount = Comparator.comparing( Bid::getMaxAutoBidAmount);

        return bids.stream().sorted(SortBidBYAutoBidAmount.reversed())
                .skip(1)
                .limit(1).collect(Collectors.toList()).get(0);

    }
}
