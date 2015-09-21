import React from 'react';

class Teste extends React.Component {
  render() {
    return (
      <h1>Teste Oi</h1>
    );
  }
}

React.render(
  <Teste />,
  document.getElementById('root'));
