//
//  Suit.swift
//  Created by William McCann on 3/13/19.
//

import Foundation

class Suit: NSObject {
    
    static let Hearts: Suit = Suit(suit: "Hearts");
    static let Clubs: Suit = Suit(suit: "Clubs");
    static let Spades: Suit = Suit(suit: "Spades");
    static let Diamonds: Suit = Suit(suit: "Diamonds");
    
    private let suit: String
    
    private init(suit: String) {
        self.suit = suit
    }
    
    func toString() -> String {
        return self.suit
    }
    
    public override var description: String {
        return suit
    }
    
}
