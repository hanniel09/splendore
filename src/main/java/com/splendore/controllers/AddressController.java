package com.splendore.controllers;

import com.splendore.domain.address.Address;
import com.splendore.domain.address.AddressDTO;
import com.splendore.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping()
    public ResponseEntity<List<AddressDTO>> getAllAddress() {
       List<AddressDTO> allAddress = this.addressService.getAllAddresses();
       return ResponseEntity.ok(allAddress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable UUID id) {
        Address address = this.addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    @PostMapping()
    public ResponseEntity<Address> createAddress(@RequestBody AddressDTO address) {
        Address createdAddress = this.addressService.createAddress(address);
        return ResponseEntity.ok(createdAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable UUID id, @RequestBody AddressDTO address) {
        Address updatedAddress = this.addressService.updateAddress(id, address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID id){
        this.addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}
