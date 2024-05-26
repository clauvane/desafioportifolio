import React, { useState } from 'react';
import MemberList from './components/MemberList';
import MemberForm from './components/MemberForm';
import './App.css';

const App = () => {
  const [memberToEdit, setMemberToEdit] = useState(null);
  const [refresh, setRefresh] = useState(false);

  const handleEdit = (member) => {
    setMemberToEdit(member);
  };

  const handleFormSubmit = () => {
    setMemberToEdit(null);
  };

  const clearEdit = () => {
    setMemberToEdit(null);
  };

  const updateMembers = () => {
    setRefresh(!refresh);
  };

  return (
    <div className="App">
      <h1>Gerenciador de Membros</h1>
      <hr/>
      <MemberForm memberToEdit={memberToEdit} onFormSubmit={handleFormSubmit} clearEdit={clearEdit} updateMembers={updateMembers} />
      <hr/>
      <MemberList onEdit={handleEdit} refresh={refresh} />
    </div>
  );
};

export default App;
