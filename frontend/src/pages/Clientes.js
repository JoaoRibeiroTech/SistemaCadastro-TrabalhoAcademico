import React, { useEffect, useState } from "react";
import api from "../api";

export default function Clientes() {
  const [lista, setLista] = useState([]);
  const [form, setForm] = useState({ nome: "", email: "", telefone: "" });

  const carregar = async () => {
    const resposta = await api.get("/clientes");
    setLista(resposta.data);
  };

  useEffect(() => {
    carregar();
  }, []);

  const salvar = async () => {
    await api.post("/clientes", form);
    setForm({ nome: "", email: "", telefone: "" });
    carregar();
  };

  const excluir = async (id) => {
    await api.delete(`/clientes/${id}`);
    carregar();
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Clientes</h2>
      <input placeholder="Nome" value={form.nome} onChange={(e) => setForm({ ...form, nome: e.target.value })} />
      <input placeholder="Email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} />
      <input placeholder="Telefone" value={form.telefone} onChange={(e) => setForm({ ...form, telefone: e.target.value })} />
      <button onClick={salvar}>Salvar</button>

      <ul>
        {lista.map((c) => (
          <li key={c.id}>
            {c.nome} - {c.email} - {c.telefone}
            <button onClick={() => excluir(c.id)}>Excluir</button>
          </li>
        ))}
      </ul>
    </div>
  );
}