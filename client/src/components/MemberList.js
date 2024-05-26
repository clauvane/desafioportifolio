import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Button } from 'react-bootstrap';

const MemberList = ({ onEdit, refresh }) => {
  const [members, setMembers] = useState([]);

  useEffect(() => {
    fetchMembers();
  }, [refresh]);

  const fetchMembers = async () => {
    try {
      const response = await axios.get('http://localhost:8080/api/membros');
      setMembers(response.data);
    } catch (error) {
      console.error('Error fetching members:', error);
    }
  };

  const deleteMember = async (idPessoa, idProjeto) => {
    try {
      await axios.delete(`http://localhost:8080/api/membros/${idPessoa}/${idProjeto}`);
      fetchMembers();
    } catch (error) {
      console.error('Error deleting member:', error);
    }
  };
  return (
    <div>
      <h2>Listagem de Membros</h2>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Nome</th>
            <th>Projeto</th>
            <th>Ações</th>
          </tr>
        </thead>        
        <tbody>
          {
            members?.map(member => {
              const { membroId: { pessoa, projeto } } = member
              return <tr key={`${pessoa.id}-${projeto.id}`}>
                      <td>{pessoa.nome}</td>
                      <td>{projeto.nome}</td>
                      <td>
                        <Button variant="primary" onClick={() => onEdit(member)}>Editar</Button>{' '}
                        <Button variant="danger" onClick={() => deleteMember(pessoa.id, projeto.id)}>Excluir</Button>
                      </td>
                    </tr>
            })
          }
        </tbody>
      </Table>
    </div>
  );
};

export default MemberList;
