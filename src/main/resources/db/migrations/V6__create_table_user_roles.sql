CREATE TABLE user_roles (
                            user_id BIGINT,
                            role VARCHAR(255),
                            FOREIGN KEY (user_id) REFERENCES users (id)
);
