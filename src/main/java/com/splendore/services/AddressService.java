package com.splendore.services;

import com.splendore.domain.address.Address;
import com.splendore.domain.address.AddressDTO;
import com.splendore.exceptions.NotFoundException;
import com.splendore.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<AddressDTO> getAllAddresses() {
        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(
                address -> new AddressDTO(
                        address.getId(),
                        address.getStreet(),
                        address.getCity(),
                        address.getState(),
                        address.getZipCode(),
                        address.getCountry()
                )
        ).collect(Collectors.toList());
    }

    public Address getAddressById(UUID id) {
        return this.addressRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Address not found with id" + id)
        );
    }

    public Address createAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.street());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());
        address.setZipCode(addressDTO.zipCode());
        address.setCountry(addressDTO.country());

        return this.addressRepository.save(address);
    }

    public Address updateAddress(UUID id, AddressDTO addressDTO) {
        Address address = this.getAddressById(id);

        address.setStreet(addressDTO.street());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());
        address.setZipCode(addressDTO.zipCode());
        address.setCountry(addressDTO.country());
        return this.addressRepository.save(address);
    }

    public void deleteAddress(UUID id) {
        this.addressRepository.deleteById(id);
    }
}
