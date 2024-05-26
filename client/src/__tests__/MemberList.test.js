import React from 'react';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import MemberList from '../components/MemberList';

const mock = new MockAdapter(axios);

describe('MemberList', () => {
  beforeEach(() => {
    mock.reset();
  });

  test('renders member list and handles delete', async () => {
    mock.onGet('http://localhost:8080/api/membros').reply(200, [
      { idPessoa: 1, idProjeto: 1 },
      { idPessoa: 2, idProjeto: 2 },
    ]);

    render(<MemberList onEdit={jest.fn()} refresh={false} />);

    expect(await screen.findByText(/Pessoa ID: 1/i)).toBeInTheDocument();
    expect(screen.getByText(/Projeto ID: 1/i)).toBeInTheDocument();
    expect(screen.getByText(/Pessoa ID: 2/i)).toBeInTheDocument();
    expect(screen.getByText(/Projeto ID: 2/i)).toBeInTheDocument();

    mock.onDelete('http://localhost:8080/api/membros/1/1').reply(200);

    userEvent.click(screen.getAllByText('Delete')[0]);

    await waitFor(() => {
      expect(screen.queryByText(/Pessoa ID: 1/i)).not.toBeInTheDocument();
    });
  });
});
