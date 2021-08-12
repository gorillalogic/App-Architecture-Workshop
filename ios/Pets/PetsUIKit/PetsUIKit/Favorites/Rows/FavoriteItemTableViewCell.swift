//
//  FavoriteItemTableViewCell.swift
//  PetsUIKit
//
//  Created by Dylan Velez on 12/08/21.
//

import UIKit
import Models

class FavoriteItemTableViewCell: UITableViewCell {
    
    @IBOutlet weak var containerView: UIView! {
        didSet {
            containerView.layer.cornerRadius = 10
            containerView.layer.shadowOpacity = 1
            containerView.layer.shadowRadius = 2
            containerView.layer.shadowColor = UIColor.black.cgColor
            containerView.layer.shadowOffset = CGSize(width: 3, height: 3)
            containerView.backgroundColor = .white
            containerView.layer.masksToBounds = true
            containerView.layer.borderWidth = 1
            containerView.layer.borderColor = UIColor.lightGray.cgColor
        }
    }
    
    @IBOutlet weak var petImage: UIImageView!
    @IBOutlet weak var petName: UILabel!
    var onHeartTapped: (() -> Void)? = nil
    
    func configure(with pet: Pet) {
        petImage.sd_setImage(with: URL(string: pet.petImage), placeholderImage: UIImage(named: "pawprint"))
        petName.text = pet.name
    }
    
    @IBAction func onHeartTap(_ sender: Any) {
        onHeartTapped?()
    }
}
