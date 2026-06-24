import React, { useEffect, useState } from "react";
import api from "../api";

export default function Pedidos() {
  const [lista, setLista] = useState([]);
  const [form, setForm] = useState({ descricao: "", valor: "", cliente: { id: "" } });

  const carregar = async () => {
    const resposta = await api.get("/pedidos");
    setLista(resposta.data);
  };

  useEffect(() => {
    carregar();
  }, []);

  const salvar = async () => {
    await api.post("/pedidos", {
      ...form,
      valor: Number(form.valor)
    });
    setForm({ descricao: "", valor: "", cliente: { id: "" } });
    carregar();
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>Pedidos</h2>
      <input placeholder="Descrição" value={form.descricao} onChange={(e) => setForm({ ...form, descricao: e.target.value })} />
      <input placeholder="Valor" value={form.valor} onChange={(e) => setForm({ ...form, valor: e.target.value })} />
      <input placeholder="ID do Cliente" value={form.cliente.id} onChange={(e) => setForm({ ...form, cliente: { id: e.target.value } })} />
      <button onClick={salvar}>Salvar</button>

      <ul>
        {lista.map((p) => (
          <li key={p.id}>
            {p.descricao} - {p.valor}
          </li>
        ))}
      </ul>
    </div>
  );
}