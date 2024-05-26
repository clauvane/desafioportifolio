module.exports = {
    testEnvironment: 'jsdom',
    transform: {
      '^.+\\.jsx?$': 'babel-jest',  // Transforma arquivos JavaScript usando Babel
    },
    moduleNameMapper: {
      '\\.(css|less|sass|scss)$': 'identity-obj-proxy',  // Mapeia arquivos de estilo para objetos vazios
    },
    setupFilesAfterEnv: ['./src/setupTests.js'],  // Arquivo de configuração adicional
  };
  