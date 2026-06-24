import React, { useState } from "react";
import { BrowserRouter, Routes, Route, Navigate, Link } from "react-router-dom";
import Login from "./pages/Login";
import Clientes from "./pages/Clientes";
import Pedidos from "./pages/Pedidos";

function RotaProtegida({ children }) {
  return localStorage.getItem("token") ? children : <Navigate to="/login" />;
}

export default function App() {
  const [logado, setLogado] = useState(!!localStorage.getItem("token"));

  const sair = () => {
    localStorage.removeItem("token");
    setLogado(false);
    window.location.href = "/login";
  };

  return (
    <BrowserRouter>
      <nav style={{ padding: 12, display: "flex", gap: 12 }}>
        <Link to="/clientes">Clientes</Link>
        <Link to="/pedidos">Pedidos</Link>
        <Link to="/login">Login</Link>
        <button onClick={sair}>Sair</button>
      </nav>

      <Routes>
        <Route path="/login" element={<Login onLogin={() => setLogado(true)} />} />
        <Route path="/clientes" element={<RotaProtegida><Clientes /></RotaProtegida>} />
        <Route path="/pedidos" element={<RotaProtegida><Pedidos /></RotaProtegida>} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </BrowserRouter>
  );
}