package com.sistema.service;

import com.sistema.model.Cliente;
import com.sistema.model.Pedido;
import com.sistema.repository.ClienteRepository;
import com.sistema.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoPedido {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    public Pedido salvar(Pedido pedido) {
        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow();
            pedido.setCliente(cliente);
        }
        pedido.setDataCriacao(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Long id, Pedido pedido) {
        Pedido atual = pedidoRepository.findById(id).orElseThrow();
        atual.setDescricao(pedido.getDescricao());
        atual.setValor(pedido.getValor());

        if (pedido.getCliente() != null && pedido.getCliente().getId() != null) {
            Cliente cliente = clienteRepository.findById(pedido.getCliente().getId()).orElseThrow();
            atual.setCliente(cliente);
        }

        return pedidoRepository.save(atual);
    }

    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }
}