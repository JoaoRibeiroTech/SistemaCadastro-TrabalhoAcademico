import React, { useState } from "react";
import api from "../api";

export default function Login({ onLogin }) {
  const [nomeUsuario, setNomeUsuario] = useState("");
  const [senha, setSenha] = useState("");

  const entrar = async (e) => {
    e.preventDefault();
    const resposta = await api.post("/autenticacao/entrar", { nomeUsuario, senha });
    localStorage.setItem("token", resposta.data.token);
    onLogin();
    window.location.href = "/clientes";
  };

  const registrar = async () => {
    const resposta = await api.post("/autenticacao/registrar", { nomeUsuario, senha });
    localStorage.setItem("token", resposta.data.token);
    onLogin();
    window.location.href = "/clientes";
  };

  return (
    <form onSubmit={entrar} style={{ padding: 20 }}>
      <h2>Login</h2>
      <input placeholder="Usuário" value={nomeUsuario} onChange={(e) => setNomeUsuario(e.target.value)} />
      <input placeholder="Senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} />
      <button type="submit">Entrar</button>
      <button type="button" onClick={registrar}>Registrar</button>
    </form>
  );
}