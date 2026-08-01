package com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.service.pedido;

import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.ActualizarEstadoPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.PedidoResponseViewProjection;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.dto.pedido.RegistroPedidoDTO;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.mapper.PedidoMapperService;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Cliente;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.Detalle_Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.catalogo.Catalogo;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pago.Pago;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.model.pedido.Pedido;
import com.proyecto_final_desarrollo_web2.Urban_Mornings_Back_End.repository.*;
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
    private final PagoRepository pagoRepository;

    @Override
    @Transactional
    public PedidoResponseProjection registroPedido(RegistroPedidoDTO registroPedidoDTO) {

        Cliente cliente = clienteRepository.findById(registroPedidoDTO.cliente_Id())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Pedido nuevoPedido = mapperService.registroDTOToEntity(registroPedidoDTO);


            nuevoPedido.setCliente(cliente);
            nuevoPedido.setCodigoRetiro(generarCodigoretiroPedido());


            List<Detalle_Pedido> detallePedidos = registroPedidoDTO.items().stream()
                    .map(dp-> {
                        Catalogo catalogo = catalogoRepository.findById(dp.catalogoId())
                                .orElseThrow(() -> new EntityNotFoundException("Catalogo no encontrado"));

                        Detalle_Pedido detallePedido = new Detalle_Pedido();
                        detallePedido.setCatalogo(catalogo);
                        detallePedido.setPrecio_Unitario(catalogo.getPrecio());
                        detallePedido.setCantidad(dp.cantidad());
                        detallePedido.setSub_Total(catalogo.getPrecio().multiply(BigDecimal.valueOf(detallePedido.getCantidad())));

                        return detallePedido;
                    }).toList();

        nuevoPedido.setTotal_Pedido(
                detallePedidos.stream()
                        .map(Detalle_Pedido::getSub_Total)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

            nuevoPedido.hacerAdopcionPedido(detallePedidos);
            nuevoPedido = pedidoRepository.save(nuevoPedido);
            detallePedidoRepository.saveAll(detallePedidos);


        Pago pago = new Pago();
        pago.setPedido(nuevoPedido);
        pago.setMetodo_Pago(registroPedidoDTO.paymentMethod());
        pago.setFecha_Pago(LocalDateTime.now());
        pago.setTransaccion_Id(generarTransactionIds(100));
        pago.setMonto_Pagado(nuevoPedido.getTotal_Pedido());

        pagoRepository.save(pago);


        return pedidoRepository.detallarPedido(nuevoPedido.getPedidoId()).stream()
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Pedido no encontrado o hubi algun problema inesperado"));
    }


   public List<PedidoResponseProjection> listarPedidosPorcliente(UUID id_cliente) {
        return pedidoRepository.listarPedidosPorcliente(id_cliente);
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

    @Override
    @Transactional
    public void eliminarPedido(UUID id) {

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));

        pedidoRepository.eliminarPedido(pedidoExistente.getPedidoId());
    }

    @Override
    @Transactional
    public Pedido actualizarPedido(UUID id, RegistroPedidoDTO registroPedidoDTO) {

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con el ID: " + id));

        Pedido datosNuevos = mapperService.registroDTOToEntity(registroPedidoDTO);

        // Llamamos a tu consulta SQL nativa en lugar de usar .save()
        pedidoRepository.actualizarPedido(
                pedidoExistente.getPedidoId(),
                datosNuevos.getCliente().getClienteId(),
                datosNuevos.getFecha_Hora_Retiro(),
                datosNuevos.getCodigoRetiro(),
                datosNuevos.getEstado_Actual().name(), // .name() porque es Enum
                datosNuevos.getTotal_Pedido(),
                pedidoExistente.getFecha_Creacion() // Mantenemos la fecha original
        );

        return pedidoRepository.findById(id).orElse(null);
    }
    @Override
    public List<PedidoResponseProjection> listarPedidos(){
        return pedidoRepository.listarPedidos();
    }

    @Override
    public List<PedidoResponseViewProjection> listarPedidosParaViews(){
        return pedidoRepository.listarTodosView();
    }

    @Override
    public void actulizarEstadoPedido(UUID idPedido ,ActualizarEstadoPedidoDTO nuevoEstadoPedidoDTO) {
        pedidoRepository.actualizarEstadoPedido(
                idPedido,
                nuevoEstadoPedidoDTO.estado().name()
        );
    }

}