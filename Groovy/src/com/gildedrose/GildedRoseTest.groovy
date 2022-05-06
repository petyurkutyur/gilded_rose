package com.gildedrose

import org.junit.Test

class GildedRoseTest {

    /**
     * Base Case tests to make sure the item name matches
     */
    @Test
    void itemName() {
        def items = [ new Item("foo", 0, 0) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert "foo" == app.items[0].name
    }

    @Test
    void itemSellIn() {
        def items = [ new Item("foo", 5, 0) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert 4 == app.items[0].sellIn
    }

    @Test
    void itemQuality() {
        def items = [ new Item("foo", 7, 10) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert 9 == app.items[0].quality
    }

    /**
     * Once the sell by date has passed, Quality degrades twice as fast.
     */
    @Test
    void sellByDatePassedDoubleDegrade() {
        def items = [ new Item("Crocodile Meat", -1, 8) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Crocodile Meat", -2, 6) as String)
    }

    /**
     * The Quality of an item is never negative.
     */
    @Test
    void qualityNeverNegative() {
        def items = [ new Item("Crocodile Meat", -1, 8) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Crocodile Meat", -2, 6) as String)
    }

    /**
     * "Aged Brie" actually increases in Quality the older it gets. It doubles in quality once expired.
     */
    @Test
    void agedBrieIncreaseInQuality() {
        def items = [ new Item("Aged Brie", 2, 2) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Aged Brie", 1, 3) as String)
    }

    @Test
    void agedBrieDoubleOnceExpired() {
        def items = [ new Item("Aged Brie", -5, 10) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Aged Brie", -6, 12) as String)
    }

    /**
     * The Quality of an item is never more than 50
     */
    @Test
    void agedBrieNotMoreThan50() {
        def items = [ new Item("Aged Brie", 2, 50) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Aged Brie", 1, 50) as String)
    }

    /**
     * "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
     */
    @Test
    void sulfurasLegendaryNeverDecreaseQuality() {
        def items = [ new Item("Sulfuras, Hand of Ragnaros", 0, 80) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Sulfuras, Hand of Ragnaros", 0, 80) as String)
    }

    /**
     * "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
     Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
     Quality drops to 0 after the concert
     */
    @Test
    void backstagePassesMoreThan10Days() {
        def items = [ new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21) as String)
    }

    @Test
    void backstagePassesLessThan10Days() {
        def items = [ new Item("Backstage passes to a TAFKAL80ETC concert", 9, 24) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 26) as String)
    }

    @Test
    void backstagePassesLessThan5Days() {
        def items = [ new Item("Backstage passes to a TAFKAL80ETC concert", 3, 25) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Backstage passes to a TAFKAL80ETC concert", 2, 28) as String)
    }

    @Test
    void backstagePassesOnceExpired() {
        def items = [ new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0) as String)
    }

    /**
     * "Conjured" items degrade in Quality twice as fast as normal items
     */
    @Test
    void conjuredDegradeTwiceAsFast() {
        def items = [ new Item("Conjured", 10, 50) ] as Item[]
        def app = new GildedRose(items)
        app.updateQuality()
        assert app.items[0].toString().equals(new Item("Conjured", 9, 48) as String)
    }
}
