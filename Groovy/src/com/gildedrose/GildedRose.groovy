package com.gildedrose

import com.sun.corba.se.impl.orbutil.closure.Constant

class GildedRose {
    Item[] items
    Map listOfItems = [
            "Brie"            : "Aged Brie",
            "BackstagePasses" : "Backstage passes to a TAFKAL80ETC concert",
            "Sulfuras"        : "Sulfuras, Hand of Ragnaros",
            "Conjured"        : "Conjured Mana Cake"
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
        Integer qualityAdjustment = 1
        Boolean negativeDegrade = (
                !item.name.equals(listOfItems.Brie) &&
                !item.name.equals(listOfItems.BackstagePasses) &&
                !item.name.equals(listOfItems.Sulfuras)
        )
        if (negativeDegrade) {
            adjustQuality(item, 0 - qualityAdjustment)
            if (item.name.equals(listOfItems.Conjured)){
                adjustQuality(item, (0 - qualityAdjustment))
            }
        } else {
            adjustQuality(item, qualityAdjustment)

            if (item.name.equals(listOfItems.BackstagePasses)) {
                if (item.sellIn < 11) {
                    adjustQuality(item, qualityAdjustment)
                }

                if (item.sellIn < 6) {
                    adjustQuality(item, qualityAdjustment)
                }
            }
        }

        if (!item.name.equals(listOfItems.Sulfuras)) {
            item.sellIn = item.sellIn - qualityAdjustment
        }

        if (item.sellIn < 0) {
            if (item.name.equals(listOfItems.BackstagePasses)){
                item.quality = item.quality - item.quality
            }
            if (item.name.equals(listOfItems.Brie)) {
                adjustQuality(item, qualityAdjustment * 2)
            }
            if (item.name.equals(listOfItems.Sulfuras)) {
                adjustQuality(item, 0 - qualityAdjustment)
            }
            if (!item.name.equals(listOfItems.Sulfuras)) {
                adjustQuality(item, 0 - qualityAdjustment)
            }
        }
    }

    void adjustQuality(Item item, int QUAlITY_ADJUSTMENT) {
        def adjustedQuality = item.quality + QUAlITY_ADJUSTMENT
        if (adjustedQuality >= 0 && adjustedQuality <= 50){
            item.quality = adjustedQuality
        }
    }
}
