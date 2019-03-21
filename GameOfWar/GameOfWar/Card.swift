//
//  Card.swift
//  Created by William McCann on 3/13/19.
//

import Foundation
import SpriteKit

class Card: SKSpriteNode {
    let rank: Int
    let suit: Suit
    let card_front: SKTexture
    let card_back: SKTexture
    var faceUp: Bool
    
    init(rank: Int, suit: Suit){
        self.rank = rank
        self.suit = suit
        self.card_back = SKTexture(image: #imageLiteral(resourceName: "Red_back.jpg"))
        self.card_front = SKTexture(imageNamed: String(rank) +
            String(suit.description[suit.description.startIndex]) + ".jpg")
        self.faceUp = false
        super.init(texture: card_back, color: .clear, size: CGSize(width: 172.5, height: 264.0))
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("NSCoding not supported")
    }
    
    func flip() {
        let startFlip = SKAction.scaleX(to: 0.0, duration: 0.1)
        let finishFlip = SKAction.scaleX(to: 1.0, duration: 0.1)
        
        if faceUp {
            run(startFlip) { self.texture = self.card_back
                self.run(finishFlip)
            }
        } else {
            run(startFlip) { self.texture = self.card_front
                self.run(finishFlip)
            }
        }
        faceUp = !faceUp
    }
    
    public override var description: String {
        if rank == 11 {
            return "Jack" + " of " + suit.description
        } else if rank == 12 {
            return "Queen" + " of " + suit.description
        } else if rank == 13 {
            return "King" + " of " + suit.description
        } else if rank == 1 {
            return "Ace" + " of " + suit.description
        } else {
            return String(rank) + " of " + suit.description
        }
    }
    
}
