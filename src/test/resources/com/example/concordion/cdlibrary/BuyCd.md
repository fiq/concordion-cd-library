# Buy CD

As a CD retailer I want purchases to be correctly processed
so that music charts and inventory levels remain accurate.

This is inspired by a [CD warehouse example](https://www.youtube.com/watch?v=yvpikkOv0eU) 
by Jason Gormon :
``Given a CD that's not in the Top 111 which we have in stock, 
When the customer buys that CD
Then one copy is deducted from CD's stock
and the customer's card is charged our price for that CD
and the charts are notified of the sale.``

_TODO_This needs simplifying further, it's very functional_

## Example Purchasing a CD charges at the correct price

Given the following CDs in our inventory:

| [ ][givenCd] [CD Title][cdTitle]  | [CD Artist][cdArtist] | [Stock][stockLevel] | [Our Price][price] |
| -------------------------------- | --------------------- | ------------------- | ------------------ | 
| So                               | Peter Gabriel         | 10                  |               9.99 | 
| Lionheart                        | Kate Bush             | 5                   |               8.99 | 

[cdTitle]: - "#cdTitle"
[cdArtist]: - "#cdArtist"
[stockLevel]: - "#stockLevel"
[price]: - "#price"
[givenCd]: - "givenCd(#cdTitle, #cdArtist, #stockLevel, #price)"

When these CDs are correctly purchased they will then be charged at the expected price:

| [ ][buyCd] [CD Title][buyCdTitle] | [CD Artist][buyCdArtist] | [Charged Price][chargedPrice] |
| --------------------------------- | ------------------------ | ----------------------------- |
| So                                | Peter Gabriel            |          9.99                 |
| Lionheart                         | Kate Bush                |          8.99                 |

[buyCd]: - "#chargedPrice = buyCd(#buyCdTitle, #buyCdArtist)"
[buyCdTitle]: - "#buyCdTitle"
[buyCdArtist]: - "#buyCdArtist"
[chargedPrice]: - "?=#chargedPrice"

## Example Purchasing a CD updates the inventory as expected

Given the following CD in our inventory:

| [ ][givenCd] [CD Title][cdTitle] | [CD Artist][cdArtist] | [Stock][stockLevel] | [Our Price][price] |
| -------------------------------- | --------------------- | ------------------- | ------------------ | 
| So                               | Peter Gabriel         | 10                  |               9.99 | 

[cdTitle]: - "#cdTitle"
[cdArtist]: - "#cdArtist"
[stockLevel]: - "#stockLevel"
[price]: - "#price"
[givenCd]: - "givenCd(#cdTitle, #cdArtist, #stockLevel, #price)"

[ ](- "buyCd(#cdTitle, #cdArtist)")
[ ](- "#stockDetails = getCdStockDetails(#cdTitle, #cdArtist)")

When the CD is purchased our stock levels will be
decremented [9](- "?= #stockDetails.getStockLevel())

## Example Purchasing a CD sends chart update notifications 

Given CDs in our inventory, when purchased they should produce the expected chart notification.

| [ ][chart.cdBought] [CD Title][chart.cdTitle] | [CD Artist][chart.cdArtist] | [Produces Notification](- "?=#chartNotification.getMessage()") |
| ---------------------------------------------------------- | --------------------- | -------------------------------------- |
| So                                                         | Peter Gabriel         | sales: 1; album: Peter Gabriel - So    | 
| Lionheart                                                  | Kate Bush             | sales: 1; album: Kate Bush - Lionheart |

[chart.cdTitle]: - "#chartCdTitle"
[chart.cdArtist]: - "#chartCdArtist"
[chart.cdBought]: - "#chartNotification = buyCdAndNotify(#chartCdTitle, #chartCdArtist)"




