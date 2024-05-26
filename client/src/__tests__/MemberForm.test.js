import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import MemberForm from '../components/MemberForm';

const mock = new MockAdapter(axios);

describe('MemberForm', () => {
  beforeEach(() => {
    mock.reset();
  });

  test('adds a new member', async () => {
    render(<MemberForm memberToEdit={null} onFormSubmit={jest.fn()} clearEdit={jest.fn()} updateMembers={jest.fn()} />);

    const pessoaInput = screen.getByLabelText(/Pessoa ID:/i);
    const projetoInput = screen.getByLabelText(/Projeto ID:/i);
    const addButton = screen.getByText(/Add/i);

    userEvent.type(pessoaInput, '1');
    userEvent.type(projetoInput, '1');

    mock.onPost('http://localhost:8080/api/membros').reply(200);

    fireEvent.click(addButton);

    await waitFor(() => {
      expect(pessoaInput.value).toBe('');
      expect(projetoInput.value).toBe('');
    });
  });

  test('edits an existing member', async () => {
    const memberToEdit = { idPessoa: 1, idProjeto: 1 };

    render(<MemberForm memberToEdit={memberToEdit} onFormSubmit={jest.fn()} clearEdit={jest.fn()} updateMembers={jest.fn()} />);

    const pessoaInput = screen.getByLabelText(/Pessoa ID:/i);
    const projetoInput = screen.getByLabelText(/Projeto ID:/i);
    const updateButton = screen.getByText(/Update/i);

    expect(pessoaInput.value).toBe('1');
    expect(projetoInput.value).toBe('1');

    userEvent.clear(pessoaInput);
    userEvent.clear(projetoInput);

    userEvent.type(pessoaInput, '2');
    userEvent.type(projetoInput, '2');

    mock.onPut(`http://localhost:8080/api/membros/1/1`).reply(200);

    fireEvent.click(updateButton);

    await waitFor(() => {
      expect(pessoaInput.value).toBe('');
      expect(projetoInput.value).toBe('');
    });
  });
});
