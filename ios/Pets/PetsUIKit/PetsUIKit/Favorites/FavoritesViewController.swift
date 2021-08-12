//
//  FavoritesViewController.swift
//  PetsUIKit
//
//  Created by Dylan Velez on 12/08/21.
//

import UIKit
import Architecture
import Models
import Combine
import ViewModels

class FavoritesViewController: UIViewController, ViewConfigurable {
    private lazy var tableView: UITableView = {
        let table = UITableView()
        table.allowsSelection = false
        table.translatesAutoresizingMaskIntoConstraints = false
        table.register(UINib(nibName: "FavoriteItemTableViewCell", bundle: .main), forCellReuseIdentifier: "cell")
        table.rowHeight = 100
        table.separatorStyle = .none
        return table
    }()
    
    private lazy var dataSource: UITableViewDiffableDataSource<Int, Pet> = {
        return UITableViewDiffableDataSource(tableView: tableView,
                                             cellProvider: { tableView, indexPath, pet in
                                                let cell = tableView.dequeueReusableCell(withIdentifier: "cell",
                                                                                         for: indexPath) as? FavoriteItemTableViewCell
                                                cell?.configure(with: pet)
                                                cell?.onHeartTapped = { [weak self] in
                                                    self?.viewModel.dispatch(event: .removeFromFavorites(id: pet.id))
                                                }
                                                return cell
                                             })
    }()
    
    let viewModel: FavoritesViewModel = .init(store: .init(initialState: .init(), reducer: .init()))
    var stateCancellables: Set<AnyCancellable> = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        addViews()
        
        viewModel.$state
            .sink(receiveValue: stateUpdate)
            .store(in: &stateCancellables)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        viewModel.dispatch(event: .fetchList)
    }
}

private extension FavoritesViewController {
    func stateUpdate(with state: FavoritesViewModel.ViewState) {
        self.update(with: state.list)
    }
}

private extension FavoritesViewController {
    func update(with list: Pets) {
        var snapshot = NSDiffableDataSourceSnapshot<Int, Pet>()
        snapshot.appendSections([0])
        snapshot.appendItems(list)
        dataSource.apply(snapshot, animatingDifferences: true)
    }
    
    func addViews() {
        self.view.addSubview(tableView)
        
        tableView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        tableView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        tableView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        tableView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
    }
}
