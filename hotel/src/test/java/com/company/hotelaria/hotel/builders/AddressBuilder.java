package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.address.AddressRequest;
import com.company.hotelaria.hotel.core.dto.address.AddressResponse;
import com.company.hotelaria.hotel.core.entities.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressBuilder {

    public static AddressResponse novoAddressResponse() {
        return criarAddressResponse(1L);
    }

    public static Address novoAddress() {
        return criarAdress(1L);
    }

    public static AddressRequest novoAddressRequest() {
        return criarAddressRequest(1L);
    }

    public static List<AddressResponse> novaListaAddressResponse() {
        AddressResponse address1 = criarAddressResponse(1L);
        AddressResponse address2 = criarAddressResponse(2L);
        List<AddressResponse> listAddress = new ArrayList<>();
        listAddress.add(address1);
        listAddress.add(address2);
        return listAddress;
    }

    private static Address criarAdress(Long id) {
        return Address.builder()
                .id(id)
                .streetName("Rua olinda")
                .number("204")
                .complement("ap105")
                .city("Belo horizonte")
                .state("Minas Gerais")
                .zipcode("32838-129")
                .country("brazil")
                .build();
    }

    private static AddressRequest criarAddressRequest(Long id) {
        return AddressRequest.builder()
                .streetName("Rua olinda")
                .number("204")
                .complement("ap105")
                .city("Belo horizonte")
                .state("Minas Gerais")
                .zipcode("32838-129")
                .country("brazil")
                .build();
    }

    private static AddressResponse criarAddressResponse(Long id) {

        return AddressResponse.builder()
                .id(id)
                .streetName("Rua olinda")
                .number("204")
                .complement("ap105")
                .city("Belo horizonte")
                .state("Minas Gerais")
                .zipcode("32838-129")
                .country("brazil")
                .build();
    }
}
