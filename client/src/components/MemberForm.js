import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Col, Container, Row } from 'react-bootstrap';

const MemberForm = ({ memberToEdit, onFormSubmit, clearEdit, updateMembers }) => {
  const [idPessoa, setIdPessoa] = useState('');
  const [idProjeto, setIdProjeto] = useState('');

  useEffect(() => {
    if (memberToEdit) {
      const { membroId: { pessoa, projeto } } = memberToEdit
      setIdPessoa(pessoa.id);
      setIdProjeto(projeto.id);
    }
  }, [memberToEdit]);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const member = { idPessoa, idProjeto }

    try {
      if (memberToEdit) {
        const { membroId: { pessoa, projeto } } = memberToEdit
        await axios.put(`http://localhost:8080/api/membros/${pessoa.id}/${projeto.id}`, member);
      } else {
        await axios.post('http://localhost:8080/api/membros', member);
      }
      onFormSubmit();
      clearEdit();
      setIdPessoa('');
      setIdProjeto('');
      updateMembers();
    } catch (error) {
      console.error('Error saving member:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <Container className="justify-content-md-center" fluid>
      <h2>{memberToEdit ? 'Editar Membro' : 'Adicionar Membro'}</h2>
      <Row>
        <Col md="auto">
          <label>
            {"ID Pessoa:"}
            <input type="text" value={idPessoa} onChange={(e) => setIdPessoa(e.target.value)} required />
          </label>
        </Col>
        <Col md="auto">
          <label>
          {"ID Projeto:"}
          <input type="text" value={idProjeto} onChange={(e) => setIdProjeto(e.target.value)} required />
          </label>
        </Col>
        <Col md="auto">
          <button type="submit">{memberToEdit ? 'Editar' : 'Salvar'}</button>
        </Col>        
      </Row>     
      </Container>
    </form>
  );
};

export default MemberForm;
