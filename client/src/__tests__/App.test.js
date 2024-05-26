import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';

import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import App from '../App';

const mock = new MockAdapter(axios);

describe('App', () => {
  beforeEach(() => {
    mock.reset();
  });

  test('updates member list after adding a new member', async () => {
    mock.onGet('http://localhost:8080/api/membros').reply(200, [
      { idPessoa: 1, idProjeto: 1 },
      { idPessoa: 2, idProjeto: 2 },
    ]);

    render(<App />);

    expect(await screen.findByText(/Pessoa ID: 1/i)).toBeInTheDocument();
    expect(screen.getByText(/Projeto ID: 1/i)).toBeInTheDocument();
    expect(screen.getByText(/Pessoa ID: 2/i)).toBeInTheDocument();
    expect(screen.getByText(/Projeto ID: 2/i)).toBeInTheDocument();

    const pessoaInput = screen.getByLabelText(/Pessoa ID:/i);
    const projetoInput = screen.getByLabelText(/Projeto ID:/i);
    const addButton = screen.getByText(/Add/i);

    userEvent.clear(pessoaInput);
    userEvent.clear(projetoInput);

    userEvent.type(pessoaInput, '3');
    userEvent.type(projetoInput, '3');

    mock.onPost('http://localhost:8080/api/membros').reply(200);
    mock.onGet('http://localhost:8080/api/membros').reply(200, [
      { idPessoa: 1, idProjeto: 1 },
      { idPessoa: 2, idProjeto: 2 },
      { idPessoa: 3, idProjeto: 3 },
    ]);

    userEvent.click(addButton);

    await waitFor(() => {
      expect(screen.getByText(/Pessoa ID: 3/i)).toBeInTheDocument();
      expect(screen.getByText(/Projeto ID: 3/i)).toBeInTheDocument();
    });
  });
});
