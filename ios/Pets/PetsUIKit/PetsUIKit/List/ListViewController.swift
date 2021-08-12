//
//  ListViewController.swift
//  PetsUIKit
//
//  Created by Dylan Velez on 12/08/21.
//

import UIKit
import Models
import Architecture
import ViewModels
import Combine

class ListViewController: UIViewController, ViewConfigurable {
    private lazy var tableView: UITableView = {
        let table = UITableView()
        table.allowsSelection = false
        table.translatesAutoresizingMaskIntoConstraints = false
        table.register(UINib(nibName: "ListItemTableViewCell", bundle: .main), forCellReuseIdentifier: "cell")
        table.rowHeight = 300
        table.separatorStyle = .none
        return table
    }()
    
    private lazy var dataSource: UITableViewDiffableDataSource<Int, Pet> = {
        return UITableViewDiffableDataSource(tableView: tableView,
                                             cellProvider: { tableView, indexPath, pet in
                                                let cell = tableView.dequeueReusableCell(withIdentifier: "cell",
                                                                                         for: indexPath) as? ListItemTableViewCell
                                                cell?.configure(with: pet, isFavorite: self.viewModel.state.favorites.contains(pet))
                                                cell?.onHeartTap = { [weak self] in
                                                    guard let self = self else { return }
                                                    if self.viewModel.state.favorites.contains(pet) {
                                                        self.viewModel.dispatch(event: .removeFromFavorites(id: pet.id))
                                                    } else {
                                                        self.viewModel.dispatch(event: .addToFavorites(id: pet.id))
                                                    }
                                                }
                                                return cell
                                             })
    }()
    
    let viewModel: ListViewModel = .init(store: .init(initialState: .init(), reducer: .init()))
    var stateCancellables: Set<AnyCancellable> = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        addViews()
        
        viewModel.$state
            .sink(receiveValue: stateUpdate)
            .store(in: &stateCancellables)
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        viewModel.dispatch(event: .fetchList)
        viewModel.dispatch(event: .fetchFavorites)
    }
}

private extension ListViewController {
    func stateUpdate(with state: ListViewModel.ViewState) {
        self.update(with: state.list)
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
}

private extension ListViewController {
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
