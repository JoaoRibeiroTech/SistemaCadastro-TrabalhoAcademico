import React, { useEffect, useState } from "react";
import axios from "axios";

export default function App() {
  const [eventos, setEventos] = useState([]);

  const carregar = async () => {
    const resposta = await axios.get("http://localhost:8080/monitor/eventos");
    setEventos(resposta.data);
  };

  useEffect(() => {
    carregar();
    const intervalo = setInterval(carregar, 3000);
    return () => clearInterval(intervalo);
  }, []);

  return (
    <div style={{ padding: 20 }}>
      <h2>Monitoramento de Eventos</h2>
      <ul>
        {eventos.map((e) => (
          <li key={e.id}>
            {e.dataEvento} - {e.tipoEvento} - {e.entidade} - {e.entidadeId}
          </li>
        ))}
      </ul>
    </div>
  );
}