package br.fiap.gff.users.domain.entities;

import br.fiap.gff.users.domain.exceptions.CustomerException;
import lombok.Builder;

import java.util.Objects;

@Builder(toBuilder = true)
public record Customer(
        Long id,
        Person person,
        String username,
        String creditCard) {

    /**
     * Adds an address to the person.
     *
     * @param a the address to be added
     */
    public void addAddress(Address a) {
        person.addAddress(a);
    }

    /**
     * Checks if the customer has a main address.
     * 
     * @return true if the customer has a main address, false otherwise
     */
    public boolean hasMainAddress() {
        return person.hasMainAddress();
    }

    /**
     * Updates the customer's address with the provided address object.
     *
     * @param a the new address to update to
     */
    public void updateCustomerAddress(Address a) {
        Address updatedAddress = person.addresses().stream()
                .filter(it -> Objects.equals(it.addressId(), a.addressId()))
                .map(it -> it.updateTo(a))
                .findFirst().orElseThrow(() -> new CustomerException("Can not update! Address not found!"));
        person.addresses().removeIf(it -> Objects.equals(it.addressId(), a.addressId()));
        person.addAddress(updatedAddress);
    }

    /**
     * Removes a customer address based on the address ID.
     *
     * @param addressId the ID of the address to be removed
     */
    public void removeCustomerAddress(String addressId) {
        person.addresses().removeIf(a -> Objects.equals(a.addressId(), addressId));
    }

    /**
     * Updates the phone number of a customer.
     *
     * @param p the new phone object containing the updated phone number
     */
    public void updateCustomerPhone(Phone p) {
        Phone updatedPhone = person.phones().stream()
                .filter(it -> Objects.equals(it.phoneId(), p.phoneId()))
                .map(it -> it.updateTo(p))
                .findFirst().orElseThrow(() -> new CustomerException("Can not update! Phone not found!"));
        person.phones().removeIf(it -> Objects.equals(it.phoneId(), p.phoneId()));
        person.addPhone(updatedPhone);
    }

    /**
     * Removes the customer phone with the specified phone ID.
     *
     * @param phoneId the ID of the phone to be removed
     */
    public void removeCustomerPhone(String phoneId) {
        person.phones().removeIf(p -> Objects.equals(p.phoneId(), phoneId));
    }
}
