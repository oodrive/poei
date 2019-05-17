CREATE TABLE secret (
  secret_id SERIAL PRIMARY KEY,
  secret_key text,
  secret_value bytea,
  salt bytea
);