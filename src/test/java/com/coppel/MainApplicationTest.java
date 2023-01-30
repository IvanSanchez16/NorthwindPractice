package com.coppel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import com.coppel.dto.ApiResponseDTO;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MainApplicationTest {
    @Value("${local.server.port}")
    private int port; // Mapea al RANDOM_PORT especificado en webEnvironment
    
    @Autowired
    private TestRestTemplate restTemplate;
//
//    @Test
//    void getFacturasStatusCode() throws Exception {
//        ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
//        new URL("http://localhost:" + port + "/api/v1/facturas").toString(),
//        ApiResponseDTO.class);
//
//        assertEquals(200, response.getBody().getMeta().getStatusCode());
//    }
//
//    @Test
//    void getFacturasPorIdNombreFactura() throws Exception {
//        ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
//        new URL("http://localhost:" + port + "/api/v1/facturas/1").toString(),
//        ApiResponseDTO.class);
//
//        FacturasDTO facturasDTO = new
//        ModelMapper().map(response.getBody().getData(), FacturasDTO.class);
//        assertEquals("Juan Carlos Zúñiga", facturasDTO.getNombreCliente());
//    }
//
//    @Test
//    void getFacturasPorIdImportes() throws Exception {
//        ResponseEntity<ApiResponseDTO> response = restTemplate.getForEntity(
//        new URL("http://localhost:" + port + "/api/v1/facturas/2").toString(),
//        ApiResponseDTO.class);
//
//        FacturasDTO facturasDTO = new
//        ModelMapper().map(response.getBody().getData(), FacturasDTO.class);
//        List<FacturasDetalleDTO> conceptos =
//        facturasDTO.getFacturaDetalleList();
//        BigDecimal importeFactura = facturasDTO.getMonto();
//        BigDecimal importeConceptos = new BigDecimal(0);
//        BigDecimal importeDetalle = new BigDecimal(0);
//        for (FacturasDetalleDTO facturaDetalle : conceptos) {
//            importeDetalle = facturaDetalle.getPrecioUnitario().multiply(new
//            BigDecimal(facturaDetalle.getCantidad()));
//            importeConceptos = importeConceptos.add(importeDetalle);
//        }
//        assertEquals(importeFactura, importeConceptos);
//    }
}
