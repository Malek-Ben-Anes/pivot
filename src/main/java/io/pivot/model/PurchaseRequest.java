package io.pivot.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
@Data
public class Article {
    private final String name;
    private final String type;
    private final Long startPrice;

    private Set<Bid> bids;
}
