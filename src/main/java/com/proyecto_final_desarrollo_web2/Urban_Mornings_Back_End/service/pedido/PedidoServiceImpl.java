package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.CatalogoRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.ClienteRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.Detalle_PedidoRepository;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoMapperService mapperService;
    private final ClienteRepository clienteRepository;
    private final CatalogoRepository catalogoRepository;
    private final Detalle_PedidoRepository detallePedidoRepository;

    @Override
    @Transactional
    public Pedido registroPedido(RegistroPedidoDTO registroPedidoDTO) {
        

        Pedido nuevoPedido = mapperService.registroDTOToEntity(registroPedidoDTO);

        Cliente cliente = clienteRepository.findById(registroPedidoDTO.cliente_Id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));


            nuevoPedido.setCliente(cliente);
            nuevoPedido.setPedido_Id(UUID.randomUUID());

            nuevoPedido.setCodigoRetiro(generarCodigoretiroPedido());
            nuevoPedido = pedidoRepository.save(nuevoPedido);

            List<Detalle_Pedido> detallePedidos = registroPedidoDTO.items().stream()
                    .map(dp-> {
                        Catalogo catalogo = catalogoRepository.findById(dp.catalogoId())
                                .orElseThrow(() -> new EntityNotFoundException("Catalogo no encontrado"));

                        Detalle_Pedido detallePedido = new Detalle_Pedido();
                        detallePedido.setDetalleId(generarUUID());
                        detallePedido.setCatalogo(catalogo);
                        detallePedido.setPrecio_Unitario(catalogo.getPrecio());
                        detallePedido.setCantidad(dp.cantidad());
                        detallePedido.setSub_Total(catalogo.getPrecio().multiply(BigDecimal.valueOf(detallePedido.getCantidad())));

                        return detallePedido;
                    }).toList();

            nuevoPedido.hacerAdopcionPedido(detallePedidos);
            detallePedidoRepository.saveAll(detallePedidos);
            nuevoPedido.setTotal_Pedido(
                    detallePedidos.stream()
                            .map(Detalle_Pedido::getSub_Total)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
            );

        Pago pago = new Pago();
        pago.setPago_Id(generarUUID());
        pago.setPedido(nuevoPedido);
        pago.setMetodo_Pago(registroPedidoDTO.paymentMethod());
        pago.setFecha_Pago(LocalDateTime.now());
        pago.setTransaccion_Id(generarTransactionIds(100));

        return nuevoPedido;
    }


    private UUID generarUUID(){
        return UUID.randomUUID();
    }


    private Long generarCodigoretiroPedido(){
        long min = 1_000_000_000L;
        long max = 9_999_999_999L;

        long codigo_Retiro;
        boolean existe;
        do {
            codigo_Retiro = ThreadLocalRandom.current().nextLong(min,max);
            existe = pedidoRepository.existsByCodigoRetiro(codigo_Retiro);

        }while (existe);

       return codigo_Retiro;

    }


    private String generarTransactionIds(int tamanio) {
         final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
         final SecureRandom random = new SecureRandom();

         StringBuilder stringBuilder = new StringBuilder();

         for (int i = 0; i < tamanio; i++) {
             int indiceAleatorio = random.nextInt(CARACTERES_PERMITIDOS.length());
             stringBuilder.append(CARACTERES_PERMITIDOS.charAt(indiceAleatorio));

         }

         return stringBuilder.toString();
    }
}