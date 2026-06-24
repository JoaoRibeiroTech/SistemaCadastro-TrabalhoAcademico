package com.sistema.service;

import com.sistema.model.Cliente;
import com.sistema.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoCliente {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id).orElseThrow();
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente cliente) {
        Cliente atual = buscar(id);
        atual.setNome(cliente.getNome());
        atual.setEmail(cliente.getEmail());
        atual.setTelefone(cliente.getTelefone());
        return clienteRepository.save(atual);
    }

    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }
}