CREATE TABLE user_roles
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id),
    role_id INT    NOT NULL REFERENCES roles (id)
);