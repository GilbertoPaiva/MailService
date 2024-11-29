CREATE TABLE IF NOT EXISTS public.auths (
    id uuid NOT NULL,
    created_at timestamp without time zone,
    password character varying(255),
    username character varying(255) UNIQUE,
    PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS public.emails(
    id           UUID PRIMARY KEY,
    subject      VARCHAR(255) NOT NULL,
    body         TEXT         NOT NULL,
    sender       VARCHAR(255) NOT NULL,
    scheduled_at TIMESTAMP    NOT NULL,
    status       VARCHAR(50)  NOT NULL
    );

CREATE TABLE IF NOT EXISTS public.recipients(
    id       UUID PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    email_id UUID         NOT NULL,
    status   VARCHAR(50)  NOT NULL,
    CONSTRAINT fk_email
    FOREIGN KEY (email_id)
    REFERENCES public.emails (id)
    ON DELETE CASCADE
    );




