package com.example.videojuegos.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.videojuegos.dto.VentaRequest;
import com.example.videojuegos.dto.VentaResponse;
import com.example.videojuegos.modelo.Venta;
import com.example.videojuegos.modelo.Videojuego;
import com.example.videojuegos.repository.UsuarioRepository;
import com.example.videojuegos.repository.VentaRepository;
import com.example.videojuegos.repository.VideojuegoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional




public class VentaServiceImpl implements VentaService{

    private final VentaRepository ventaRepository;
    private final UsuarioRepository usuarioRepository;
    private final VideojuegoRepository videojuegoRepository;
    
private VentaResponse entitadADTO(Venta venta) {
    return VentaResponse.builder()
            .id(venta.getId())
            .rutComprador(venta.getRutComprador())
            .idVideojuego(venta.getIdVideojuego()) // Esta es la línea que fallaba
            .tituloVideojuego(venta.getTituloVideojuego())
            .precioUnitario(venta.getPrecioUnitario())
            .cantidad(venta.getCantidadStock()) // O venta.getCantidadStock() según cómo esté en tu entidad Venta
            .total(venta.getTotal())
            .fechaCompra(venta.getFechaDeCompra())
            .build();
}

   /*  private Venta dtoAEntidad(VentaRequest dto){
        Venta venta= new Venta();
        venta.setRutComprador(dto.getRutComprador());
        venta.setIdVideojuego(dto.getIdVideojuego());
        

        venta.setCantidadStock(dto.getCantidadStock());
        
        return venta;
    }
*/

  /*   @Override
    public VentaResponse crearVenta(VentaRequest request){
        System.out.println("DEBUG: El ID recibido es: " + request.getIdVideojuego());
        log.info("Procesando la venta para el rut : {}"+request.getRutComprador()+"- del videojuego  : {}"+request.getIdVideojuego() );
        boolean existeUsuario = usuarioRepository.existsByRut(request.getRutComprador());
        if(!existeUsuario){
            log.error("Venta Rechazada no se encontro el rut {}",request.getRutComprador());
            throw new RuntimeException("Error: El cliente con RUT " + request.getRutComprador() + " no existe en el sistema.");
        }
        Videojuego videojuego = videojuegoRepository.findById(request.getIdVideojuego())
                .orElseThrow(() -> new RuntimeException("Error: El videojuego ID " + request.getIdVideojuego() + " no existe."));
        
        if (videojuego.getStock() < request.getCantidadStock()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + videojuego.getStock());}
        
        videojuego.setStock(videojuego.getStock() - request.getCantidadStock());
        videojuegoRepository.save(videojuego);

        // 5. Crear y guardar la Venta
        /*Venta nuevaVenta = new Venta();
        nuevaVenta.setRutComprador(request.getRutComprador());
        nuevaVenta.setIdVideojuego(videojuego.getId());
        nuevaVenta.setTituloVideojuego(videojuego.getTitulo());
        nuevaVenta.setPrecioUnitario(videojuego.getPrecio());
        nuevaVenta.setCantidadStock(request.getCantidadStock()); 
Venta nuevaVenta = new Venta();
nuevaVenta.setRutComprador(request.getRutComprador());
nuevaVenta.setIdVideojuego(videojuego.getId());

// SOLUCIÓN: Saca el título del objeto 'videojuego' que encontraste en la BD, no del request
nuevaVenta.setTituloVideojuego(videojuego.getTitulo()); 

nuevaVenta.setPrecioUnitario(videojuego.getPrecio());
nuevaVenta.setCantidadStock(request.getCantidadStock());

        double totalCalculado = videojuego.getPrecio() * request.getCantidadStock();
        nuevaVenta.setTotal(totalCalculado);
        Venta ventaGuardada = ventaRepository.save(nuevaVenta);
        log.info("Venta finalizada con éxito. ID: {} - Total: ${}", 
             ventaGuardada.getId(), totalCalculado);
        return entitadADTO(ventaGuardada);

        }
*/
    @Override
    public VentaResponse crearVenta(VentaRequest request) {
    // 1. Validar Usuario y Videojuego
    if (!usuarioRepository.existsByRut(request.getRutComprador())) {
        throw new RuntimeException("Usuario no encontrado");
    }
    
    Videojuego videojuego = videojuegoRepository.findById(request.getIdVideojuego())
        .orElseThrow(() -> new RuntimeException("Videojuego no encontrado"));

    // 2. Mapeo Manual (Request -> Modelo)
    Venta venta = new Venta();
    venta.setRutComprador(request.getRutComprador());
    venta.setIdVideojuego(videojuego.getId());
    venta.setTituloVideojuego(videojuego.getTitulo());
    venta.setPrecioUnitario(videojuego.getPrecio());
    venta.setCantidadStock(request.getCantidadStock());
    venta.setTotal(videojuego.getPrecio() * request.getCantidadStock());

    // 3. Guardar
    Venta guardada = ventaRepository.save(venta);

    // 4. Retornar (Modelo -> Response)
    return entitadADTO(guardada);
}



@Override 
        public VentaResponse buscarVenta(Long id){
            log.info("Buscando venta por id: {}", id);
            Venta venta = ventaRepository.findById(id)
            .orElseThrow(()->new RuntimeException("error"));
            return entitadADTO(venta);
        }
    
        @Override
        public Page<VentaResponse> obtenerTodasLasVentas (Pageable pageable){
            Page<Venta> ventaPage = ventaRepository.findAll(pageable);
            return ventaPage.map(this::entitadADTO);
        }
        /*
    VentaResponse crearVenta(VentaRequest requestDTO);

    VentaResponse buscarVenta(Long id);

    Page<VentaResponse> obtenerTodasLasVentas (Pageable pageable);
 */
}
