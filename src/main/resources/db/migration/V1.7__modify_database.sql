ALTER TABLE user
    DROP FOREIGN KEY fk_user_token;

ALTER TABLE user
    DROP COLUMN token_id;

DROP TABLE user_token;