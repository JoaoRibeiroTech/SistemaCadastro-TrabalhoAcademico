package com.sistema.controller;

import com.sistema.model.Pedido;
import com.sistema.service.PublicadorEvento;
import com.sistema.service.ServicoPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoController {

    private final ServicoPedido servicoPedido;
    private final PublicadorEvento publicadorEvento;

    @GetMapping
    public List<Pedido> listar() {
        return servicoPedido.listar();
    }

    @PostMapping
    public Pedido salvar(@RequestBody Pedido pedido) {
        Pedido salvo = servicoPedido.salvar(pedido);
        publicadorEvento.publicar("CRIAR", "Pedido", salvo.getId(), "Pedido criado");
        return salvo;
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        Pedido atualizado = servicoPedido.atualizar(id, pedido);
        publicadorEvento.publicar("ATUALIZAR", "Pedido", atualizado.getId(), "Pedido atualizado");
        return atualizado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        servicoPedido.excluir(id);
        publicadorEvento.publicar("EXCLUIR", "Pedido", id, "Pedido excluído");
    }
}