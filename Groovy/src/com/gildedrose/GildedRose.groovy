package com.gildedrose

class GildedRose {
    Item[] items
    Map listOfItems = [
            "Brie"            : "Aged Brie",
            "BackstagePasses" : "Backstage passes to a TAFKAL80ETC concert",
            "Sulfuras"        : "Sulfuras, Hand of Ragnaros",
            "Conjured"        : "Conjured"
    ]

    GildedRose(Item[] items) {
        this.items = items
    }

    void updateQuality() {
        items.each {
            UpdateItemQuality(it)
        }
    }

    void UpdateItemQuality(Item item) {
        if (!item.name.equals(listOfItems.Brie)
                && !item.name.equals(listOfItems.BackstagePasses)) {
            if (item.quality > 0) {
                if (!item.name.equals(listOfItems.Sulfuras)) {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (item.quality < 50) {
                item.quality = item.quality + 1

                if (item.name.equals(listOfItems.BackstagePasses)) {
                    if (item.sellIn < 11) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < 6) {
                        if (item.quality < 50) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (!item.name.equals(listOfItems.Sulfuras)) {
            item.sellIn = item.sellIn - 1
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(listOfItems.Brie)) {
                if (!item.name.equals(listOfItems.BackstagePasses)) {
                    if (item.quality > 0) {
                        if (!item.name.equals(listOfItems.Sulfuras)) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (item.quality < 50) {
                    item.quality = item.quality + 1
                }
            }
        }
    }
}
