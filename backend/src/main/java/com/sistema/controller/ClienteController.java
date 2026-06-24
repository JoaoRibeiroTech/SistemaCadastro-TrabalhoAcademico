package com.sistema.controller;

import com.sistema.model.Cliente;
import com.sistema.service.PublicadorEvento;
import com.sistema.service.ServicoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ServicoCliente servicoCliente;
    private final PublicadorEvento publicadorEvento;

    @GetMapping
    public List<Cliente> listar() {
        return servicoCliente.listar();
    }

    @PostMapping
    public Cliente salvar(@RequestBody Cliente cliente) {
        Cliente salvo = servicoCliente.salvar(cliente);
        publicadorEvento.publicar("CRIAR", "Cliente", salvo.getId(), "Cliente criado");
        return salvo;
    }

    @PutMapping("/{id}")
    public Cliente atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente atualizado = servicoCliente.atualizar(id, cliente);
        publicadorEvento.publicar("ATUALIZAR", "Cliente", atualizado.getId(), "Cliente atualizado");
        return atualizado;
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        servicoCliente.excluir(id);
        publicadorEvento.publicar("EXCLUIR", "Cliente", id, "Cliente excluído");
    }
}