//
//  Deck.swift
//  Created by William McCann on 3/13/19.
//

import Foundation
import SpriteKit

class Deck: Sequence {
    
    private var deck: [Card]
    
    init() {
        self.deck = Deck.createDeck()
        self.shuffle()
    }
    
    init(cardlist: [Card]){
        self.deck = cardlist
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    class func createDeck() -> [Card]{
        var deck: [Card] = []
        for rank in 1...13{
            deck.append(Card(rank: rank, suit: Suit.Hearts))
            deck.append(Card(rank: rank, suit: Suit.Diamonds))
            deck.append(Card(rank: rank, suit: Suit.Clubs))
            deck.append(Card(rank: rank, suit: Suit.Spades))
        }
        return deck
    }
    
    func makeIterator() -> Array<Card>.Iterator {
        return deck.makeIterator()
    }
    
    public func isEmpty() -> Bool {
        return self.deck.isEmpty
    }
    
    public func getTopCard() -> Card {
        return self.deal()
    }
    
    private func shuffle() {
        self.deck.shuffle()
    }
    
    private func deal() -> Card {
        let card:Card = self.deck.removeFirst()
        card.flip()
        return card
    }
    
}
