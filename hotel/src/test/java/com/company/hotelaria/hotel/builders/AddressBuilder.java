package com.company.hotelaria.hotel.builders;

import com.company.hotelaria.hotel.core.dto.address.AddressResponse;

import java.util.ArrayList;
import java.util.List;

public class AddressBuilder {

    public static List<AddressResponse> novaListaAddressResponse() {
        AddressResponse address1 = criarAddressResponse(1L);
        AddressResponse address2 = criarAddressResponse(2L);
        List<AddressResponse> listAddress = new ArrayList<>();
        listAddress.add(address1);
        listAddress.add(address2);
        return listAddress;
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
