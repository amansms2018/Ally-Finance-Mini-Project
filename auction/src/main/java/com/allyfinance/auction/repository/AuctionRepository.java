package com.allyfinance.auction.repository;

import com.allyfinance.auction.model.AuctionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionItem, Long> {
}
