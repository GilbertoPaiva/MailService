-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS public.auths (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    password character varying(255),
    username character varying(255) UNIQUE,
    PRIMARY KEY (id))