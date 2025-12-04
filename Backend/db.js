const { Pool } = require('pg');
require('dotenv').config();

// Configuração SSL condicional
// - Produção (Railway): SSL habilitado
// - CI/CD (GitHub Actions): SSL desabilitado
// - Local: SSL desabilitado
const sslConfig = process.env.DISABLE_SSL === 'true' || process.env.NODE_ENV === 'test'
  ? false
  : {
      rejectUnauthorized: false,
    };

const pool = new Pool({
  connectionString: process.env.DATABASE_URL,
  ssl: sslConfig,
});

module.exports = { pool };