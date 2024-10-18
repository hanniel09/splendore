package com.splendore.services.restaurant;

import com.splendore.domain.restaurant.tables.Tables;
import com.splendore.domain.restaurant.tables.TablesRequestDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.restaurant.TablesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TablesService {

    @Autowired
    private TablesRepository tablesRepository;

    public List<Tables> getTablesRepository() {
        return tablesRepository.findAll();
    }

    public Tables getTablesRepositoryById(Long id) {
        return tablesRepository.findById(id).orElseThrow(()-> new NotFoundException("Table not found"));
    }

    public Tables crateTables(TablesRequestDTO tablesRequestDTO) {
        Tables tables = new Tables();
        tables.setTableNumber(tablesRequestDTO.tableNumber());
        tables.setCapacity(tablesRequestDTO.capacity());
        tables.setIsOccupied(tablesRequestDTO.isOccupied());
        tables.setOrders(tablesRequestDTO.orders());
        return tablesRepository.save(tables);
    }

    public Tables updateTables(Long id,TablesRequestDTO tablesRequestDTO) {
        Tables tables = getTablesRepositoryById(id);
        tables.setTableNumber(tablesRequestDTO.tableNumber());
        tables.setCapacity(tablesRequestDTO.capacity());
        tables.setIsOccupied(tablesRequestDTO.isOccupied());
        tables.setOrders(tablesRequestDTO.orders());
        return tablesRepository.save(tables);
    }

    public void deleteTables(Long id) {
        Tables tables = getTablesRepositoryById(id);
        tablesRepository.delete(tables);
    }

}
